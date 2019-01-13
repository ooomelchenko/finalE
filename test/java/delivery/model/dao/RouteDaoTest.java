package delivery.model.dao;

import config.EmbeddedDbRunner;
import delivery.model.entity.Route;
import org.junit.*;

public class RouteDaoTest {
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
    public void findAllReturn1Route() {
        RouteDao dao = daoFactory.createRouteDao();
        int expectedCount = 25;

        int actualCount = dao.findAll().size();
        Assert.assertEquals(expectedCount, actualCount);
     }

    @Test
    public void findByIdRouteTest() {
        RouteDao dao = daoFactory.createRouteDao();
        int testedId = 1;
        String expectedStart = "Odessa";
        String expectedEnd = "Kyiv";

        Route route = dao.findById(testedId);
        String actualStart = route.getRouteStart();
        String actualEnd = route.getRouteEnd();

        Assert.assertEquals(expectedStart, actualStart);
        Assert.assertEquals(expectedEnd, actualEnd);
    }


}
