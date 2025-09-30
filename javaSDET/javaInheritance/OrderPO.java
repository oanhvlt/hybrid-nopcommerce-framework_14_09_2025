package javaInheritance;

//OrderPO: kế thừa SideBarPO => gọi trực tiếp hàm SideBarPO, BasePage mà không cần khoi tao BasePage, SideBarPO
public class OrderPO extends SideBarPO{
    public void clickToOrderCheckbox(){
        clickToElement();
        clickToOrderButton();
    }
}
