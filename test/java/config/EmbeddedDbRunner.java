package config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Version;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class EmbeddedDbRunner {
    private Properties dbConfiguration;
    private EmbeddedMysql embeddedDb;
    private MysqlDataSource dataSource;

    public EmbeddedDbRunner() {
        dbConfiguration = new Properties();
        try {
            dbConfiguration.load(Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream("dbconfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUp() {
        MysqldConfig config = MysqldConfig.aMysqldConfig(Version.v5_7_latest)
                .withCharset(Charset.UTF8)
                .withUser(dbConfiguration.getProperty("db.username"),
                          dbConfiguration.getProperty("db.password"))
                .withPort(Integer.parseInt(dbConfiguration.getProperty("db.port")))
                .withTimeZone(TimeZone.getTimeZone("UTC"))
                .withTimeout(Integer.parseInt(dbConfiguration.getProperty("db.timeout")), TimeUnit.MINUTES)
                .build();

        embeddedDb = EmbeddedMysql.anEmbeddedMysql(config)
                .addSchema(dbConfiguration.getProperty("db.schema"),
                        ScriptResolver.classPathScript(dbConfiguration.getProperty("db.initscript")))
                .start();

        dataSource = new MysqlDataSource();
        dataSource.setUrl(dbConfiguration.getProperty("db.url"));
        dataSource.setUser(dbConfiguration.getProperty("db.username"));
        dataSource.setPassword(dbConfiguration.getProperty("db.password"));
        dataSource.setDatabaseName(dbConfiguration.getProperty("db.schema"));
        dataSource.setUseSSL(false);
    }

    public void tearDown() {
        embeddedDb.stop();
    }

    public void refresh() {
        embeddedDb.reloadSchema(dbConfiguration.getProperty("db.schema"),
                ScriptResolver.classPathScript(dbConfiguration.getProperty("db.initscript")));
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
