package delivery.model.dao;

        import delivery.model.dao.mapper.OrderMapper;
        import delivery.model.dao.mapper.UserMapper;
        import delivery.model.entity.Order;
        import delivery.model.entity.User;
        import delivery.util.bundleManagers.SqlQueryManager;

        import java.sql.*;
        import java.util.*;

public class UserDaoImpl implements UserDao {

    private Connection connection;

    UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {

        try(PreparedStatement ps = connection.prepareStatement
                (SqlQueryManager.getProperty("user.createUser"))){
            ps.setString(1, user.getLogin());
            ps.setString( 2, user.getPassword());
            ps.setString( 3, user.getFirstName());
            ps.setString( 4, user.getLastName());
            ps.setString( 5, user.getEmail());
            ps.setString( 6, user.getRole().name());
            ps.execute();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findById(long id) {

        UserMapper userMapper = new UserMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("user.findById"))) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return userMapper.extractFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

        @Override
    public List<User> findAll() {
        Map<Long, User> userMap = new HashMap<>();
        Map<Long, Order> orderMap = new HashMap<>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SqlQueryManager.getProperty("user.findAll"));

            UserMapper userMapper = new UserMapper();
            OrderMapper orderMapper = new OrderMapper();

            while (rs.next()) {
                User user = userMapper
                        .extractFromResultSet(rs);
                Order order = orderMapper
                        .extractFromResultSet(rs);
                user = userMapper
                        .makeUnique(userMap, user);
                order = orderMapper
                        .makeUnique(orderMap, order);

                user.getOrders().add(order);
            }
            return new ArrayList<>(userMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByLoginPassword(String login, String password) {

        UserMapper mapper = new UserMapper();

        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("user.findByLoginPassword"))){
            ps.setString( 1, login);
            ps.setString( 2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                result = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public boolean refill(User user, Long payment){

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("user.refill"))){
            ps.setLong(1, payment);
            ps.setLong(2, user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
