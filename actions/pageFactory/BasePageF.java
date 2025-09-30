package pageFactory;

import commons.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageF {

    //Element
    public void clickToElem(WebElement elem) {
        elem.click();
    }

    public void sendKeyToElement(WebElement elem, String keyToSend) {
        elem.sendKeys(keyToSend);
    }

    public void selectItemInDropdown(WebElement elem, String textItem) {
        new Select(elem).selectByVisibleText(textItem);
    }

    public String getSelectItemInDropdown(WebElement elem) {
        return new Select(elem).getFirstSelectedOption().getText();
    }

    public String getElementAttribute(WebElement elem, String attributeName){
        return elem.getAttribute(attributeName);
    }

    public String getElementText(WebElement elem){
        return elem.getText();
    }

    public String getElementCssValue(WebElement elem, String propertyName){
        return elem.getCssValue(propertyName);
    }

    public void checkToCheckboxRadio(WebElement elem){
        if(!elem.isSelected()){
            elem.click();
        }
    }

    public boolean isElementDisplayed(WebElement elem){
        return elem.isDisplayed();
    }

    public boolean IsElementEnabled(WebElement elem){
        return elem.isEnabled();
    }

    public boolean IsElementSelected(WebElement elem){
        return elem.isSelected();
    }

    public void waitForElementVisible(WebDriver driver, WebElement elem) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(elem));
    }

    public void waitForElementSelected(WebDriver driver, WebElement elem) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeSelected(elem));
    }

    public void waitForElementClickable(WebDriver driver, WebElement elem) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(elem));
    }

}
