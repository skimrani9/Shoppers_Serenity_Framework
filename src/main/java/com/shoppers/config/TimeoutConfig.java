package com.shoppers.config;

/**
 * Centralized timeout constants for waits and page loads.
 */
public final class TimeoutConfig {

    public static final int IMPLICIT_WAIT_SECONDS = 10;
    public static final int EXPLICIT_WAIT_SECONDS = 30;
    public static final int PAGE_LOAD_TIMEOUT_SECONDS = 60;
    public static final int SCRIPT_TIMEOUT_SECONDS = 30;
    /** Home page may wait for DB seed on first Docker startup. */
    public static final int HOME_PAGE_LOAD_SECONDS = 180;

    private TimeoutConfig() {
    }
}
