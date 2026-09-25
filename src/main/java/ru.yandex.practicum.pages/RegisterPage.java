package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {

    private static final By NAME_INPUT = By.xpath(".//label[contains(text(),'Имя')]/following-sibling::input");
    private static final By EMAIL_INPUT = By.xpath(".//label[contains(text(),'Email')]/following-sibling::input");
    private static final By PASSWORD_INPUT = By.xpath(".//label[contains(text(),'Пароль')]/following-sibling::input");
    private static final By REGISTER_SUBMIT = By.xpath(".//button[contains(text(),'Зарегистрироваться')]");
    private static final By LOGIN_LINK = By.xpath(".//a[contains(text(),'Войти')]");
    private static final By PASSWORD_ERROR = By.xpath(".//p[contains(@class,'input__error') and contains(text(),'пароль')]");
    private static final By ANY_ERROR = By.xpath(".//p[contains(@class,'input__error')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу регистрации")
    public RegisterPage open() {
        driver.get(BASE_URL + "/register");
        return this;
    }

    @Step("Ввести имя: {name}")
    public RegisterPage typeName(String name) {
        type(NAME_INPUT, name);
        return this;
    }

    @Step("Ввести email: {email}")
    public RegisterPage typeEmail(String email) {
        type(EMAIL_INPUT, email);
        return this;
    }

    @Step("Ввести пароль")
    public RegisterPage typePassword(String password) {
        type(PASSWORD_INPUT, password);
        return this;
    }

    @Step("Нажать кнопку «Зарегистрироваться»")
    public Object clickRegisterButton() {
        click(REGISTER_SUBMIT);

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.or(
                        ExpectedConditions.urlContains("/login"),
                        ExpectedConditions.visibilityOfElementLocated(ANY_ERROR)
                ));

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

    @Step("Нажать ссылку «Войти» на странице регистрации")
    public LoginPage clickLoginLink() {
        click(LOGIN_LINK);
        waitForUrlContains("/login");
        return new LoginPage(driver);
    }
}
