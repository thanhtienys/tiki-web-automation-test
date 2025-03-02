package utils.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import utils.common.WaitEx;

import java.util.List;

public class WebUI extends WaitEx {

    public static WebElement findElement(WebDriver driver, By selector) {
        return driver.findElement(selector);
    }

    public static List<WebElement> findElements(WebDriver driver, By selector) {
        return driver.findElements(selector);
    }


    public static String getPlaceHolder(WebDriver driver, By selector) {
        return findElement(driver, selector).getAttribute("placeholder").trim();
    }

    public static String getText(WebDriver driver, By selector) {
        return findElement(driver, selector).getText().trim();
    }

    public static String getText(WebElement element) {
        return element.getText().trim();
    }

    public static String getValue(WebDriver driver, By selector) {
        return findElement(driver, selector).getAttribute("value").trim();
    }

    public static String getValue(WebElement element) {
        return element.getAttribute("value").trim();
    }

    public static String getPlaceholder(WebDriver driver, By selector) {
        return findElement(driver, selector).getAttribute("placeholder").trim();
    }


    @Step("Check text of selector : {selector}")
    public static void checkTextStr(WebDriver driver, SoftAssert softAssert, By selector, String expectedStr) {
        softAssert.assertEquals(getText(driver, selector), expectedStr, "[ERR] Text not match");
    }

    @Step("Check text of selector : {selector}")
    public static void checkTextStr(WebDriver driver, SoftAssert softAssert, WebElement element, String expectedStr) {
        softAssert.assertEquals(getText(element), expectedStr, "[ERR] Text not match");
    }

    @Step("Check value of selector: {selector}")
    public static void checkValueStr(WebDriver driver, SoftAssert softAssert, By selector, String expectedStr) {
        softAssert.assertEquals(getValue(driver, selector), expectedStr, "[ERR] Text not match");
    }

    @Step("Check placeholder of selector: {selector}")
    public static void checkPlaceholderStr(WebDriver driver, SoftAssert softAssert, By selector, String expectedStr) {
        softAssert.assertEquals(getPlaceholder(driver, selector), expectedStr, "[ERR] Text not match");
    }

    @Step("Check button enable: {element}")
    public static void checkButtonEnable(WebDriver driver, SoftAssert softAssert, By selector, String expectedStr) {
        WebElement element = findElement(driver, selector);
        softAssert.assertTrue(element.isEnabled(), "[ERR] Button is not enable");
        softAssert.assertEquals(getText(element), expectedStr, "[ERR] Text not match");
    }

    @Step("Check button disable: {element}")
    public static void checkButtonDisable(WebDriver driver, SoftAssert softAssert, By selector, String expectedStr) {
        WebElement element = findElement(driver, selector);
        softAssert.assertFalse(element.isEnabled(), "[ERR] Button is not disable");
        softAssert.assertEquals(getText(element), expectedStr, "[ERR] Text not match");
    }

    public static By buttonContainsText(String value) {
        return By.xpath("//button[contains(text(),'" + value + "')]");
    }

    public static By buttonContainsClass(String value) {
        return By.xpath("//button[contains(@class,'" + value + "')]");
    }

    public static By spanContainsText(String value) {
        return By.xpath("//span[contains(text(),'" + value + "')]");
    }

    public static By spanContainsClass(String value) {
        return By.xpath("//span[contains(@class,'" + value + "')]");
    }

    public static By containsText(String value) {
        return By.xpath("//*[contains(text(),'" + value + "')]");
    }
}
