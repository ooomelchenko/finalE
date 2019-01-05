package delivery.util.bundleManagers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ContentManager {
    private static final Map<String, ResourceBundle> bundleMap = new HashMap<>();

    static {
        bundleMap.put("ua", ResourceBundle.getBundle("content", Locale.forLanguageTag("ua")));
        bundleMap.put("en", ResourceBundle.getBundle("content"));
        bundleMap.put(null, ResourceBundle.getBundle("content"));
    }

    private ContentManager() {
    }

    public static String getProperty(String key) {
        return bundleMap.get(null).getString(key);
    }

    public static String getProperty(String key, String locale) {
        return bundleMap.getOrDefault(locale, bundleMap.get(null))
                .getString(key);
    }
}
