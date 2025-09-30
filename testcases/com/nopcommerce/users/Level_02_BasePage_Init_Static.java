package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_Init_Static extends BasePage {

    WebDriver driver;
    BasePage basePage;
    WebDriverWait explicitWait;

    //Tạo data test
    String firstName, lastName, email, companyName, password, day, month, year;

    @BeforeClass
    public void beforeClass(){
        //initial:
        driver = new FirefoxDriver();
        //getBasePage: hàm static có thề truy cập trực tiếp từ phạm vi class (Encapsulation: đóng gói)
        basePage = BasePage.getBasePage();

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        int num =  generateRandomNumber();
        firstName ="Alice" + num;
        lastName="Vu";
        email="alice" + num + "@gmail.com" ;
        companyName="alice" + num + "LTD";
        password="123456";
        day="10";
        month="October";
        year="1988";
    }

    @Test
    public void TC_01_Register(){
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElem(driver, "//a[@class='ico-register']");
        basePage.waitForElementClickable(driver, "//input[@id='gender-male']");
        basePage.clickToElem(driver, "//input[@id='gender-male']");

        basePage.sendKeyToElement(driver, "//input[@id='FirstName']",firstName);
        basePage.sendKeyToElement(driver, "//input[@id='LastName']",lastName);
        basePage.sendKeyToElement(driver, "//input[@id='Email']",email);
        basePage.sendKeyToElement(driver, "//input[@id='Company']",companyName);
        basePage.sendKeyToElement(driver, "//input[@id='Password']",password);
        basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']",password);

        //select dropdown
        //basePage.selectItemInDropdown(driver,"","");
        //select calendar
        //new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthday']"))).selectByVisibleText("");

        basePage.waitForElementClickable(driver,"//button[@id='register-button']");
        basePage.clickToElem(driver, "//button[@id='register-button']");


        //verify:
        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='result']"), "Your registration completed");

        //logout a.ico-logout
        getElement(driver,"//a[@class='ico-logout']").click();


    }
    @Test
    public void TC_02_Login(){
        basePage.waitForElementClickable(driver, "//a[@class='ico-login']");
        basePage.clickToElem(driver, "//a[@class='ico-login']");
        basePage.sendKeyToElement(driver, "//input[@id='Email']",email);
        basePage.sendKeyToElement(driver, "//input[@id='Password']",password);

        basePage.waitForElementClickable(driver,"//button[@class='button-1 login-button']");
        basePage.clickToElem(driver, "//button[@class='button-1 login-button']");

        //verify: link My account hiển thị
        Assert.assertTrue(basePage.isElementDisplayed(driver,"//a[@class='ico-account' and text()='My account']"));

    }

    @Test
    public void TC_03_MyAccount(){
        basePage.waitForElementClickable(driver, "//a[@class='ico-account']");
        basePage.clickToElem(driver, "//a[@class='ico-account']");

        //verify:
        Assert.assertTrue(basePage.IsElementSelected(driver, "//input[@id='gender-male']"));
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id='FirstName']", "value"), firstName);
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id='LastName']", "value"), lastName);
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id='Email']", "value"), email);
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id='Company']", "value"), companyName);

        //verify dropdown
        //Assert.assertEquals(basePage.getSelectItemInDropdown(driver,""), "");

    }

    private int generateRandomNumber(){
        return new Random().nextInt(99999);
    }

    @AfterClass
    public void afterClass(){

        driver.quit();
    }


}
