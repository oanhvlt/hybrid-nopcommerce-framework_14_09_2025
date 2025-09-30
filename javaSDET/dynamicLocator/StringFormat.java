package dynamicLocator;

public class StringFormat {
    public static void main(String[] args) {
        String DELETE_ICON_BY_FEMAIL = "//td[@data-key='females' and text()='%s']/preceding-sibling::td/button[@class='qgrd-remove-row-btn']";
        clickToDeleteIcon(DELETE_ICON_BY_FEMAIL, "384187");
        clickToDeleteIcon(DELETE_ICON_BY_FEMAIL, "384187");
    }

    public static void clickToDeleteIcon(String locator, String female){
        locator = String.format(locator, female);
        System.out.println("Delete to icon: " + locator);
    }

    public static void clickToDeleteIcon(String locator, String country, String female){
        locator = String.format(locator, country, female);
        System.out.println("Delete to icon: " + locator);
    }

    public static void clickToDeleteIcon(String locator, String... restParameter){
        locator = String.format(locator, (Object[]) restParameter); //cast restParameter thành object để dùng trong String.format
        System.out.println("Delete to icon: " + locator);
    }

}
