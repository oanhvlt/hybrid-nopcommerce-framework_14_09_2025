package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.*;


//Kế thừa không cần khai báo và khoi tạo đối tượng mà goi trực tiếp hàm
public class Level_05_PageFactory extends BaseTest {

    //private WebDriver driver;
    private HomePageFactory homePage;
    private RegisterPageFactory registerPage;
    private LoginPageFactory loginPage;
    private CustomerInfoPageFactory customerInfoPage;

    //Tạo data test
    String firstName, lastName, email, companyName, password, day, month, year;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){ //String browserName
        //driver: có thể kế thừa từ BaseTest nếu ko khai báo
        driver = getBrowserDriver(browserName);
        //khoi tao
        homePage = new HomePageFactory(driver);

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

    @Test
    public void User_01_Register(){
        //Action 1: tại homepage
        homePage.clickToRegisterLink();
        //Từ HomePage qua registerPage
        registerPage = new RegisterPageFactory(driver);
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
        registerPage.clickLoLoginLink();
        //khoi tao loginPage
        loginPage = new LoginPageFactory(driver);
        //Actions:
        loginPage.enterToEmailTextbox(email);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();
        //=> trở về homepage sau khi login thành công => khoi tao homepage
        homePage = new HomePageFactory(driver);
        //verify: link My account hiển thị
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

    @Test
    public void User_03_MyAccount(){
        //Action tại homePage:
        homePage.clickToMyAccountLink();
        //=> move to qua myAccount page
        customerInfoPage = new CustomerInfoPageFactory(driver);
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
