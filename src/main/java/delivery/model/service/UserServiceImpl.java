package delivery.model.service;

import delivery.util.bundleManagers.RegexManager;
import delivery.model.dao.DaoFactoryAbst;
import delivery.model.dao.UserDao;
import delivery.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public List<User> getAllUsers() {
        try (UserDao dao = daoFactoryAbst.createUserDao()) {
            return dao.findAll();
        }
    }

    @Override
    public Optional<User> getById(long id){
        try (UserDao userDao = daoFactoryAbst.createUserDao()) {
            return Optional.ofNullable(userDao.findById(id));
        }
    }

    @Override
    public Optional<User> login(String login, String pass) {
        Optional<User> result; //= Optional.empty();
        try (UserDao userDao = daoFactoryAbst.createUserDao()) {
            result = Optional.ofNullable(userDao.findByLoginPassword(login, pass));
        }
        return result;
    }

    @Override
    public Map<String, String> validateFields(Map<String, String> fieldMap) {
        return fieldMap.entrySet().stream()
                .filter(map -> !map.getValue().matches(RegexManager.getProperty("input." + map.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public User create(User user) {
        try (UserDao userDao = daoFactoryAbst.createUserDao()) {
            userDao.create(user);
        }
        return null;
    }

    @Override
    public User create(String login, String pass, String firstName, String lastName, String email, String role) {
        try (UserDao userDao = daoFactoryAbst.createUserDao()) {
            userDao.create(new User(login, pass, firstName, lastName, email, User.Role.valueOf(role)));
        }
        return null;
    }

    @Override
    public boolean refill(User user, Long payment) {
        try (UserDao userDao = daoFactoryAbst.createUserDao()) {
            return userDao.refill(user, payment);
        }
    }

}
