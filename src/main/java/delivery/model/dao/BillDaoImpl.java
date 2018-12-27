package delivery.model.dao;

import delivery.model.dao.mapper.BillMapper;
import delivery.model.dao.mapper.UserMapper;
import delivery.model.entity.Bill;
import delivery.model.entity.User;
import delivery.util.bundleManagers.SqlQueryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillDaoImpl implements BillDao {

    private Connection connection;

    BillDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Bill entity) {

    }

    @Override
    public Bill findById(long id) {

        BillMapper billMapper = new BillMapper();
        UserMapper userMapper = new UserMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.findById"))){

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Bill bill =  billMapper.extractFromResultSet(rs);
                User user = userMapper.extractFromResultSet(rs);
                bill.setUser(user);

                return bill;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return null;
    }

    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public void update(Bill entity) {

    }

    @Override
    public void delete(long id) {

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
                throw new SQLException("Not enough money or already paid");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
