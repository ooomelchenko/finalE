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
    public int updateOrInsert(List<AvailableOption> optionList){

        try( PreparedStatement updateStatement = connection.prepareStatement(SqlQueryManager.getProperty("option.update.byRouteTariffId"));
                PreparedStatement insertStatement = connection.prepareStatement(SqlQueryManager.getProperty("option.create")) ) {

            connection.setAutoCommit(false);
            int i=0;

            for(AvailableOption option: optionList){
                updateStatement.setBoolean(1, option.isAvailable());
                updateStatement.setLong(2, option.getRoute().getId());
                updateStatement.setLong(3, option.getTariff().getId());

                int upd = updateStatement.executeUpdate();

                if (upd==0){
                    insertStatement.setBoolean(1, option.isAvailable());
                    insertStatement.setLong(2, option.getRoute().getId());
                    insertStatement.setLong(3, option.getTariff().getId());

                    upd = insertStatement.executeUpdate();
                }
                i+=upd;
            }
            if(i==optionList.size()){
                connection.commit();
                return i;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }


        return 0;
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
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
