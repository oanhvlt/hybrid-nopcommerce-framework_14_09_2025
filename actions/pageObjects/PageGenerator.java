package pageObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.admin.AdminDashboardPO;
import pageObjects.admin.AdminLoginPO;
import pageObjects.user.*;

//gọi PageGenerator từ LoginPageObject,RegisterPageObject. HomePageObject, CustomerInfoPageObject

public class PageGenerator {
    //dùng static để có thể truy cập trực tiếp khi dùng class PageGenerator từ class khác

    public static UserHomePO getUserHomePage(WebDriver driver){
        return new UserHomePO(driver);
    }

    public static UserLoginP0 getUserLoginPage(WebDriver driver){
        return new UserLoginP0(driver);
    }

    public static UserRegisterPO getUserRegisterPage(WebDriver driver){
        return new UserRegisterPO(driver);
    }

    public static UserCustomerInfoPO getUserCustomerInfoPage(WebDriver driver){
        return new UserCustomerInfoPO(driver);
    }

    public static UserAddressPO getUserAddressPage(WebDriver driver){
        return new UserAddressPO(driver);
    }

    public static UserOrderPO getUserOrderPage(WebDriver driver){
        return new UserOrderPO(driver);
    }

    public static UserRewardPointPO getUserRewardPointPage(WebDriver driver){
        return new UserRewardPointPO(driver);
    }

    public static AdminLoginPO getAdminLoginPage(WebDriver driver){
        return new AdminLoginPO(driver);
    }

    public static AdminDashboardPO getAdminDashboardPage(WebDriver driver){
        return new AdminDashboardPO(driver);
    }

}
