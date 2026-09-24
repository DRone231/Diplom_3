package ru.yandex.practicum.utils;

import java.util.Random;

public class UserGenerator {
    private static final Random random = new Random();

    public static String randomEmail() {
        return "test_" + random.nextInt(100000) + "@yandex.ru";
    }

    public static String randomName() {
        return "Тестер" + random.nextInt(100000);
    }

    public static String randomPassword() {
        return "pass" + random.nextInt(100000);
    }

    public static String shortPassword() {
        return "12345"; // 5 символов — меньше минимальных 6
    }
}
