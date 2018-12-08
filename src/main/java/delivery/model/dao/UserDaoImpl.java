package delivery.model.dao;

import delivery.util.bundleManagers.SqlQueryManager;
import delivery.model.dao.mapper.UserMapper;
import delivery.model.entity.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

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
        return null;
    }

    @Override
    public List<User> findAll() {
        /*Map<Integer, User> users = new HashMap<>();
        Map<Integer, Order> orders = new HashMap<>();

        final String query = "" +
                " select * from users" +
                " left join studen_has_user using (idstuden)" +
                " left join user using (iduser)";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();
            UserMapper orderMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper
                        .extractFromResultSet(rs);
                Order order = userMapper
                        .extractFromResultSet(rs);
                user = userMapper
                        .makeUnique(users, user);
                order = userMapper
                        .makeUnique(users, user);
                user.getUsers().add(user);
            }
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }*/
        return null;
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

        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("user.findByLoginPassword"))){
            ps.setString( 1, login);
            ps.setString( 2, password);
            ResultSet rs;
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()){
                result = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }
}
