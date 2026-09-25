package ru.yandex.practicum.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import ru.yandex.practicum.base.BaseTest;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.BasePage;
import ru.yandex.practicum.api.ApiClient;
import ru.yandex.practicum.utils.UserGenerator;

import java.util.Arrays;
import java.util.Collection;

@Epic("Stellar Burgers UI")
@Feature("Вход в аккаунт")
@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    @Parameter
    public String browser;

    @Parameters(name = "Браузер: {0}")
    public static Collection<String> data() {
        return Arrays.asList("chrome", "yandex");
    }

    private String testEmail;
    private String testPassword;
    private String testName;
    private String accessToken;

    @Before
    public void setUpUser() {
        testEmail = UserGenerator.randomEmail();
        testPassword = UserGenerator.randomPassword();
        testName = UserGenerator.randomName();

        ApiClient.register(testEmail, testPassword, testName);
        accessToken = ApiClient.loginAndGetToken(testEmail, testPassword);
    }

    @After
    public void tearDownUser() {
        if (accessToken != null) {
            ApiClient.deleteByToken(accessToken);
        }
    }

    @Story("Вход через кнопку «Войти в аккаунт» на главной")
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной странице")
    @Test
    public void loginViaMainPageButton() {
        MainPage mainPage = new MainPage(driver)
                .open()
                .clickLoginButton()
                .login(testEmail, testPassword);

        Assert.assertTrue("Кнопка «Оформить заказ» должна быть видна после входа",
                mainPage.isOrderButtonVisible());
    }

    @Story("Вход через кнопку «Личный кабинет»")
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Test
    public void loginViaPersonalCabinetButton() {
        BasePage page = new MainPage(driver)
                .open()
                .clickPersonalCabinet();

        LoginPage loginPage = (LoginPage) page;

        MainPage mainPage = loginPage.login(testEmail, testPassword);

        Assert.assertTrue("Кнопка «Оформить заказ» должна быть видна после входа",
                mainPage.isOrderButtonVisible());
    }

    @Story("Вход через кнопку в форме регистрации")
    @DisplayName("Вход через ссылку «Войти» в форме регистрации")
    @Test
    public void loginViaRegisterForm() {
        LoginPage loginPage = new MainPage(driver)
                .open()
                .clickLoginButton()
                .clickRegisterLink()
                .clickLoginLink();

        MainPage mainPage = loginPage.login(testEmail, testPassword);

        Assert.assertTrue("Кнопка «Оформить заказ» должна быть видна после входа",
                mainPage.isOrderButtonVisible());
    }

    @Story("Вход через кнопку в форме восстановления пароля")
    @DisplayName("Вход через ссылку «Войти» в форме восстановления пароля")
    @Test
    public void loginViaForgotPasswordForm() {
        LoginPage loginPage = new MainPage(driver)
                .open()
                .clickLoginButton()
                .clickForgotPasswordLink()
                .clickLoginLink();

        MainPage mainPage = loginPage.login(testEmail, testPassword);

        Assert.assertTrue("Кнопка «Оформить заказ» должна быть видна после входа",
                mainPage.isOrderButtonVisible());
    }
}
