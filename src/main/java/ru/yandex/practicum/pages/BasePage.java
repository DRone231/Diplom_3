package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public static final String BASE_URL = "https://stellarburgers.education-services.ru";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        var el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.DELETE);
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        return wait.until(d -> d.findElement(locator)).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(d -> d.findElement(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForUrlContains(String fragment) {
        wait.until(d -> d.getCurrentUrl().contains(fragment));
    }
}
