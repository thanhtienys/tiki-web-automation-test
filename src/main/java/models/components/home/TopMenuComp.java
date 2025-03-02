package models.components.home;

import models.components.Component;
import models.components.ComponentXpathSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentXpathSelector(value = "//div[@class='home-page']/header[@id='main-header']")
public class TopMenuComp extends Component {

    By accountBtnSel = By.xpath("//*[@data-view-id='header_header_account_container']");

    public TopMenuComp(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public By getAccountBtnSel() {
        return accountBtnSel;
    }
}
