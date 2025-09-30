package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

//Kế thừa không cần khai báo và khoi tạo đối tượng mà goi trực tiếp hàm

public class Level_02_BasePage_Inheritance extends BasePage{

    WebDriver driver;
    ///BasePage basePage;
    WebDriverWait explicitWait;

    //Tạo data test
    String firstName, lastName, email, companyName, password, day, month, year;

    Properties props = new Properties();
    String projectPath = System.getProperty("user.dir");
    FileOutputStream outputStream;

    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        //initial:
        driver = new FirefoxDriver();
        
        //basePage = getBasePage();

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

        String path = projectPath + "\\dataTest\\user.properties";
        outputStream = new FileOutputStream(path);
    }


    @Test
    public void TC_01_Register() throws IOException {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElem(driver, "//a[@class='ico-register']");
        waitForElementClickable(driver, "//input[@id='gender-male']");
        clickToElem(driver, "//input[@id='gender-male']");

        sendKeyToElement(driver, "//input[@id='FirstName']",firstName);
        sendKeyToElement(driver, "//input[@id='LastName']",lastName);
        sendKeyToElement(driver, "//input[@id='Email']",email);
        sendKeyToElement(driver, "//input[@id='Company']",companyName);
        sendKeyToElement(driver, "//input[@id='Password']",password);
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']",password);

        //select dropdown
        //selectItemInDropdown(driver,"","");
        //select calendar
        //new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthday']"))).selectByVisibleText("");

        waitForElementClickable(driver,"//button[@id='register-button']");
        clickToElem(driver, "//button[@id='register-button']");


        //verify:
        Assert.assertEquals(getElementText(driver,"//div[@class='result']"), "Your registration completed");

        props.setProperty("email", email);
        props.setProperty("password", password);
        props.store(outputStream,null);



    }
    @Test
    public void TC_02_Login(){
        //logout a.ico-logout
        getElement(driver,"//a[@class='ico-logout']").click();
        waitForElementClickable(driver, "//a[@class='ico-login']");
        clickToElem(driver, "//a[@class='ico-login']");
        sendKeyToElement(driver, "//input[@id='Email']",email);
        sendKeyToElement(driver, "//input[@id='Password']",password);

        waitForElementClickable(driver,"//button[@class='button-1 login-button']");
        clickToElem(driver, "//button[@class='button-1 login-button']");

        //verify: link My account hiển thị
        Assert.assertTrue(isElementDisplayed(driver,"//a[@class='ico-account' and text()='My account']"));

    }

    @Test
    public void TC_03_MyAccount(){
        waitForElementClickable(driver, "//a[@class='ico-account']");
        clickToElem(driver, "//a[@class='ico-account']");

        //verify:
        Assert.assertTrue(IsElementSelected(driver,"//input[@id='gender-male']"));
        Assert.assertEquals(getElementAttribute(driver, "//input[@id='FirstName']", "value"), firstName);
        Assert.assertEquals(getElementAttribute(driver, "//input[@id='LastName']", "value"), lastName);
        Assert.assertEquals(getElementAttribute(driver, "//input[@id='Email']", "value"), email);
        Assert.assertEquals(getElementAttribute(driver, "//input[@id='Company']", "value"), companyName);

        //verify dropdown
        //Assert.assertEquals(getSelectItemInDropdown(driver,""), "");

    }

    private int generateRandomNumber(){
        return new Random().nextInt(99999);
    }

    @AfterClass
    public void afterClass(){

        //driver.quit();
    }


}
