package com.nopcommerce.users;

import commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.*;
import pageObjects.user.*;

//khởi tạo PO ở PageGenerator

public class Level_07_Switch_Page_Object extends BaseTest {

    //private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginP0 loginPage;
    private UserCustomerInfoPO customerInfoPage;
    private UserAddressPO addressPage;
    private UserOrderPO orderPage;
    private UserRewardPointPO rewardPointPage;

    //Tạo data test
    String firstName, lastName, email, companyName, password, day, month, year;

    //@Parameters("browser")
    @BeforeClass
    public void beforeClass(){ //String browserName
        //driver: có thể kế thừa từ BaseTest nếu ko khai báo
        //driver = getBrowserDriver(browserName);
        driver = getBrowserDriver("chrome");
        //khoi tao
        homePage = PageGenerator.getUserHomePage(driver);

        int num = generateRandomNumber();
        firstName ="Alice" + num;
        lastName="Vu";
        email="alice" + num + "@gmail.com" ;
        companyName="alice" + num + " LTD";
        password="123456";
        day="10";
        month="October";
        year="1988";
    }

    //Khởi tạo thể hiện trực tiếp trên test class
    @Test
    public void User_01_Register(){
        //Action 1: tại homepage => Từ HomePage qua registerPage và khởi tạo
        registerPage = homePage.openRegisterPage();
        //Action 2: tại registerPage
        registerPage.clickToMaleRadio();
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(email);
        registerPage.enterToCompanyNameTextbox(companyName);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        //dropdown
        //registerPage.selectDayDropdown("");
        registerPage.clickToRegisterButton();
        //=> van ở register page
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

    }
    @Test
    public void User_02_Login(){
        //về homepage
        registerPage.clickToLogoutLink();
        //Vào login page và khởi tạo
        loginPage =  registerPage.openLoginPage();
        //Actions:
        //Submit login => trở về homepage sau khi login thành công, khoi tao homepage
        homePage =  loginPage.loginToSystem(email, password);

        //verify: link My account hiển thị
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_MyAccount(){
        //Action tại homePage : move to qua myAccount page, khởi tạo
        customerInfoPage = homePage.openCustomerInfoPage();
        //verify:
        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), email);
        Assert.assertEquals(customerInfoPage.getCompanyNameTextboxValue(), companyName);
        //get dropdown value
        //Assert.assertEquals(customerInfoPage.getDayDropdownSelectedValue(), "");
    }

    @Test
    public void User_04_Switch_Page(){
        //Customer Info -> Address page
        addressPage = customerInfoPage.openAddressPage(driver);

        //
        rewardPointPage = addressPage.openRewardPointPage(driver);

        //
        orderPage = rewardPointPage.openOrderPage(driver);

        //
        addressPage = orderPage.openAddressPage(driver);

        //
        customerInfoPage = addressPage.openCustomerInfoPage(driver);

        addressPage = rewardPointPage.openAddressPage(driver);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
