package delivery.util;

import java.util.Locale;

public class LocaleThreadLocal {
    private static final ThreadLocal<Locale> threadLocalScope = new  ThreadLocal<>();

    public static Locale getLocale() {
        return threadLocalScope.get();
    }

    public static void setLocale(Locale locale) {
        threadLocalScope.set(locale);
    }

}
