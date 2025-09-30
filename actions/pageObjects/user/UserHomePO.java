package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.user.HomePageUI;

public class UserHomePO extends BasePage {
    private WebDriver driver;

    //contructor
    //1. cùng tên class
    //2. không kiểu trả về
    //3. chạy đầu tiên khi class được gọi (new HomePageObject)
    //4. Có tham số hoặc không
    //5. nếu ko tự define constructor => JVM sẽ mặc định tạo ra 1 hàm khởi tạo rỗng:   public HomePageObject() { }
    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPO openRegisterPage() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElem(driver, HomePageUI.REGISTER_LINK);
        return PageGenerator.getUserRegisterPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver,HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver,HomePageUI.MY_ACCOUNT_LINK);
    }

    public UserCustomerInfoPO openCustomerInfoPage() {
        waitForElementClickable(driver,HomePageUI.MY_ACCOUNT_LINK);
        clickToElem(driver,HomePageUI.MY_ACCOUNT_LINK);
        return PageGenerator.getUserCustomerInfoPage(driver);
    }


}
