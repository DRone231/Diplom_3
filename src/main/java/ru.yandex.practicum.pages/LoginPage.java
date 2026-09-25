package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT = By.xpath(".//label[contains(text(),'Email')]/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath(".//label[contains(text(),'Пароль')]/following-sibling::input");
    private static final By LOGIN_SUBMIT = By.xpath(".//button[contains(text(),'Войти')]");
    private static final By REGISTER_LINK = By.xpath(".//a[contains(text(),'Зарегистрироваться')]");
    private static final By FORGOT_PASSWORD_LINK = By.xpath(".//a[contains(text(),'Восстановить пароль')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу входа")
    public LoginPage open() {
        driver.get(BASE_URL + "/login");
        return this;
    }

    @Step("Ввести email: {email}")
    public LoginPage typeEmail(String email) {
        type(EMAIL_INPUT, email);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage typePassword(String password) {
        type(PASSWORD_INPUT, password);
        return this;
    }

    @Step("Нажать кнопку «Войти»")
    public MainPage clickLoginButton() {
        click(LOGIN_SUBMIT);
        waitForUrlContains(BASE_URL + "/");
        return new MainPage(driver);
    }

    @Step("Войти в аккаунт с email: {email}")
    public MainPage login(String email, String password) {
        typeEmail(email);
        typePassword(password);
        return clickLoginButton();
    }

    @Step("Нажать ссылку «Зарегистрироваться»")
    public RegisterPage clickRegisterLink() {
        click(REGISTER_LINK);
        waitForUrlContains("/register");
        return new RegisterPage(driver);
    }

    @Step("Нажать ссылку «Восстановить пароль»")
    public ForgotPasswordPage clickForgotPasswordLink() {
        click(FORGOT_PASSWORD_LINK);
        waitForUrlContains("/forgot-password");
        return new ForgotPasswordPage(driver);
    }
}
