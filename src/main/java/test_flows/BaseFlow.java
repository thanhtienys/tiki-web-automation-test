package test_flows;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utils.common.Urls;
import utils.common.WaitEx;

public class BaseFlow {

    private final WebDriver driver;

    public BaseFlow(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Init page and close Ads")
    public void initPageAndCloseAds() {
        driver.get(Urls.BASE);
        homeFlow().clickBtnCloseAds();
        WaitEx.sleepLow();
    }

    @Step("[HOME] Move to [Login] screen")
    public void moveToLoginScreen() {
        homeFlow().clickBtnAccount();
    }

    public HomeFlow homeFlow() {
        return new HomeFlow(driver);
    }

    public LoginFlow loginFlow() {
        return new LoginFlow(driver);
    }
}

