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
    public void update(Tariff entity) {

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
