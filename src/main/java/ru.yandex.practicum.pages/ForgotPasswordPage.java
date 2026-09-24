package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private static final By LOGIN_LINK = By.xpath(".//a[contains(text(),'Войти')]");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public ForgotPasswordPage open() {
        driver.get(BASE_URL + "/forgot-password");
        return this;
    }

    public LoginPage clickLoginLink() {
        click(LOGIN_LINK);
        waitForUrlContains("/login");
        return new LoginPage(driver);
    }
}
