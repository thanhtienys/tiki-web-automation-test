package models.components.home;

import models.components.Component;
import models.components.ComponentXpathSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentXpathSelector(value = "//div[@class='home-page']/header[@id='main-header']")
public class MainComp extends Component {

    public MainComp(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    By closeAdsBtnSel = By.xpath("//div[@id='VIP_BUNDLE']//picture[@class='webpimg-container']");

    public By getCloseAdsBtnSel() {
        return closeAdsBtnSel;
    }
}
