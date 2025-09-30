package javaInheritance;

//LoginPage: kế thừa BaseBase => gọi trực tiếp hàm mà không cần khoi tao BasePage
public class LoginPage extends BasePage{
    public void clickToLoginButton(){
        clickToElement();
    }
}
