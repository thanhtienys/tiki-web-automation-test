package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory extends Config {

    private WebDriver driver;

    public WebDriver getDriver(BrowserName browserName) {
        boolean isRemoteRunning = System.getProperty("hub") != null;
        if (isRemoteRunning) return getRemoteWebDriver(browserName, System.getProperty("hub"));
        else return getLocalDriver(browserName);
    }

    private WebDriver getLocalDriver(BrowserName browserName) {
        if (driver == null) {
            switch (browserName) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                    chromeOptions.addArguments("--disable-infobars"); // Tắt thanh thông tin tự động hóa
                    chromeOptions.addArguments("--disable-popup-blocking"); // Tắt chặn popup
                    chromeOptions.addArguments("--incognito");
                    Map<String, Object> prefs = new HashMap<>();

                    // Permission location 0 = Block, 1 = Ask, 2 =  Allow
                    prefs.put("profile.default_content_setting_values.geolocation", 2);
                    chromeOptions.setExperimentalOption("prefs", prefs);

                    driver = new ChromeDriver(chromeOptions);
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();

                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case SAFARI:
                    WebDriverManager.safaridriver().setup();

                    SafariOptions safariOptions = new SafariOptions();

                    driver = new SafariDriver(safariOptions);
                default:
                    throw new IllegalArgumentException(browserName + " is not supported!");
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(TIMEOUT_GET_ELEMENT);
        }
        return driver;
    }

    private WebDriver getRemoteWebDriver(BrowserName browserName, String hub) {
        if (driver == null) {
            try {
                URL hubUrl = new URL(hub.concat("/wd/hub"));

                switch (browserName) {
                    case CHROME:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        driver = new RemoteWebDriver(hubUrl, chromeOptions);
                        break;
                    case FIREFOX:
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver = new RemoteWebDriver(hubUrl, firefoxOptions);
                        break;
                    case SAFARI:
                        SafariOptions safariOptions = new SafariOptions();
                        driver = new RemoteWebDriver(hubUrl, safariOptions);
                        break;
                    default:
                        throw new IllegalArgumentException(browserName + " is not supported!");
                }

                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(TIMEOUT_GET_ELEMENT);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to create RemoteWebDriver", e);
            }
        }
        return driver;
    }

    public void closeBrowserSession() {
        if (driver != null) {
            driver.quit();
        }
    }

}