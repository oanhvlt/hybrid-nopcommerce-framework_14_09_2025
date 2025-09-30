package javaInheritance;

//SideBarPO: kế thừa BaseBase => gọi trực tiếp hàm mà không cần khoi tao BasePage
public class SideBarPO extends BasePage{
    public void clickToOrderButton(){
        clickToElement();
    }
}
