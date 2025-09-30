package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;
import pageObjects.user.UserAddressPO;
import pageObjects.user.UserCustomerInfoPO;
import pageObjects.user.UserOrderPO;
import pageObjects.user.UserRewardPointPO;
import pageUIs.user.SidebarPageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

//class: luôn luôn là public, có thể thêm abstract/final
public class BasePage {
    //Access modifier:
    /*
    * public/default/protected/private: có thể đi kèm với:
    * final/static
    * */

    //hàm static có thề truy cập trực tiếp từ phạm vi class (Encapsulation: đóng gói)

    public static BasePage getBasePage(){

        return new BasePage();
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Truyền tham số vào loại gì sẽ trả về kieu By tương ứng
    //convention: css/id/name/class
    private By getByLocator(String prefixLocator){
        By by = null;
        if(prefixLocator.toLowerCase().startsWith("id")){//"id=..."
            by = By.id(prefixLocator.substring(3));
        }else if(prefixLocator.toLowerCase().startsWith("class")){
            by = By.className(prefixLocator.substring(6));
        }else if(prefixLocator.toLowerCase().startsWith("name")){
            by = By.name(prefixLocator.substring(5));
        }else if(prefixLocator.toLowerCase().startsWith("tagname")){
            by = By.tagName(prefixLocator.substring(8));
        }else if(prefixLocator.toLowerCase().startsWith("css")){
            by = By.cssSelector(prefixLocator.substring(4));
        }else if(prefixLocator.toLowerCase().startsWith("xpath")){
            by = By.xpath(prefixLocator.substring(6));
        }else {
            throw new RuntimeException("Locator type is not support!");
        }
        return by;
    }

    private By getByXpath(String locator){
        return By.xpath(locator);
    }


    protected WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }

    protected List<WebElement> getListElement(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }

    public void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }
    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }
    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }
    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }
    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }


    //Alert
    //Kiểu dữ liệu trả về Alert
    public Alert wailAlertPresent(WebDriver driver){
        //alertIsPresent() đã bao gồm alert.switchTo()
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    //Action of Alert: hàm gọi hàm > tái sử dụng wailAlertPresent(driver)
    public void acceptToAlert(WebDriver driver) {
        //wailAlertPresent: trả về Alert và có chứa switchTo()
        wailAlertPresent(driver).accept();
    }
    public void cancelToAlert(WebDriver driver) {
        wailAlertPresent(driver).dismiss();
    }
    public String getAlertText(WebDriver driver) {
        return wailAlertPresent(driver).getText();
    }
    public void sendKeyToAlert(WebDriver driver, String keyToSend) {
        wailAlertPresent(driver).sendKeys(keyToSend);
    }

    //window
    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows){
            if(!runWindow.equals((parentID))){
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }
    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows){
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if(currentWin.equals((title))){
                break;
            }
        }
    }
    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows){
            if(!runWindow.equals((parentID))){
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    //Element
    public void clickToElem(WebDriver driver, String locator) {
       getElement(driver,locator).click();
    }
    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend) {
        getElement(driver,locator).sendKeys(keyToSend);
    }

    public void clearTextInputElement(WebDriver driver, String locator) {
        getElement(driver,locator).clear();
    }


    public void selectItemInDropdown(WebDriver driver, String locator, String textItem) {
        new Select(getElement(driver,locator)).selectByVisibleText(textItem);
    }

    public String getSelectItemInDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver,locator)).getFirstSelectedOption().getText();
    }
    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver,locator)).isMultiple();
    }

    public void customDropdown(WebDriver driver, String parentLocator, String chileItemLocator, String expectedItem) throws InterruptedException {
        //JavascriptExecutor jsExe;
        getElement(driver,parentLocator).click();
        sleepInSecond(1);

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(chileItemLocator)));
        for (WebElement item : allItems){
            if(item.getText().trim().equals(expectedItem)){
                //jsExe = (JavascriptExecutor) driver;
                //jsExe.executeScript("arguments[0].scrollIntoView(true)", item);
                sleepInSecond(1);
                item.click();
                break;
            }
        }
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName){
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locator){
        return getElement(driver, locator).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName){
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbaValue){
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator){
        return getListElement(driver, locator).size();
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator){
        if(!getElement(driver,locator).isSelected()){
            getElement(driver,locator).click();
        }
    }

    //Display: present + visible
    //Enable: present + Clickable/Editable/scrollable/selectable
    //Selected: present + tagName = input / option (selected) => checkbox/radio/dropdown default

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getElement(driver,locator).isDisplayed();
    }
    public boolean IsElementEnabled(WebDriver driver, String locator){
        return getElement(driver,locator).isEnabled();
    }

    public boolean IsElementSelected(WebDriver driver, String locator){
        return getElement(driver,locator).isSelected();
    }

    //iframe
    public void switchToIFrame(WebDriver driver, String locator){
        driver.switchTo().frame(getElement(driver,locator));
    }

    //DefaultContent
    public void switchToDefaultPage(WebDriver driver, String locator){
        driver.switchTo().defaultContent();
    }

    //Actions: dùng perform()
    public void hoverToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getElement(driver,locator)).perform();
    }

    public void clickAndHoldToElement(WebDriver driver, String locator){
        new Actions(driver).clickAndHold(getElement(driver,locator)).perform();
    }
    //sau khi hold thì nhả chuột: release
    public void releaseLeftMouse(WebDriver driver){
        new Actions(driver).release();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getElement(driver,locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getElement(driver,locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getElement(driver,sourceLocator), getElement(driver,targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key){
        new Actions(driver).sendKeys(getElement(driver,locator), key).perform();
    }

    public void scrollToElement(WebDriver driver, String locator){
        new Actions(driver).scrollToElement(getElement(driver,locator)).perform();
    }

    //JavascriptExecutor

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getElement(driver,locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].textContent;", getElement(driver, locator));
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean)((JavascriptExecutor)driver)
                .executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                        getElement(driver,locator));
    }

    //wait

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementPresent(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }


    public UserRewardPointPO openRewardPointPage(WebDriver driver) {
        waitForElementClickable(driver, SidebarPageUI.REWARD_POINT_LINK);
        clickToElem(driver, SidebarPageUI.REWARD_POINT_LINK);
        return PageGenerator.getUserRewardPointPage(driver);

    }

    public UserCustomerInfoPO openCustomerInfoPage(WebDriver driver) {
        waitForElementClickable(driver, SidebarPageUI.CUSTOMER_INFO_LINK);
        clickToElem(driver,SidebarPageUI.CUSTOMER_INFO_LINK);
        return PageGenerator.getUserCustomerInfoPage(driver);
    }

    public UserAddressPO openAddressPage(WebDriver driver) {
        waitForElementClickable(driver, SidebarPageUI.ADDRESS_LINK);
        clickToElem(driver, SidebarPageUI.ADDRESS_LINK);
        return PageGenerator.getUserAddressPage(driver);
    }

    public UserOrderPO openOrderPage(WebDriver driver) {
        waitForElementClickable(driver, SidebarPageUI.ORDER_LINK);
        clickToElem(driver,SidebarPageUI.ORDER_LINK);
        return PageGenerator.getUserOrderPage(driver);
    }

}



















