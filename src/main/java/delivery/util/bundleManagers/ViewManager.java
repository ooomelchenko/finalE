package delivery.util.bundleManagers;

import java.util.ResourceBundle;

public class ViewManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("view");
    private ViewManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
