package models.components.authentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.language.current.Language.TXT_9;

public class LoginComp {

    private final WebDriver driver;

    //? Area heading
    By closeBtnSel = By.className("btn-close");
    By backBtnSel = By.className("btn-action");
    By headingTxtSel = By.xpath("//div[@class='heading']/h4");
    By contentTxtSel = By.xpath("//div[@class='heading']/p");
    By valueOfContentTxtSel = By.xpath("//div[@class='heading']/p/b");

    //? Area right content
    By rightTitleTxtSel = By.xpath("//div[@class='content']/h4");
    By rightContentTxtSel = By.xpath("//div[@class='content']/span[text()='" + TXT_9 + "']");

    //? Area input and submit
    By inputSel = By.xpath("//form//input");
    By continueBtnSel = By.xpath("//form//button");
    By errorMsgSel = By.xpath("//form/span[@class='error-mess']");

    //? Login by phone number
    By loginWithEmailSel = By.className("login-with-email");
    By orContinueByTxtSel = By.xpath("//p[@class='social-heading']/span");
    By socialItemSel = By.className("social__items");
    By termOfUserSel = By.className("note");

    //? Login by phone number > Input password
    By forgotPassSel = By.className("forgot-pass");
    By loginBySMSSel = By.className("login-with-sms");
    By createAccountSel = By.className("create-account");

    public LoginComp(WebDriver driver) {
        this.driver = driver;
    }

    public By getRightTitleTxtSel() {
        return rightTitleTxtSel;
    }

    public By getRightContentTxtSel() {
        return rightContentTxtSel;
    }

    public By getCloseBtnSel() {
        return closeBtnSel;
    }

    public By getBackBtnSel() {
        return backBtnSel;
    }

    public By getHeadingTxtSel() {
        return headingTxtSel;
    }

    public By getContentTxtSel() {
        return contentTxtSel;
    }

    public By getValueOfContentTxtSel() {
        return valueOfContentTxtSel;
    }

    public By getInputSel() {
        return inputSel;
    }

    public By getContinueBtnSel() {
        return continueBtnSel;
    }

    public By getErrorMsgSel() {
        return errorMsgSel;
    }

    public By getLoginWithEmailSel() {
        return loginWithEmailSel;
    }

    public By getOrContinueByTxtSel() {
        return orContinueByTxtSel;
    }

    public By getSocialItemSel() {
        return socialItemSel;
    }

    public By getTermOfUserSel() {
        return termOfUserSel;
    }

    public By getForgotPassSel() {
        return forgotPassSel;
    }

    public By getLoginBySMSSel() {
        return loginBySMSSel;
    }

    public By getCreateAccountSel() {
        return createAccountSel;
    }
}
