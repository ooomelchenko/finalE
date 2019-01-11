package delivery.util.bundleManagers;

import java.util.ResourceBundle;

public class SqlQueryManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sql_query");

    private SqlQueryManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}