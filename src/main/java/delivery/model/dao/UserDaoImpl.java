package delivery.model.dao;

import delivery.controller.commands.SqlQueryManager;
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
        //CallableStatement
        System.out.println("user.getRole().name() = "+user.getRole().name());
        System.out.println(user);
        try(CallableStatement ps = connection.prepareCall("INSERT into users SET login, password, firstName, lastName, email, role")){
            ps.setString("login", user.getLogin());
            ps.setString( "password", user.getPassword());
            ps.setString( "firstName", user.getFirstName());
            ps.setString( "lastName", user.getLastName());
            ps.setString( "email", user.getEmail());
            ps.setString( "role", user.getRole().name());
            ps.execute();
            System.out.println("execute "+user);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findById(int id) {
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
    public void delete(int id) {

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
        try(PreparedStatement ps = connection.prepareCall(SqlQueryManager.getProperty("user.findByLoginPassword"))){
            ps.setString( 1, login);
            ps.setString( 2, password);
            ResultSet rs;
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()){
                result = Optional.of(mapper.extractFromResultSet(rs));
            }//TODO : ask question how avoid two users with the same name
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }
}
