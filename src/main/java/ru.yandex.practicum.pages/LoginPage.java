package ru.yandex.practicum.pages;

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

    public LoginPage open() {
        driver.get(BASE_URL + "/login");
        return this;
    }

    public LoginPage typeEmail(String email) {
        type(EMAIL_INPUT, email);
        return this;
    }

    public LoginPage typePassword(String password) {
        type(PASSWORD_INPUT, password);
        return this;
    }

    public MainPage clickLoginButton() {
        click(LOGIN_SUBMIT);
        waitForUrlContains(BASE_URL + "/");
        return new MainPage(driver);
    }

    public MainPage login(String email, String password) {
        typeEmail(email);
        typePassword(password);
        return clickLoginButton();
    }

    public RegisterPage clickRegisterLink() {
        click(REGISTER_LINK);
        waitForUrlContains("/register");
        return new RegisterPage(driver);
    }

    public ForgotPasswordPage clickForgotPasswordLink() {
        click(FORGOT_PASSWORD_LINK);
        waitForUrlContains("/forgot-password");
        return new ForgotPasswordPage(driver);
    }
}
