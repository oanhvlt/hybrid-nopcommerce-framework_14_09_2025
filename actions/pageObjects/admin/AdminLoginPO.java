package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
    WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        clearTextInputElement(driver, AdminLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        //sleepInSecond(1);
        sendKeyToElement(driver, AdminLoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        clearTextInputElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        //sleepInSecond(1);
        sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public AdminDashboardPO clickToLoginButton() {
        clickToElem(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getAdminDashboardPage(driver);
    }

    public AdminDashboardPO loginSystem(String email, String password) {
        enterToEmailTextbox(email);
        enterToPasswordTextbox(password);
        return clickToLoginButton();
    }


}
