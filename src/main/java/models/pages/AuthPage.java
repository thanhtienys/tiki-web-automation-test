package models.pages;


import models.components.authentication.LoginComp;
import org.openqa.selenium.WebDriver;


public class AuthPage {

    private final WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginComp loginComp() {
        return new LoginComp(driver);
    }
}
