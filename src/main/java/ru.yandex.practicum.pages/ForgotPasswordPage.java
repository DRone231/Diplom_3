package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private static final By LOGIN_LINK = By.xpath(".//a[contains(text(),'Войти')]");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу восстановления пароля")
    public ForgotPasswordPage open() {
        driver.get(BASE_URL + "/forgot-password");
        return this;
    }

    @Step("Нажать ссылку «Войти» на странице восстановления пароля")
    public LoginPage clickLoginLink() {
        click(LOGIN_LINK);
        waitForUrlContains("/login");
        return new LoginPage(driver);
    }
}
