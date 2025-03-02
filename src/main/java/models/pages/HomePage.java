package models.pages;

import models.components.Component;
import models.components.home.MainComp;
import models.components.home.TopMenuComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Component {

    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;
    }

    public TopMenuComp topMenuComp() {
        return findComponent(TopMenuComp.class, driver);
    }

    public MainComp mainComp() {
        return findComponent(MainComp.class, driver);
    }
}
