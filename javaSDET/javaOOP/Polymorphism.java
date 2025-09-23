package javaOOP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Tính đa hình: tạo các hàm cùng tên, khác tham số (các hàm có cùng chức năng nhưng dùng trong các ngữ cảnh khác nhau)
public class Polymorphism {

    //Tạo nhiều loại hàm khởi tạo => truy cập từ bên ngoài có thể gọi 1 trong các hàm
    public Polymorphism(){

    }
    public Polymorphism (WebDriver webDriver){

    }
    public Polymorphism (WebDriver webDriver, WebDriverWait explicitWait){

    }

    public static void main (String[] args){
        WebDriver driver = new FirefoxDriver();
        Actions actions = new Actions(driver);
        actions.clickAndHold();
        actions.clickAndHold(driver.findElement(By.cssSelector("")));
    }
}
