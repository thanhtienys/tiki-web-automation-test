package utils.common;


import driver.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitEx extends Config {

    public static void waitElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_CLICK_ABLE);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitElementToBeClickable(WebDriver driver, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_CLICK_ABLE);
        wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public static void waitElementToBeClickable(WebDriver driver, WebElement element, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitVisibilityOf(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_VISIBILITY);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitVisibilityOf(WebDriver driver, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_VISIBILITY);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static void waitVisibilityOf(WebDriver driver, By selector, Duration second) {
        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static void waitVisibilityOf(WebDriver driver, WebElement element, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitInvisibilityOf(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_INVISIBILITY);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitInvisibilityOf(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_INVISIBILITY);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitInvisibilityOf(WebDriver driver, WebElement element, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void presenceOfElementLocated(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_INVISIBILITY);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void presenceOfElementLocated(WebDriver driver, By locator, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public static void sleepLow() {
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
        }
    }

    public static void sleepMedium() {
        try {
            Thread.sleep(3000);
        } catch (Exception ignore) {
        }
    }

    public static void sleepHigh() {
        try {
            Thread.sleep(5000);
        } catch (Exception ignore) {
        }
    }

}
