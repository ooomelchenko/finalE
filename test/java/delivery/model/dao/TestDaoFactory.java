package delivery.model.dao;

import config.EmbeddedDbRunner;

import java.sql.Connection;

public class TestDaoFactory extends DaoFactoryAbst {

    private EmbeddedDbRunner dbRunner;

    TestDaoFactory(EmbeddedDbRunner dbRunner) {
        this.dbRunner = dbRunner;
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }
    @Override
    public RouteDao createRouteDao() {
        return new RouteDaoImpl(getConnection());
    }
    @Override
    public TariffDao createTariffDao() {
        return new TariffDaoImpl(getConnection());
    }
    @Override
    public OrderDao createOrderDao() {
        return new OrderDaoImpl(getConnection());
    }
    @Override
    public AvailableOptionDao createOptionDao() {
        return new AvailableOptionDaoImpl(getConnection());
    }
    @Override
    public BillDao createBillDao() {
        return new BillDaoImpl(getConnection());
    }

    private Connection getConnection() {
            return dbRunner.getConnection();
    }
}
