package test;

import driver.Config;
import driver.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest extends Config {

    private final static List<DriverFactory> webdriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String browserName;

    protected WebDriver getDriver() {
        return driverThread.get().getDriver(BrowserName.valueOf(browserName));
    }

    @BeforeTest
    @Parameters({"browser"})
    public void initBrowserSession(String browserName) {
        this.browserName = browserName;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory webdriverThread = new DriverFactory();
            webdriverThreadPool.add(webdriverThread);
            return webdriverThread;
        });
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowserSession() {
        driverThread.get().closeBrowserSession();
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // testMethodName-yyyy-m-dd-hr-mm-sec.png

            // 1. Get method name
            String methodName = result.getName();

            // 2. Get Taken time
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String filename = methodName + "-" + y + "-" + m + "-" + d + "-" + hr + "-" + min + "-" + sec + ".png";

            // 3. Take Screenshot
            File screenshotBase64Data = ((TakesScreenshot) driverThread.get().getDriver(BrowserName.valueOf(browserName))).getScreenshotAs(OutputType.FILE);

            try {

                // 4. Save
                String fileLocation = System.getProperty("user.dir") + "/screenshots/" + filename;
                FileUtils.copyFile(screenshotBase64Data, new File(fileLocation));

                // 5. Attach to report
                Path content = Paths.get(fileLocation);
                try (InputStream inputStream = Files.newInputStream(content)) {
                    Allure.addAttachment(methodName, inputStream);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}