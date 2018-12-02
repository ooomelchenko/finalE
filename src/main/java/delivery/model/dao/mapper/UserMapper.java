package delivery.model.dao.mapper;

import delivery.model.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("user.firstName"));
        user.setLastName(rs.getString("user.lastName"));
        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> cache,
                           User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
