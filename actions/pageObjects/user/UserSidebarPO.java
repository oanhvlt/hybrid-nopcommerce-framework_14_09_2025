package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.user.SidebarPageUI;

public class UserSidebarPO extends BasePage {
    WebDriver driver;

    public UserSidebarPO(WebDriver driver) {
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

    public UserSidebarPO openSidebarLinkByPageNameAndReturn(String className, String pageName){
        waitForElementClickable(driver, SidebarPageUI.DYNAMIC_LINK_BY_CLASS_NAME_PAGE_NAME, className, pageName);
        clickToElem(driver,SidebarPageUI.DYNAMIC_LINK_BY_CLASS_NAME_PAGE_NAME, className, pageName);

        if(className.equals("customer-addresses") && pageName.equals("Addresses")){
            return PageGenerator.getUserAddressPage(driver);
        }else if(className.equals("reward-points") && pageName.equals("Reward points")){
            return PageGenerator.getUserRewardPointPage(driver);
        }else if(className.equals("customer-info") && pageName.equals("Customer info")){
            return PageGenerator.getUserCustomerInfoPage(driver);
        }else if(className.equals("customer-orders") && pageName.equals("Orders")){
            return PageGenerator.getUserOrderPage(driver);
        }else {
            throw new RuntimeException("Page not found");
        }
    }

    public void openSidebarLinkByPageNames(String className, String pageName){
        waitForElementClickable(driver, SidebarPageUI.DYNAMIC_LINK_BY_CLASS_NAME_PAGE_NAME, className, pageName);
        clickToElem(driver,SidebarPageUI.DYNAMIC_LINK_BY_CLASS_NAME_PAGE_NAME, className, pageName);
    }

}
