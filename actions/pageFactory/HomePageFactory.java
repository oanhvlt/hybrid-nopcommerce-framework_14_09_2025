package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageFactory extends BasePageF {
    private WebDriver driver;

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        //initElements: xử lý luôn findElement
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='ico-register']")//tìm theo @class của elem
    private WebElement registerLink;

    @FindBy(xpath = "//a[@class='ico-account']")
    private WebElement myAccountLink;


    public void clickToRegisterLink() {
        waitForElementClickable(driver,registerLink);
        clickToElem(registerLink);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver,myAccountLink);
        return isElementDisplayed(myAccountLink);
    }

    public void clickToMyAccountLink() {
        waitForElementClickable(driver,myAccountLink);
        clickToElem(myAccountLink);
    }
}
