package delivery.model.dao;

import delivery.model.dao.mapper.AvailableOptionMapper;
import delivery.model.dao.mapper.RouteMapper;
import delivery.model.dao.mapper.TariffMapper;
import delivery.model.entity.AvailableOption;
import delivery.util.bundleManagers.SqlQueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvailableOptionDaoImpl implements AvailableOptionDao {

    private Connection connection;

    AvailableOptionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public AvailableOption create(AvailableOption option) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("option.create"), Statement.RETURN_GENERATED_KEYS)){

            ps.setBoolean(1, option.isAvailable());
            ps.setLong(2, option.getRoute().getId());
            ps.setLong(3, option.getTariff().getId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){

                option.setId(rs.getLong(1));

                return option;
            }
            else
                return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateOrInsert(List<AvailableOption> optionList){

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

                return true;
            }
            else{

                connection.rollback();

                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public AvailableOption findById(long id) {

        AvailableOptionMapper mapper = new AvailableOptionMapper();

        try (PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("option.findById"))) {

            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            if(rs.next())
                return mapper.extractFromResultSet(rs);
            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AvailableOption> findAll() {

        List<AvailableOption> optionList = new ArrayList<>();

        AvailableOptionMapper availableOptionMapper = new AvailableOptionMapper();
        RouteMapper routeMapper = new RouteMapper();
        TariffMapper tariffMapper = new TariffMapper();

        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(SqlQueryManager.getProperty("option.findAll"));

            while (rs.next()) {

                AvailableOption option = availableOptionMapper.extractFromResultSet(rs);

                option.setRoute(routeMapper.extractFromResultSet(rs));
                option.setTariff(tariffMapper.extractFromResultSet(rs));

                optionList.add(option);
            }

            return optionList;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(AvailableOption option) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("option.update.byRouteTariffId"))){

            ps.setBoolean(1, option.isAvailable());
            ps.setLong(2, option.getRoute().getId());
            ps.setLong(3, option.getTariff().getId());

            return (ps.executeUpdate()>0);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(long id) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("option.delete"))){

            ps.setLong(1, id);

            return (ps.executeUpdate()>0);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
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
    public AvailableOption findByRouteTariffId(long id_route, long id_tariff) {

        AvailableOptionMapper optionMapper = new AvailableOptionMapper();
        RouteMapper routeMapper = new RouteMapper();
        TariffMapper tariffMapper = new TariffMapper();

        try (PreparedStatement st = connection.prepareStatement(SqlQueryManager.getProperty("option.findByRouteTariffId"))) {

            st.setLong(1, id_route);
            st.setLong(2, id_tariff);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                AvailableOption availableOption = optionMapper.extractFromResultSet(rs);

                availableOption.setRoute(routeMapper.extractFromResultSet(rs));
                availableOption.setTariff(tariffMapper.extractFromResultSet(rs));

                return availableOption;
            }
            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
