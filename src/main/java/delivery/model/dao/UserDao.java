package delivery.model.dao;

import delivery.model.entity.User;

public interface UserDao extends GenericDao<User> {

    User findByLoginPassword(String name, String password);

    boolean refill(User user, Long payment);
}
