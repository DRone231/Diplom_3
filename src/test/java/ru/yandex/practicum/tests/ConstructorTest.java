package ru.yandex.practicum.tests;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import ru.yandex.practicum.base.BaseTest;
import ru.yandex.practicum.pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

@Epic("Stellar Burgers UI")
@Feature("Конструктор")
@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {

    @Parameter
    public String browser;

    @Parameters(name = "Браузер: {0}")
    public static Collection<String> data() {
        return Arrays.asList("chrome", "yandex");
    }

    @Step("Перейти к разделу «Булки»")
    @Story("Переход к разделу «Булки»")
    @Test
    public void navigateToBuns() {
        MainPage mainPage = new MainPage(driver).open();

        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        Assert.assertEquals("Булки", mainPage.getActiveTabText());
    }

    @Step("Перейти к разделу «Соусы»")
    @Story("Переход к разделу «Соусы»")
    @Test
    public void navigateToSauces() {
        MainPage mainPage = new MainPage(driver).open();

        mainPage.clickSaucesTab();

        Assert.assertEquals("Соусы", mainPage.getActiveTabText());
    }

    @Step("Перейти к разделу «Начинки»")
    @Story("Переход к разделу «Начинки»")
    @Test
    public void navigateToFillings() {
        MainPage mainPage = new MainPage(driver).open();

        mainPage.clickFillingsTab();

        Assert.assertEquals("Начинки", mainPage.getActiveTabText());
    }
}
