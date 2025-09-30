package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageFactory extends BasePageF {
    private WebDriver driver;
    public RegisterPageFactory(WebDriver driver) {
        this.driver = driver;
        //initElements: xử lý luôn findElement
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='gender-male']")
    private WebElement genderMaleRadio;

    @FindBy(id = "FirstName")
    private WebElement firstNameTextbox;

    @FindBy(id = "LastName")
    private WebElement lastNameTextbox;

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordTextbox;

    @FindBy(id = "Company")
    private WebElement companyTextbox;

    @FindBy(xpath = "//button[@id='register-button']")
    private WebElement registerButton;

    @FindBy(className = "result")
    private WebElement registerSuccessMessage;

    @FindBy(className = "ico-logout")
    private WebElement logoutLink;

    @FindBy(className = "ico-login")
    private WebElement loginLink;
    
    public void clickToMaleRadio() {
        waitForElementClickable(driver, genderMaleRadio);
        clickToElem(genderMaleRadio);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, firstNameTextbox);
        sendKeyToElement(firstNameTextbox, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, lastNameTextbox);
        sendKeyToElement(lastNameTextbox, lastName);
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendKeyToElement(emailTextbox, emailAddress);
    }

    public void enterToCompanyNameTextbox(String companyName) {
        waitForElementVisible(driver, companyTextbox);
        sendKeyToElement(companyTextbox, companyName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(passwordTextbox, password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendKeyToElement(confirmPasswordTextbox, password);
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver,registerButton);
        clickToElem(registerButton);
    }

    public String getRegisterSuccessMessage() {
        waitForElementClickable(driver,registerSuccessMessage);
        return getElementText(registerSuccessMessage);
    }

    public void clickToLogoutLink() {
        waitForElementClickable(driver,logoutLink);
        clickToElem(logoutLink);
    }

    public void clickLoLoginLink() {
        waitForElementClickable(driver,loginLink);
        clickToElem(loginLink);
    }
}
