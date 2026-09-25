package ru.yandex.practicum.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.practicum.model.User;

public class ApiClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @Step("Регистрация пользователя: email={email}")
    public static void register(String email, String password, String name) {
        User user = new User(email, password, name);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL + "/api/auth/register")
                .then()
                .statusCode(200);
    }

    @Step("Вход пользователя: email={email}")
    public static String loginAndGetToken(String email, String password) {
        User user = new User(email, password, null);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL + "/api/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.jsonPath().getString("accessToken");
    }

    @Step("Удаление пользователя по токену")
    public static void deleteByToken(String accessToken) {
        RestAssured.given()
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + "/api/auth/user")
                .then()
                .statusCode(202);
    }
}
