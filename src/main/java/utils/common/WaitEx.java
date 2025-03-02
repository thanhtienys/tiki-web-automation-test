package utils.common;


import driver.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitEx extends Config {


    public static void waitElementToBeClickable(WebDriver driver, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_CLICK_ABLE);
        wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public static void waitVisibilityOf(WebDriver driver, By selector, Duration second) {
        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static void waitInvisibilityOf(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_INVISIBILITY);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
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
