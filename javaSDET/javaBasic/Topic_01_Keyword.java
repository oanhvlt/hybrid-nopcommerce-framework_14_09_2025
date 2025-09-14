package javaBasic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//class bình thường: Có các hàm non-abstract, không dc chứa các hàm abstract
//Có các hàm non-abstract: có phần body
public class Topic_01_Keyword {
    //Nguyên thủy
    char c = 'a';
    byte bNumber = 10;
    short sNumber = 100;
    int iNumber = 1000;
    long lNumber = 10000;
    float fNumber = 15.7f;
    double dNumber = 45.34d;
    boolean bStatus = true;

    //Tham chiếu (Reference type)
    String fullName = "Automation Test";

    //Functions
    void clickToLoginButton(){

    }
    String getLoginMessage(){
        return "";
    }

    //Access modifier: phạm vi truy cập
    /*
    - public
    - protected: kế thừa mới dùng dc
    - private: chỉ bản thân class đó dùng dc
    - default: trong package là dùng dc
     */
    public String address = "";
    protected String city = "";
    private String phone = "";
    //default:
    String zipcode = "353";

    //package: define xem class/Interface nằm trong package nào

    WebDriver driver;

    public WebDriver getBrowserDriver(String browserName){
//        if(browserName.equals("firefox")){
//            driver = new FirefoxDriver();
//        }else {
//            driver = new ChromeDriver();
//        }
//



        switch (browserName){
            case "firefox": //case ko cho phép trùng
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        return driver;
    }

}























