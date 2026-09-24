package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private static final By LOGIN_BUTTON = By.xpath(".//button[contains(text(),'Войти в аккаунт')]");
    private static final By PERSONAL_CABINET_LINK = By.xpath(".//a[@href='/account']");
    private static final By ORDER_BUTTON = By.xpath(".//button[contains(text(),'Оформить заказ')]");
    private static final By LOGO = By.xpath(".//div[contains(@class,'logo')]/a");
    private static final By CONSTRUCTOR_LINK = By.xpath(".//a[@href='/']");

    private static final By BUNS_TAB = By.xpath(".//span[contains(text(),'Булки')]/parent::div");
    private static final By SAUCES_TAB = By.xpath(".//span[contains(text(),'Соусы')]/parent::div");
    private static final By FILLINGS_TAB = By.xpath(".//span[contains(text(),'Начинки')]/parent::div");
    private static final By ACTIVE_TAB = By.xpath(".//div[contains(@class,'current')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public LoginPage clickLoginButton() {
        click(LOGIN_BUTTON);
        waitForUrlContains("/login");
        return new LoginPage(driver);
    }

    public Object clickPersonalCabinet() {
        click(PERSONAL_CABINET_LINK);
        if (driver.getCurrentUrl().contains("/login")) {
            return new LoginPage(driver);
        }
        return new AccountPage(driver);
    }

    public MainPage clickLogo() {
        click(LOGO);
        return this;
    }

    public MainPage clickConstructorLink() {
        click(CONSTRUCTOR_LINK);
        return this;
    }

    public MainPage clickBunsTab() {
        click(BUNS_TAB);
        return this;
    }

    public MainPage clickSaucesTab() {
        click(SAUCES_TAB);
        return this;
    }

    public MainPage clickFillingsTab() {
        click(FILLINGS_TAB);
        return this;
    }

    public String getActiveTabText() {
        return getText(ACTIVE_TAB);
    }

    public boolean isOrderButtonVisible() {
        return isDisplayed(ORDER_BUTTON);
    }

    public boolean isLoginButtonVisible() {
        return isDisplayed(LOGIN_BUTTON);
    }
}
