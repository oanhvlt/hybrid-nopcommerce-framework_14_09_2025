package javaBasic;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Loop {

    //final: khộng cho phép gán lại giá trị mới
    //- không cho các class khác kế thừa
    public static final String PI = "3.14";

    //ko có static: phải tạo đối tượng mới dc truy cập
    //- không cho các class khác override
    public final void clickToElem(){

    }

    //static: truy cập trực tiếp class mà không phải khởi tạo
    public static void clickToButton() {
        //
    }

    //throws: nếu dùng hàm có throws thì cũng phải thêm throws
    public static void clickToButton2() throws InterruptedException {
        Thread.sleep(5000);
    }
    //dùng clickToButton2 có throws => cũng phải thêm throws
    public static void main(String[] args) throws InterruptedException {

        //Khởi tạo object mới truy cập dc hàm trong class
        Topic_02_Loop topic02 = new Topic_02_Loop();
        topic02.clickToElem();

        //truy cập trực tiếp hàm/biến static trong class
        System.out.println(Topic_02_Loop.PI);
        Topic_02_Loop.clickToButton();
        //hoặc gọi trực tiếp:
        clickToButton();

        //dùng clickToButton2 có throws => hàm main cũng phải thêm throws
        clickToButton2();

       int i, j = 1;

       System.out.println("For:");
       for(i = 1; i <= 10; i++){
           if(i == 5){
               continue;
           }
           System.out.println(i);
       }

       i = 1;
       while (i <= 10){
           System.out.println(i);
           i++;
       }

       i = 1;
       do{
           System.out.println(i);
           i++;
       }while (i <= 10);


   }

   //try-catch,throw , new
   public boolean isElementDisplayed(){
       WebDriver driver = new FirefoxDriver(); // khi khời tạo 1 class phải dùng new
       WebElement element = driver.findElement(By.cssSelector(""));
       boolean status = false;
       try {
           status = element.isDisplayed();
       } catch (NoSuchElementException e){
           e.printStackTrace();
           //show message
           throw new RuntimeException(e.getMessage());
       } finally { //bắt buộc chạy
           //Đóng kết nối DB
           //Clean data
       }
       return status;
   }
}





