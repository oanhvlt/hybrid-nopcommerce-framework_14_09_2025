package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.user.SidebarPageUI;

public class SidebarPageObject extends BasePage {
    WebDriver driver;

    public SidebarPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public UserRewardPointPO openRewardPointPage() {
        waitForElementClickable(driver, SidebarPageUI.REWARD_POINT_LINK);
        clickToElem(driver, SidebarPageUI.REWARD_POINT_LINK);
        return PageGenerator.getUserRewardPointPage(driver);

    }

    public UserCustomerInfoPO openCustomerInfoPage() {
        waitForElementClickable(driver, SidebarPageUI.CUSTOMER_INFO_LINK);
        clickToElem(driver,SidebarPageUI.CUSTOMER_INFO_LINK);
        return PageGenerator.getUserCustomerInfoPage(driver);
    }

    public UserAddressPO openAddressPage() {
        waitForElementClickable(driver, SidebarPageUI.ADDRESS_LINK);
        clickToElem(driver, SidebarPageUI.ADDRESS_LINK);
        return PageGenerator.getUserAddressPage(driver);
    }

    public UserOrderPO openOrderPage() {
        waitForElementClickable(driver, SidebarPageUI.ORDER_LINK);
        clickToElem(driver,SidebarPageUI.ORDER_LINK);
        return PageGenerator.getUserOrderPage(driver);
    }
}
