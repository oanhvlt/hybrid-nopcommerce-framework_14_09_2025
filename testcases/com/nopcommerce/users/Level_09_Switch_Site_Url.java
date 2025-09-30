package com.nopcommerce.users;

import commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.admin.AdminDashboardPO;
import pageObjects.admin.AdminLoginPO;
import pageObjects.user.UserHomePO;
import pageObjects.user.UserLoginP0;
import pageObjects.user.UserRegisterPO;

//khởi tạo PO ở PageGenerator
//Kế thừa BaseTest, không cần khởi tao driver
public class Level_09_Switch_Site_Url extends BaseTest {

    private String userURL, adminURL;
    //private String url = "https://demo.nopcommerce.com/";

    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private UserLoginP0 userLoginPage;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;

    //Tạo data test
    String firstName, lastName, email, companyName, password, day, month, year;
    String emailAdmin = "admin@yourstore.com", passwordAdmin = "admin";

    @Parameters({"browser", "userURL", "adminURL"})
    @BeforeClass
    public void beforeClass(String browserName, String userURL, String adminURL){
        this.userURL = userURL;
        this.adminURL = adminURL;

        driver = getBrowserDriver(browserName, this.userURL);
        userHomePage = PageGenerator.getUserHomePage(driver);

        int num = generateRandomNumber();
        firstName ="Alice" + num;
        lastName="Vu";
        email="alice" + num + "@gmail.com" ;
        companyName="alice" + num + " LTD";
        password="123456";
        day="10";
        month="October";
        year="1988";

        //pre-condition
        register();
    }

    public void register(){
        //Action 1: tại homepage => Từ HomePage qua registerPage và khởi tạo
        userRegisterPage = userHomePage.openRegisterPage();
        //Action 2: tại registerPage
        userRegisterPage.clickToMaleRadio();
        userRegisterPage.enterToFirstNameTextbox(firstName);
        userRegisterPage.enterToLastNameTextbox(lastName);
        userRegisterPage.enterToEmailTextbox(email);
        userRegisterPage.enterToCompanyNameTextbox(companyName);
        userRegisterPage.enterToPasswordTextbox(password);
        userRegisterPage.enterToConfirmPasswordTextbox(password);
        //dropdown
        //registerPage.selectDayDropdown("");
        userRegisterPage.clickToRegisterButton();
        //=> van ở register page
        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
    }

    public void userLogin(){
        //về homepage
        userRegisterPage.clickToLogoutLink();
        //Vào login page và khởi tạo
        userLoginPage =  userRegisterPage.openLoginPage();
        //Submit login => trở về homepage sau khi login thành công, khoi tao homepage
        userHomePage =  userLoginPage.loginToSystem(email, password);
        //verify: link My account hiển thị
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
    }

    //Khởi tạo thể hiện trực tiếp trên test class
    @Test
    public void Role_01_User_Site_To_Admin_Site(){
        //userLogin();
        //order
        //....
        //move Admin page
        userHomePage.openPageUrl(driver, this.adminURL);
        //Chưa login
        adminLoginPage = PageGenerator.getAdminLoginPage(driver);
        //login admin account
        //adminDashboardPage = adminLoginPage.clickToLoginButton();
        adminDashboardPage = adminLoginPage.loginSystem(emailAdmin, passwordAdmin);
        adminDashboardPage.sleepInSecond(1);
    }

    @Test
    public void Role_02_Admin_Site_To_User_Site(){
        //Vào trang bất kỳ: Order/Customer/....

        adminDashboardPage.openPageUrl(driver, this.userURL);
        userHomePage = PageGenerator.getUserHomePage(driver);

        //step bất kỳ tại user homepage

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
