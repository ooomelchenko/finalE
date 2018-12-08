package delivery.model.service;

import delivery.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> login(String login, String pass);

    Map<String, String> validateFields(Map<String, String> fieldMap);

    User create(User user);

    User create(String login, String pass, String firstName, String lastName, String email, String role);

}
