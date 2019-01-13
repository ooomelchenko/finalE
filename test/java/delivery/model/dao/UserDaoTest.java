package delivery.model.dao;

import config.EmbeddedDbRunner;
import delivery.controller.exceptions.NotUniqUserException;
import delivery.model.entity.User;
import org.junit.*;

public class UserDaoTest {
    private static EmbeddedDbRunner dbRunner;
    private static DaoFactoryAbst daoFactory;

    @BeforeClass
    public static void beforeAllTests() {
        dbRunner = new EmbeddedDbRunner();
        dbRunner.setUp();
        daoFactory = new TestDaoFactory(dbRunner);
    }

    @Before
    public void beforeEachTest() {
        dbRunner.refresh();
    }

    @AfterClass
    public static void afterAllTests() {
        dbRunner.tearDown();
    }

    @Test
    public void createUser(){
        UserDao dao = daoFactory.createUserDao();
        User user = dao.create(new User("someU", "a44n73", "Alexis", "Omelko", "milo@gmail.com", User.Role.USER ));
        Assert.assertEquals(user.getId(), 29);
    }

    @Test(expected= NotUniqUserException.class)
    public void createNotUniqUser(){
        UserDao dao = daoFactory.createUserDao();
        dao.create(new User("mer", "a44n73", "Alexis", "Omelko", "milo@gmail.com", User.Role.USER ));
    }
    @Test
    public void updateUserTest1(){
        UserDao dao = daoFactory.createUserDao();
        boolean res = dao.update( new User(2L,"someU", "a44n73", "Alexis", "Omelko", "milo@gmail.com", User.Role.USER, null));
        Assert.assertTrue(res);
    }
    @Test
    public void updateUserTest2(){
        UserDao dao = daoFactory.createUserDao();
        boolean res = dao.update( new User(50L,"someU", "a44n73", "Alexis", "Omelko", "milo@gmail.com", User.Role.USER, null));
        Assert.assertFalse(res);
    }
}
