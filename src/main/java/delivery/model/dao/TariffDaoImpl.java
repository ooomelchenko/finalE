package delivery.model.dao;

import delivery.model.dao.mapper.TariffMapper;
import delivery.model.entity.Tariff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        return null;
    }

    @Override
    public List<Tariff> findAll() {

        List<Tariff> tariffList = new ArrayList<>();
        TariffMapper tariffMapper = new TariffMapper();

        final String query = "select * from orders";

        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(query);

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

    }
}
