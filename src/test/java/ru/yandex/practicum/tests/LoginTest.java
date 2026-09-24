package ru.yandex.practicum.tests;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import ru.yandex.practicum.base.BaseTest;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.LoginPage;

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

    private static final String TEST_EMAIL = "test_login@yandex.ru";
    private static final String TEST_PASSWORD = "password123";

    @Step("Вход через кнопку «Войти в аккаунт» на главной")
    @Story("Вход через кнопку «Войти в аккаунт» на главной")
    @Test
    public void loginViaMainPageButton() {
        MainPage mainPage = new MainPage(driver)
                .open()
                .clickLoginButton()
                .login(TEST_EMAIL, TEST_PASSWORD);

        Assert.assertTrue("Кнопка «Оформить заказ» должна быть видна после входа",
                mainPage.isOrderButtonVisible());
    }

    @Step("Вход через кнопку «Личный кабинет»")
    @Story("Вход через кнопку «Личный кабинет»")
    @Test
    public void loginViaPersonalCabinetButton() {
        new MainPage(driver)
                .open()
                .clickPersonalCabinet();

        MainPage mainPage = new LoginPage(driver)
                .login(TEST_EMAIL, TEST_PASSWORD);

        Assert.assertTrue("Кнопка «Оформить заказ» должна быть видна после входа",
                mainPage.isOrderButtonVisible());
    }

    @Step("Вход через кнопку в форме регистрации")
    @Story("Вход через кнопку в форме регистрации")
    @Test
    public void loginViaRegisterForm() {
        LoginPage loginPage = new MainPage(driver)
                .open()
                .clickLoginButton()
                .clickRegisterLink()
                .clickLoginLink();

        MainPage mainPage = loginPage.login(TEST_EMAIL, TEST_PASSWORD);

        Assert.assertTrue("Кнопка «Оформить заказ» должна быть видна после входа",
                mainPage.isOrderButtonVisible());
    }

    @Step("Вход через кнопку в форме восстановления пароля")
    @Story("Вход через кнопку в форме восстановления пароля")
    @Test
    public void loginViaForgotPasswordForm() {
        LoginPage loginPage = new MainPage(driver)
                .open()
                .clickLoginButton()
                .clickForgotPasswordLink()
                .clickLoginLink();

        MainPage mainPage = loginPage.login(TEST_EMAIL, TEST_PASSWORD);

        Assert.assertTrue("Кнопка «Оформить заказ» должна быть видна после входа",
                mainPage.isOrderButtonVisible());
    }
}
