package com.nopcommerce.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_01_Repeat_Yourself {

    WebDriver driver;
    WebDriverWait explicitWait;

    //Tạo data test
    String firstName, lastName, email, companyName, password, day, month, year;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //initial:
        int num =  generateRandomNumber();
        firstName ="Alice" + num;
        lastName="Vu";
        email="thomas" + num + "@gmail.com" ;
        companyName="Alice" + num + "LTD";
        password="123456";
        day="10";
        month="October";
        year="1988";

    }

    @Test
    public void TC_01_Register(){
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#gender-male")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        //select calender
        //new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthday']"))).selectByVisibleText("");

        driver.findElement(By.cssSelector("button#register-button")).click();

        //verify:
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

        //logout a.ico-logout
        driver.findElement(By.cssSelector("a.ico-logout")).click();
    }
    @Test
    public void TC_02_Login(){
         driver.findElement(By.cssSelector("a.ico-login")).click();
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);

        driver.findElement(By.cssSelector("button.login-button")).click();

        //verify: link My account hiển thị
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account' and text()='My account']")).isDisplayed());

    }

    @Test
    public void TC_03_MyAccount(){
        //driver.findElement(By.cssSelector("a.ico-account")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.ico-account"))).click();
        //verify:
        Assert.assertTrue(driver.findElement(By.cssSelector("input#gender-male")).isSelected());
        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);

        //select calender
        //Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthday']"))).getFirstSelectedOption().getText(), "");

    }

    private int generateRandomNumber(){
        return new Random().nextInt(99999);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
