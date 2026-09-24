package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private static final By NAME_INPUT = By.xpath(".//label[contains(text(),'Имя')]/following-sibling::input");
    private static final By EMAIL_INPUT = By.xpath(".//label[contains(text(),'Email')]/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath(".//label[contains(text(),'Пароль')]/following-sibling::input");
    private static final By REGISTER_SUBMIT = By.xpath(".//button[contains(text(),'Зарегистрироваться')]");
    private static final By LOGIN_LINK = By.xpath(".//a[contains(text(),'Войти')]");
    private static final By PASSWORD_ERROR = By.xpath(".//p[contains(@class,'input__error') and contains(text(),'пароль')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage open() {
        driver.get(BASE_URL + "/register");
        return this;
    }

    public RegisterPage typeName(String name) {
        type(NAME_INPUT, name);
        return this;
    }

    public RegisterPage typeEmail(String email) {
        type(EMAIL_INPUT, email);
        return this;
    }

    public RegisterPage typePassword(String password) {
        type(PASSWORD_INPUT, password);
        return this;
    }

    public Object clickRegisterButton() {
        click(REGISTER_SUBMIT);
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        if (driver.getCurrentUrl().contains("/login")) {
            return new LoginPage(driver);
        }
        return this;
    }

    public String getPasswordErrorText() {
        return getText(PASSWORD_ERROR);
    }

    public boolean isPasswordErrorVisible() {
        return isDisplayed(PASSWORD_ERROR);
    }

    public LoginPage clickLoginLink() {
        click(LOGIN_LINK);
        waitForUrlContains("/login");
        return new LoginPage(driver);
    }
}
