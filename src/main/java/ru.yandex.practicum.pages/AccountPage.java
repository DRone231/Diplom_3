package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    private static final By LOGOUT_BUTTON = By.xpath(".//button[contains(text(),'Выход')]");
    private static final By PROFILE_TEXT = By.xpath(".//a[contains(text(),'Профиль')]");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage open() {
        driver.get(BASE_URL + "/account");
        return this;
    }

    public void clickLogoutButton() {
        click(LOGOUT_BUTTON);
        waitForUrlContains("/login");
    }

    public boolean isProfileDisplayed() {
        return isDisplayed(PROFILE_TEXT);
    }
}
