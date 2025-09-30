package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Random;


//Chỉ khởi tạo driver 1 lần ở đây
public class BaseTest {

    protected WebDriver driver;

    protected WebDriver getBrowserDriver(String browserName){
        //sử dụng emum cho các browser value
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser){
            case FIREFOX:
                //Dùng WebDriverManager
                //- create() vừa tải, vừa cấu hình driver, vừa trả về đối tượng WebDriver.
                ////driver = WebDriverManager.firefoxdriver().create();

                //dùng SeleniumManager
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");
        }
      //Mở URL
      driver.get("https://demo.nopcommerce.com/");
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));

      return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url){
        //sử dụng emum cho các browser value
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser){
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");
        }
        //Mở URL
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));

        return driver;
    }

    protected int generateRandomNumber(){
        return new Random().nextInt(99999);
    }

}
