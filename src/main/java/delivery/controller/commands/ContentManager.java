package delivery.controller.commands;

import java.util.ResourceBundle;

public class ContentManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("content");
    // класс извлекает информацию из файла messages.properties
    private ContentManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
