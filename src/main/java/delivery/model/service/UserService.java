package delivery.model.service;

import delivery.model.dao.DaoFactoryAbst;
import delivery.model.dao.UserDao;
import delivery.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    public List<User> getAllStudents(){
        try (UserDao dao = daoFactoryAbst.createUserDao()) {
            return dao.findAll();
        }
    }

    public Optional<User> login(String name){
        Optional<User> result; //= Optional.empty();
        try(UserDao userDao = daoFactoryAbst.createUserDao()){
            result = userDao.findByName(name);
        }
        return result;
    }
}
