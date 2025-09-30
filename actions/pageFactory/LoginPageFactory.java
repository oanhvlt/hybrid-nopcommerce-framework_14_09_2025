package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory extends BasePageF {
    private WebDriver driver;
    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        //initElements: xử lý luôn findElement
        PageFactory.initElements(driver, this);
    }
    //@CacheLookup
    @FindBy(id = "Email")
    private WebElement emailTextbox;
    @FindBy(id = "Password")
    private WebElement passwordTextbox;
    @FindBy(xpath = "//button[@class='button-1 login-button']")
    private WebElement loginButton;

    public void enterToEmailTextbox(String emailAddress) {
       waitForElementVisible(driver, emailTextbox);
       sendKeyToElement(emailTextbox, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(passwordTextbox, password);
    }


    public void clickToLoginButton() {
        waitForElementClickable(driver,loginButton);
        clickToElem(loginButton);
    }

    public void loginToSystem(String emailAddress, String password){
        enterToEmailTextbox(emailAddress);
        enterToPasswordTextbox(password);
        clickToLoginButton();
    }
}









