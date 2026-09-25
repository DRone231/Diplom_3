package ru.yandex.practicum.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import ru.yandex.practicum.base.BaseTest;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.RegisterPage;
import ru.yandex.practicum.utils.UserGenerator;

import java.util.Arrays;
import java.util.Collection;

@Epic("Stellar Burgers UI")
@Feature("Регистрация")
@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTest {

    @Parameter
    public String browser;

    @Parameters(name = "Браузер: {0}")
    public static Collection<String> data() {
        return Arrays.asList("chrome", "yandex");
    }

    @Story("Успешная регистрация")
    @DisplayName("Успешная регистрация нового пользователя")
    @Test
    public void successfulRegistration() {
        RegisterPage registerPage = new MainPage(driver)
                .open()
                .clickLoginButton()
                .clickRegisterLink();

        registerPage
                .typeName(UserGenerator.randomName())
                .typeEmail(UserGenerator.randomEmail())
                .typePassword(UserGenerator.randomPassword())
                .clickRegisterButton();

        Assert.assertTrue("Должны перейти на страницу входа после регистрации",
                driver.getCurrentUrl().contains("/login"));
    }

    @Story("Ошибка при пароле меньше 6 символов")
    @DisplayName("Регистрация с паролем короче 6 символов показывает ошибку")
    @Test
    public void registrationWithShortPasswordShowsError() {
        RegisterPage registerPage = new MainPage(driver)
                .open()
                .clickLoginButton()
                .clickRegisterLink();

        registerPage
                .typeName(UserGenerator.randomName())
                .typeEmail(UserGenerator.randomEmail())
                .typePassword(UserGenerator.shortPassword())
                .clickRegisterButton();

        Assert.assertTrue("Должно появиться сообщение об ошибке пароля",
                registerPage.isPasswordErrorVisible());

        Assert.assertEquals("Некорректный пароль",
                registerPage.getPasswordErrorText());
    }
}
