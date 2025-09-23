package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    WebDriverWait explicitWait;

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


    //Wait - Alert
    //Kiểu dữ liệu trả về Alert
    public Alert wailAlertPresent(WebDriver driver){
        //alertIsPresent() đã bao gồm alert.switchTo()
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
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

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(chileItemLocator)));
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

    public void sleepInSecond(long timeInSecond) throws InterruptedException {
        Thread.sleep(timeInSecond * 1000);
    }


    public WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(By.xpath(locator));
    }
}



















