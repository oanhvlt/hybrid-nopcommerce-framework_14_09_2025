package pageUIs.user;

public class SidebarPageUI {
    public static final String ADDRESS_LINK = "Xpath=//li[contains(@class,'customer-addresses')]/a[text()='Addresses']";
    public static final String REWARD_POINT_LINK = "Xpath=//li[contains(@class,'reward-points')]/a[text()='Reward points']";
    public static final String CUSTOMER_INFO_LINK = "Xpath=//li[contains(@class,'customer-info')]/a[text()='Customer info']";
    public static final String ORDER_LINK = "Xpath=//li[contains(@class,'customer-orders')]/a[text()='Orders']";

    public static final String DYNAMIC_LINK_BY_CLASS_NAME_PAGE_NAME = "Xpath=//li[contains(@class,'%s')]/a[text()='%s']";
}
