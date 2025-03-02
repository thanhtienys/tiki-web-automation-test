package utils.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Optional;
import utils.common.WaitEx;

public class WebAction {

    private final WebDriver driver;

    public WebAction(WebDriver driver) {
        this.driver = driver;
    }

    public long getHeight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (long) js.executeScript("return document.body.scrollHeight");
    }

    public long getWidth() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (long) js.executeScript("return document.body.scrollWidth");
    }

    public void scrollDown(int ratio) {
        int locate = (int) (getHeight() / ratio);
        System.out.println(locate);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + locate + ")", "");
        WaitEx.sleepMedium();
    }

    public void scrollUp(int ratio) {
        int locate = (int) (getHeight() / ratio);
        System.out.println(locate);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + (-locate) + ")", "");
        WaitEx.sleepMedium();
    }

    public void scrollToLocation(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + "," + y + ")", "");
        WaitEx.sleepMedium();
    }

    public void scrollToElement(By selector) {
        WebElement element = driver.findElement(selector);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy()", element);
        WaitEx.sleepMedium();

    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy()", element);
        WaitEx.sleepMedium();

    }

    public static void clickWithAction(WebDriver driver, By selector) {
        Actions actions = new Actions(driver);
        actions.moveToElement(WebUI.findElement(driver, selector)).click().perform();
    }

    public static void clickAtPercentage(WebDriver driver, double percentX, double percentY, boolean isDouble) {

        // Get size window
        Dimension windowSize = driver.manage().window().getSize();
        int width = windowSize.getWidth();
        int height = windowSize.getHeight();

        // Calculate X, Y coordinates as % of input
        int xOffset = (int) (width * percentX);
        int yOffset = (int) (height * percentY);

        try {
            Actions actions = new Actions(driver);
            if (isDouble) {
                actions.moveByOffset(xOffset, yOffset).click().perform();
            } else {
                actions.moveByOffset(xOffset, yOffset).doubleClick().perform();
            }
        } catch (Exception e) {
            System.out.println("Can click: " + e.getMessage());
        }
    }

    public static void clickAboveSelector(WebDriver driver, By selector, int distance) {

        WebElement element = WebUI.findElement(driver, selector);

        Actions actions = new Actions(driver);
        int x = element.getLocation().getX();
        int y = element.getLocation().getY() - distance;
        try {
            actions.moveByOffset(x, y).click().perform();
        } catch (Exception e) {
            System.out.println("Can click: " + e.getMessage());
        }
    }

    public static void sendKeyEx(WebDriver driver, By selector, @Optional("value") String value) {
        if (value != null) {
            WebElement element = driver.findElement(selector);
            element.click();
            element.clear();
            element.sendKeys(value);
        }
    }

    public static void sendKeyEx(WebElement element, @Optional("value") String value) {
        if (value != null) {
            element.click();
            element.clear();
            element.sendKeys(value);
        }
        //TODO increase wait time to limit authentication requests
        WaitEx.sleepMedium();

    }


    public static void clickItem(WebDriver driver, By selector) {
        //TODO increase wait time to limit authentication requests
        WaitEx.sleepLow();
        WaitEx.waitElementToBeClickable(driver, selector);
        WebUI.findElement(driver, selector).click();
        //TODO increase wait time to limit authentication requests
        WaitEx.sleepMedium();
    }

}
