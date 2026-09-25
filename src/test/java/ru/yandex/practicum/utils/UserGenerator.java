package ru.yandex.practicum.utils;

import java.util.Random;

public class UserGenerator {
    private static final Random random = new Random();

    public static String randomEmail() {
        return "test_" + System.currentTimeMillis() + random.nextInt(1000) + "@yandex.ru";
    }

    public static String randomName() {
        return "Тестер" + random.nextInt(100000);
    }

    public static String randomPassword() {
        return "pass" + String.format("%06d", random.nextInt(1000000));
    }

    public static String shortPassword() {
        return "12345";
    }
}
