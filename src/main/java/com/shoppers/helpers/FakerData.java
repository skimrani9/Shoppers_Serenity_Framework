package com.shoppers.helpers;

import com.github.javafaker.Faker;

/**
 * Test data generation using JavaFaker.
 */
public final class FakerData {

    private static final Faker FAKER = new Faker();

    private FakerData() {
    }

    public static String firstName() {
        return FAKER.name().firstName();
    }

    public static String lastName() {
        return FAKER.name().lastName();
    }

    public static String email() {
        return FAKER.internet().emailAddress();
    }

    public static String phoneNumber() {
        return FAKER.phoneNumber().cellPhone();
    }

    public static String streetAddress() {
        return FAKER.address().streetAddress();
    }

    public static String password() {
        return "TestPass1!";
    }

    private static String lastGeneratedPassword = password();

    public static String lastPassword() {
        return lastGeneratedPassword;
    }

    public static String uniqueUsername() {
        lastGeneratedPassword = password();
        return "auto" + System.currentTimeMillis() % 1_000_000_000L;
    }
}
