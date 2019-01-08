package delivery.model.dao;

import delivery.model.dao.mapper.TariffMapper;
import delivery.model.entity.Tariff;
import delivery.util.bundleManagers.SqlQueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffDaoImpl implements TariffDao {

    private Connection connection;

    TariffDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long create(Tariff tariff) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("tariff.create"), Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, tariff.getName());
            ps.setLong(2, tariff.getCostPerKm());
            ps.setLong(3, tariff.getCostPerKg());
            ps.setLong(4, tariff.getPaceDayKm());

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
    public Tariff findById(long id) {

        TariffMapper tariffMapper = new TariffMapper();

        try (PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("tariff.findById"))) {

            st.setLong(1, id);

            ResultSet rs = st.executeQuery();
            rs.next();

            return tariffMapper.extractFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Tariff> findAll() {

        List<Tariff> tariffList = new ArrayList<>();
        TariffMapper tariffMapper = new TariffMapper();

        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(SqlQueryManager.getProperty("tariff.findAll"));
            while (rs.next()) {
                tariffList.add(tariffMapper.extractFromResultSet(rs));
            }
            return tariffList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Tariff> findAvailableByRouteId(long routeId) {

        List<Tariff> tariffList = new ArrayList<>();
        TariffMapper tariffMapper = new TariffMapper();

        try (PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("tariff.find.byRouteId"))) {

            st.setLong(1, routeId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                tariffList.add(tariffMapper.extractFromResultSet(rs));
            }
            return tariffList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Tariff tariff) {

        try(PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("tariff.update"))){

            st.setLong(1, tariff.getCostPerKm());
            st.setLong(2, tariff.getCostPerKg());
            st.setLong(3, tariff.getPaceDayKm());
            st.setLong(4, tariff.getId());

            return st.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(long id) {

        try(PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("tariff.delete"))){

            st.setLong(1, id);

            return st.executeUpdate()>0;

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
}
