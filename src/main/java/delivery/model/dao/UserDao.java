package delivery.model.dao;

import delivery.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByName(String name);
}
