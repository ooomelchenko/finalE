package delivery.util.bundleManagers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MessageManager {
    private static final Map<String, ResourceBundle> bundleMap = new HashMap<>();
    static {
        bundleMap.put("ua", ResourceBundle.getBundle("messages", new Locale("ua")));
        bundleMap.put("en", ResourceBundle.getBundle("messages", Locale.ENGLISH));
        bundleMap.put(null, ResourceBundle.getBundle("messages"));
    }

       private MessageManager() { }
    public static String getProperty(String key) {
        return bundleMap.get(null).getString(key);
    }
    public static String getProperty(String key, String locale) {
        return bundleMap.getOrDefault(locale , bundleMap.get(null))
                .getString(key);
    }
}
