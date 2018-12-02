package delivery.model.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class FactoryDaoAbst extends DaoFactoryAbst {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
