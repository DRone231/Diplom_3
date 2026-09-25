package ru.yandex.practicum.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    public static void register(String email, String password, String name) {
        String payload = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                email, password, name
        );

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(BASE_URL + "/api/auth/register")
                .then()
                .statusCode(200);
    }

    public static String loginAndGetToken(String email, String password) {
        String payload = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\"}",
                email, password
        );

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(BASE_URL + "/api/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.jsonPath().getString("accessToken");
    }

    public static void deleteByToken(String accessToken) {
        RestAssured.given()
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + "/api/auth/user")
                .then()
                .statusCode(202);
    }
}
