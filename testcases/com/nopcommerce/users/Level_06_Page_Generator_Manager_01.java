package com.nopcommerce.users;

import commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.UserCustomerInfoPO;
import pageObjects.user.UserHomePO;
import pageObjects.user.UserLoginP0;
import pageObjects.user.UserRegisterPO;


// khoi tao PO ở tầng test class

public class Level_06_Page_Generator_Manager_01 extends BaseTest {

    //private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginP0 loginPage;
    private UserCustomerInfoPO customerInfoPage;

    //Tạo data test
    String firstName, lastName, email, companyName, password, day, month, year;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        //driver: có thể kế thừa từ BaseTest nếu ko khai báo
        driver = getBrowserDriver(browserName);
        //khoi tao
        homePage = new UserHomePO(driver);

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
        //Action 1: tại homepage
        homePage.openRegisterPage();
        //Từ HomePage qua registerPage
        registerPage = new UserRegisterPO(driver);
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
        //Vào login page
        registerPage.openLoginPage();
        //khoi tao loginPage
        loginPage = new UserLoginP0(driver);
        //Actions:
        //Submit login => trở về homepage sau khi login thành công, khoi tao homepage
        homePage =  loginPage.loginToSystem(email, password);

        //verify: link My account hiển thị
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_MyAccount(){
        //Action tại homePage:
        homePage.openCustomerInfoPage();
        //=> move to qua myAccount page
        customerInfoPage = new UserCustomerInfoPO(driver);
        //verify:
        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), email);
        Assert.assertEquals(customerInfoPage.getCompanyNameTextboxValue(), companyName);
        //get dropdown value
        //Assert.assertEquals(customerInfoPage.getDayDropdownSelectedValue(), "");
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
