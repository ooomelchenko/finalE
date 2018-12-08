package delivery.util.bundleManagers;

import java.util.ResourceBundle;

public class ContentManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("content");
    private ContentManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
