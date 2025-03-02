package test_flows;

import io.qameta.allure.Step;
import models.components.home.MainComp;
import models.components.home.TopMenuComp;
import models.pages.HomePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import utils.common.WaitEx;
import utils.ui.WebAction;

import static driver.Config.TIME_WAIT_DISPLAY_ADS;
import static utils.language.current.Language.TXT_1;

public class HomeFlow {

    private final WebDriver driver;
    private final TopMenuComp topMenuComp;
    private final MainComp mainComp;

    public HomeFlow(WebDriver driver) {
        this.driver = driver;
        HomePage homePage = new HomePage(driver);
        this.topMenuComp = homePage.topMenuComp();
        this.mainComp = homePage.mainComp();
    }

    @Step("[Home] Close Ads ")
    public void clickBtnCloseAds() {
        WaitEx.waitVisibilityOf(driver, mainComp.getCloseAdsBtnSel(), TIME_WAIT_DISPLAY_ADS);
        WebAction.clickAboveSelector(driver, mainComp.getCloseAdsBtnSel(), 80);
        try {
            WaitEx.waitInvisibilityOf(driver, mainComp.getCloseAdsBtnSel());
        } catch (TimeoutException ignore) {
            WebAction.clickAboveSelector(driver, mainComp.getCloseAdsBtnSel(), 80);
        }
    }

    @Step("[Home][TopMenu] click button <" + TXT_1 + ">")
    public void clickBtnAccount() {
        WebAction.clickItem(driver, topMenuComp.getAccountBtnSel());
    }
}
