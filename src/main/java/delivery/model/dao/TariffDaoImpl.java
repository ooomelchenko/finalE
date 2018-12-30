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
    public void create(Tariff entity) {

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
    public void update(Tariff tariff) {

        try(PreparedStatement st = connection.prepareStatement("Update tariffs set name = ?, cost_per_km=?, cost_per_kg=?, pace_day_km=? where id_tariff=?")){

            st.setString(1, tariff.getName());
            st.setLong(2, tariff.getCostPerKm());
            st.setLong(3, tariff.getCostPerKg());
            st.setLong(4, tariff.getPaceDayKm());
            st.setLong(5, tariff.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
