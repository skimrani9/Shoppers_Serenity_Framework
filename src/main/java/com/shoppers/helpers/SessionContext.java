package com.shoppers.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe session storage for values that span multiple scenarios in a suite run.
 */
public final class SessionContext {

    private static final ThreadLocal<Map<String, Object>> SESSION = ThreadLocal.withInitial(HashMap::new);

    private SessionContext() {
    }

    public static void put(String key, Object value) {
        SESSION.get().put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) SESSION.get().get(key);
    }

    public static void clear() {
        SESSION.get().clear();
        SESSION.remove();
    }
}
