package com.nopcommerce.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.user.UserCustomerInfoPO;
import pageObjects.user.UserHomePO;
import pageObjects.user.UserLoginP0;
import pageObjects.user.UserRegisterPO;


import java.time.Duration;
import java.util.Random;

//Kế thừa không cần khai báo và khoi tạo đối tượng mà goi trực tiếp hàm
public class Level_03_Page_Object_Pattern {

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginP0 loginPage;
    private UserCustomerInfoPO customerInfoPage;

    //Tạo data test
    String firstName, lastName, email, companyName, password, day, month, year;

    @BeforeClass
    public void beforeClass(){
        //initial:
        driver = new FirefoxDriver();
        //Mở URL => qua Home page
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //khoi tao
        homePage = new UserHomePO(driver);

        int num =  generateRandomNumber();
        firstName ="Alice" + num;
        lastName="Vu";
        email="alice" + num + "@gmail.com" ;
        companyName="alice" + num + " LTD";
        password="123456";
        day="10";
        month="October";
        year="1988";
    }

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
        loginPage.enterToEmailTextbox(email);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();
        //=> trở về homepage sau khi login thành công => khoi tao homepage
        homePage = new UserHomePO(driver);
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

    private int generateRandomNumber(){
        return new Random().nextInt(99999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
