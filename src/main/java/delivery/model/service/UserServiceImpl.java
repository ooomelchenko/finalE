package delivery.model.service;

import delivery.model.dao.DaoFactoryAbst;
import delivery.model.dao.UserDao;
import delivery.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public List<User> getAllUsers(){
        try (UserDao dao = daoFactoryAbst.createUserDao()) {
            return dao.findAll();
        }
    }

    @Override
    public Optional<User> login(String login, String pass) {
        Optional<User> result; //= Optional.empty();
        try(UserDao userDao = daoFactoryAbst.createUserDao()){
            result = userDao.findByLoginPassword(login, pass);
        }
        return result;

       // return Optional.of(new User(1L, "admin", "admin", "Admin", "Adminchenko", "admin@gmail.com", User.Role.ADMIN, new ArrayList<Order>()));
       // return Optional.empty();
    }

    @Override
    public List<String> validateFields(String login, String pass, String firstName, String lastName, String email, String role) {
        //"wrong."+
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User create(String login, String pass, String firstName, String lastName, String email, String role) {
        try(UserDao userDao = daoFactoryAbst.createUserDao()){
            userDao.create(new User(login, pass, firstName, lastName, email, User.Role.valueOf(role)));
        }
        return null;
    }


}
