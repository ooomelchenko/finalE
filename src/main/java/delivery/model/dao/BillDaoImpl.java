package delivery.model.dao;

import delivery.model.dao.mapper.BillMapper;
import delivery.model.dao.mapper.UserMapper;
import delivery.model.entity.Bill;
import delivery.util.bundleManagers.SqlQueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {

    private Connection connection;

    BillDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long create(Bill bill) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.create"), Statement.RETURN_GENERATED_KEYS)){

            ps.setLong(1, bill.getTotal());
            ps.setBoolean(2, bill.isPaid());
            ps.setLong(3, bill.getUser().getId());
            ps.setLong(4, bill.getOrder().getId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            return rs.getLong(1);
        }
        catch (SQLException e){
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public Bill findById(long id) {

        BillMapper billMapper = new BillMapper();
        UserMapper userMapper = new UserMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.findById"))){

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            rs.next();

            Bill bill =  billMapper.extractFromResultSet(rs);
            bill.setUser(userMapper.extractFromResultSet(rs));

            return bill;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Bill> findAll() {

        List<Bill> billList = new ArrayList<>();

        BillMapper billMapper = new BillMapper();
        UserMapper userMapper = new UserMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.findAll"))){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Bill bill = billMapper.extractFromResultSet(rs);
                bill.setUser(userMapper.extractFromResultSet(rs));

                billList.add(bill);
            }

            return billList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Bill bill) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.update"))){

            ps.setLong(1, bill.getTotal());
            ps.setBoolean(2, bill.isPaid());
            ps.setLong(3, bill.getId());

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(long id) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.delete"))){

            ps.setLong(1, id);

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean settleUp(Bill bill){

        long chargeSum = bill.getTotal();
        long userId = bill.getUser().getId();

        try( PreparedStatement stUser = connection.prepareStatement(SqlQueryManager.getProperty("user.chargeOff"));
             PreparedStatement stUserCheck = connection.prepareStatement(SqlQueryManager.getProperty("user.findById"));
             PreparedStatement stBillCheck = connection.prepareStatement(SqlQueryManager.getProperty("bill.checkIsPaid") );
             PreparedStatement stBill = connection.prepareStatement(SqlQueryManager.getProperty("bill.settleUp") ) ){

            connection.setAutoCommit(false);

            stUser.setLong(1, chargeSum);
            stUser.setLong(2, userId);
            stUser.executeUpdate();

            stUserCheck.setLong(1, userId);
            ResultSet rs = stUserCheck.executeQuery();
            rs.next();
            long accountResidual = rs.getLong("account_sum");

            stBillCheck.setLong(1, bill.getId());
            ResultSet resultSet = stBillCheck.executeQuery();
            resultSet.next();
            boolean isAlreadyPaid = resultSet.getBoolean("is_paid");

            stBill.setLong(1, bill.getId());
            stBill.executeUpdate();

            if(accountResidual >=0 && !isAlreadyPaid){
                connection.commit();
                return true;
            }
            else {
                connection.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
