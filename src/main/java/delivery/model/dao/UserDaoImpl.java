package delivery.model.dao;

import delivery.model.dao.mapper.UserMapper;
import delivery.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private Connection connection;


    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        /*Map<Integer, User> users = new HashMap<>();
        Map<Integer, Order> users = new HashMap<>();

        final String query = "" +
                " select * from users" +
                " left join studen_has_user using (idstuden)" +
                " left join user using (iduser)";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();
            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper
                        .extractFromResultSet(rs);
                Order user = userMapper
                        .extractFromResultSet(rs);
                user = userMapper
                        .makeUnique(users, user);
                user = userMapper
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
    public Optional<User> findByName(String name) {

        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM users WHERE name = ?")){
            ps.setString( 1, name);
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
