package pageObjects.user;

import org.openqa.selenium.WebDriver;
import pageUIs.user.CustomerPageUI;

public class UserCustomerInfoPO extends UserSidebarPO {

    public UserCustomerInfoPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebDriver driver;

    public boolean isGenderMaleSelected() {
        waitForElementSelected(driver, CustomerPageUI.GENDER_MALE_RADIO);
        return IsElementSelected(driver, CustomerPageUI.GENDER_MALE_RADIO);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, CustomerPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, CustomerPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, CustomerPageUI.EMAIL_ADDRESS_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.EMAIL_ADDRESS_TEXTBOX, "value");
    }


    public String getCompanyNameTextboxValue() {
        waitForElementVisible(driver, CustomerPageUI.COMPANY_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.COMPANY_NAME_TEXTBOX, "value");
    }
    //dropdown
    public String getDayDropdownSelectedValue() {
        waitForElementClickable(driver, CustomerPageUI.DAY_DROPDOWN);
        return getSelectItemInDropdown(driver, CustomerPageUI.DAY_DROPDOWN);
    }

}























