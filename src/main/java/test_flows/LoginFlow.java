package test_flows;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import models.components.authentication.LoginComp;
import models.pages.AuthPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.asserts.SoftAssert;
import utils.common.GlobalConstants.AuthPageEnum;
import utils.common.GlobalConstants.ErrorType;
import utils.common.RandomEx;
import utils.common.WaitEx;
import utils.ui.WebAction;
import utils.ui.WebUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static utils.language.current.Language.*;


public class LoginFlow {

    private final WebDriver driver;
    private final LoginComp loginComp;

    public LoginFlow(WebDriver driver) {
        this.driver = driver;
        AuthPage authPage = new AuthPage(driver);
        this.loginComp = authPage.loginComp();
    }

    @Step("Verify UI of {0}")
    public void verifyUI(AuthPageEnum authPage, @Optional("phoneNumber") String phoneNumber) {

        SoftAssert softAssert = new SoftAssert();

        List<String> expectForm = new ArrayList<>(4);

        WebUI.checkButtonEnable(driver, softAssert, loginComp.getCloseBtnSel(), "");

        switch (authPage) {
            case BY_PHONE -> {
                Collections.addAll(expectForm, TXT_3, TXT_4, TXT_18, TXT_2);
                WebUI.checkTextStr(driver, softAssert, loginComp.getLoginWithEmailSel(), TXT_16);
                WebUI.checkTextStr(driver, softAssert, loginComp.getOrContinueByTxtSel(), TXT_5);
            }
            case PASSWORD -> {
                WebUI.checkButtonEnable(driver, softAssert, loginComp.getBackBtnSel(), "");
                Collections.addAll(expectForm, TXT_13, TXT_14, TXT_15, TXT_10);
                WebUI.checkTextStr(driver, softAssert, loginComp.getValueOfContentTxtSel(), phoneNumber);
                WebUI.checkTextStr(driver, softAssert, loginComp.getForgotPassSel(), TXT_11);
                WebUI.checkTextStr(driver, softAssert, loginComp.getLoginBySMSSel(), TXT_12);
            }
            case BY_EMAIL -> {
                List<WebElement> webElementList = WebUI.findElements(driver, loginComp.getInputSel());
                WebUI.checkButtonEnable(driver, softAssert, loginComp.getBackBtnSel(), "");
                Collections.addAll(expectForm, TXT_19, TXT_20, TXT_21, TXT_10);
                WebUI.checkTextStr(driver, softAssert, webElementList.get(1), TXT_15);
                WebUI.checkTextStr(driver, softAssert, loginComp.getForgotPassSel(), TXT_11);
            }

        }

        WebUI.checkTextStr(driver, softAssert, loginComp.getHeadingTxtSel(), expectForm.get(0));
        WebUI.checkTextStr(driver, softAssert, loginComp.getContentTxtSel(), expectForm.get(1));
        WebUI.checkPlaceholderStr(driver, softAssert, loginComp.getInputSel(), expectForm.get(2));
        WebUI.checkButtonEnable(driver, softAssert, loginComp.getContinueBtnSel(), expectForm.get(3));

        WebUI.checkTextStr(driver, softAssert, loginComp.getRightTitleTxtSel(), TXT_8);
        WebUI.checkTextStr(driver, softAssert, loginComp.getRightContentTxtSel(), TXT_9);


        softAssert.assertAll();
    }

    @Step("[Login By Phone Number] Move to screen [Login By Email]")
    public void moveToLoginByEmail() {
        WebAction.clickItem(driver, loginComp.getLoginWithEmailSel());
    }

    @Step(" Click button <Back>")
    public void clickButtonBack() {
        WebAction.clickItem(driver, loginComp.getBackBtnSel());
    }

    @Step("[Input Password] Click button <Login By SMS>")
    public void clickBtnLoginBySMS() {
        WebAction.clickItem(driver, loginComp.getLoginBySMSSel());
    }

    @Step("[Login By Phone Number] Verify invalid phone number")
    public void verifyInputPhoneNumber() {

        SoftAssert softAssert = new SoftAssert();
        Random random = new Random();

        Allure.step("Not input");
        checkErrorInputPhoneNumber(softAssert, ErrorType.REQUIRED, null);

        Allure.step("Not a positive integer");
        checkErrorInputPhoneNumber(softAssert, ErrorType.NOT_DISPLAY, RandomEx.randomStringNotNumber(random.nextInt(9) + 1));
        checkErrorInputPhoneNumber(softAssert, ErrorType.NOT_DISPLAY, RandomEx.randomSpecialCharacter(random.nextInt(9) + 1));

        Allure.step("Less than 10 numbers | Number under 4 digits");
        for (int i = 1; i < 4; i++) {
            checkErrorInputPhoneNumber(softAssert, ErrorType.INVALID, RandomEx.randomNumber(i));
        }
        Allure.step("Less than 10 numbers | Phone number valid under from 4 to 9 digits");
        for (int i = 4; i < 8; i++) {
            checkErrorInputPhoneNumber(softAssert, ErrorType.INVALID, RandomEx.generateRandomPhone(i, true));
        }
        checkErrorInputPhoneNumber(softAssert, ErrorType.INVALID, RandomEx.generateRandomPhone(8, true));

        Allure.step("Less than 10 numbers | Phone number invalid 10 digits");
        checkErrorInputPhoneNumber(softAssert, ErrorType.INVALID, RandomEx.generateRandomPhone(9, false));

        Allure.step("Max length phone number greater than 10 digits");
        checkErrorInputPhoneNumber(softAssert, ErrorType.MAX_LENGTH, RandomEx.generateRandomPhone(12, true));

        softAssert.assertAll();
    }

    @Step("[Login By Phone Number] > [Input Password] verify input password")
    public void verifyInputPassword(String password) {

        SoftAssert softAssert = new SoftAssert();

        Allure.step("Not input");
        checkErrorInputPassword(softAssert, ErrorType.REQUIRED, null);

        Allure.step("Wrong password");
        for (String invalidPass : RandomEx.randomPassword(password)) {
            checkErrorInputPassword(softAssert, ErrorType.INVALID, invalidPass);
        }

        softAssert.assertAll();

    }

    @Step("[Login By Email] > Verify input email and password")
    public void verifyInputEmailAndPassword(String email, String password) {

        SoftAssert softAssert = new SoftAssert();

        Allure.step("Not input");
        checkErrorInputEmailAndPassword(softAssert, ErrorType.REQUIRED, null, null);
        checkErrorInputEmailAndPassword(softAssert, ErrorType.REQUIRED, email, null);
        checkErrorInputEmailAndPassword(softAssert, ErrorType.REQUIRED, null, password);

        Allure.step("Wrong password");
        for (String invalidPass : RandomEx.randomPassword(password)) {
            checkErrorInputEmailAndPassword(softAssert, ErrorType.INVALID, email, invalidPass);
        }
        softAssert.assertAll();

    }

    @Step("[Login By Email] > Input email {3} , password {4} and verify with expect {2}")
    private void checkErrorInputEmailAndPassword(SoftAssert softAssert, ErrorType errorType, String email, String password) {
        moveToLoginByEmail();
        inputEmailPasswordAndSubmit(email, password);

        switch (errorType) {
            case INVALID ->
                    softAssert.assertEquals(WebUI.getText(driver, loginComp.getErrorMsgSel()), TXT_24, "[ERR] Err display incorrect");
            case REQUIRED -> {
                if (email == null && password == null) {
                    softAssert.assertEquals(WebUI.getText(WebUI.findElements(driver, loginComp.getErrorMsgSel()).get(0)), TXT_23, "[ERR] Err display incorrect");
                    softAssert.assertEquals(WebUI.getText(WebUI.findElements(driver, loginComp.getErrorMsgSel()).get(1)), TXT_22, "[ERR] Err display incorrect");
                } else if (email != null) {
                    softAssert.assertEquals(WebUI.getText(driver, loginComp.getErrorMsgSel()), TXT_22, "[ERR] Err display incorrect");
                } else {
                    softAssert.assertEquals(WebUI.getText(driver, loginComp.getErrorMsgSel()), TXT_23, "[ERR] Err display incorrect");
                }
            }
            default -> softAssert.fail(" ErrorType of [Input Password] must be INVALID, REQUIRED");
        }
        clickButtonBack();
    }

    @Step("Verify deleted account")
    public void verifyErrorDeleteAccount() {
        Assert.assertEquals(WebUI.getText(driver, loginComp.getErrorMsgSel()), TXT_25, "[ERR] Err display incorrect");
    }

    @Step("[Login By Phone Number] > [Input Password] Input value {3} and verify with expect {2}")
    private void checkErrorInputPassword(SoftAssert softAssert, ErrorType errorType, String value) {
        inputAndSubmit(value);
        switch (errorType) {
            case INVALID ->
                    softAssert.assertEquals(WebUI.getText(driver, loginComp.getErrorMsgSel()), TXT_26, "[ERR] Err display incorrect");
            case REQUIRED ->
                    softAssert.assertEquals(WebUI.getText(driver, loginComp.getErrorMsgSel()), TXT_22, "[ERR] Err display incorrect");
            default -> softAssert.fail(" ErrorType of [Input Password] must be INVALID, REQUIRED");
        }
    }

    @Step("[Login By Phone Number] Input value {3} and verify with expect {2}")
    private void checkErrorInputPhoneNumber(SoftAssert softAssert, ErrorType errorType, String value) {
        inputAndSubmit(value);
        switch (errorType) {
            case NOT_DISPLAY ->
                    softAssert.assertEquals(WebUI.getValue(driver, loginComp.getInputSel()), "", "[ERR] Value {0} still display");
            case INVALID ->
                    softAssert.assertEquals(WebUI.getText(driver, loginComp.getErrorMsgSel()), TXT_7, "[ERR] Err display incorrect");
            case REQUIRED ->
                    softAssert.assertEquals(WebUI.getText(driver, loginComp.getErrorMsgSel()), TXT_6, "[ERR] Err display incorrect");
            case MAX_LENGTH ->
                    softAssert.assertEquals(WebUI.getValue(driver, loginComp.getInputSel()), value.substring(0, 10), "[ERR] Value {0} still display");
            default ->
                    softAssert.fail(" ErrorType of [Input Phone Number] must be NOT_DISPLAY, INVALID, REQUIRED, MAX_LENGTH");
        }
    }

    @Step("Input value {0} and submit")
    public void inputAndSubmit(String value) {
        WebAction.sendKeyEx(driver, loginComp.getInputSel(), value);
        WebAction.clickItem(driver, loginComp.getContinueBtnSel());
        WaitEx.sleepMedium();
    }

    @Step("Input value email {0}, password {1} and submit")
    public void inputEmailPasswordAndSubmit(String email, String password) {

//        if (email != null && password == null) {
//            WebAction.sendKeyEx(WebUI.findElements(driver, loginComp.getInputSel()).get(0), email);
//            WebAction.clearValueEx(WebUI.findElements(driver, loginComp.getInputSel()).get(1));
//        } else if (email == null && password != null) {
//            WebAction.clearValueEx(WebUI.findElements(driver, loginComp.getInputSel()).get(0));
//            WebAction.sendKeyEx(WebUI.findElements(driver, loginComp.getInputSel()).get(1), password);
//        } else {
        WebAction.sendKeyEx(WebUI.findElements(driver, loginComp.getInputSel()).get(0), email);
        WebAction.sendKeyEx(WebUI.findElements(driver, loginComp.getInputSel()).get(1), password);
//        }
        WebAction.clickItem(driver, loginComp.getContinueBtnSel());
    }

}
