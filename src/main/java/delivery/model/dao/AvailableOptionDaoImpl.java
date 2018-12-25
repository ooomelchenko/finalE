package delivery.model.dao;

import delivery.model.dao.mapper.AvailableOptionMapper;
import delivery.model.dao.mapper.RouteMapper;
import delivery.model.dao.mapper.TariffMapper;
import delivery.model.entity.AvailableOption;
import delivery.util.bundleManagers.SqlQueryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AvailableOptionDaoImpl implements AvailableOptionDao {

    private Connection connection;

    AvailableOptionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(AvailableOption entity) {

    }

    @Override
    public AvailableOption findById(long id) {
        return null;
    }

    @Override
    public List<AvailableOption> findAll() {
        return null;
    }

    @Override
    public void update(AvailableOption entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close() {

    }

    @Override
    public AvailableOption findByRouteTariffId(long id_route, long id_tariff) {

        AvailableOptionMapper optionMapper = new AvailableOptionMapper();
        RouteMapper routeMapper = new RouteMapper();
        TariffMapper tariffMapper = new TariffMapper();

        try (PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("option.findByRouteTariffId"))) {

            st.setLong(1, id_route);
            st.setLong(2, id_tariff);

            ResultSet rs = st.executeQuery();
            rs.next();
            AvailableOption availableOption = optionMapper.extractFromResultSet(rs);
            availableOption.setRoute(routeMapper.extractFromResultSet(rs));
            availableOption.setTariff(tariffMapper.extractFromResultSet(rs));

            return availableOption;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
