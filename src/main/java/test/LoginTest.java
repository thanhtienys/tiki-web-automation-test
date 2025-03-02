package test;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import test_flows.BaseFlow;
import test_flows.LoginFlow;

import static utils.common.GlobalConstants.AuthPageEnum.*;

public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    String emailDelete = "thanh.tien.ys@gmail.com";
    String email = "nguyenthibachhue9198@gmail.com";
    String phoneNumberDelete = "0898496380";
    String phoneNumber = "0383989198";
    String password = "Tester@123";

    @Test(description = "Test Login By Phone Number")
    public void testLoginByPhoneNumber() {

        //DATA TEST
        WebDriver driver = getDriver();

        BaseFlow baseFlow = new BaseFlow(driver);
        LoginFlow loginFlow = baseFlow.loginFlow();

        baseFlow.initPageAndCloseAds();
        baseFlow.moveToLoginScreen();

        Allure.step("=== LOGIN BY PHONE NUMBER ===");
        loginFlow.verifyUI(BY_PHONE, null);
        loginFlow.verifyInputPhoneNumber();
        loginFlow.clickButtonBack();

        Allure.step("=== LOGIN BY PHONE NUMBER DELETED ===");
        loginFlow.inputAndSubmit(phoneNumberDelete);
        loginFlow.clickBtnLoginBySMS();
        loginFlow.verifyErrorDeleteAccount();
        loginFlow.clickButtonBack();

        Allure.step("=== INPUT PASSWORD ===");
        loginFlow.inputAndSubmit(phoneNumber);
        loginFlow.verifyUI(PASSWORD, phoneNumber);
        loginFlow.verifyInputPassword(password);
        loginFlow.clickButtonBack();

        Allure.step("=== LOGIN BY EMAIL DELETED ===");
        loginFlow.moveToLoginByEmail();
        loginFlow.verifyUI(BY_EMAIL, null);
        loginFlow.verifyInputEmailAndPassword(emailDelete, password);
        loginFlow.verifyErrorDeleteAccount();
        loginFlow.clickButtonBack();

        Allure.step("=== LOGIN BY EMAIL ===");
        loginFlow.verifyInputEmailAndPassword(email, password);
    }

}
