package com.automationpractice.pageObject;

import com.automationpractice.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateAnAccountPage {
    @FindBy(id = "id_gender1")
    private WebElement maleGenderInPersonalIinformationRadioButton;
    @FindBy (id = "id_gender2")
    private WebElement femaleGenderInPersonalIinformationRadioButton;
    @FindBy (id = "customer_firstname")
    private WebElement firstNameInPersonalIinformationInputField;
    @FindBy (id = "customer_lastname")
    private WebElement lastNameInPersonalIinformationInputField;
    @FindBy (id = "passwd")
    private WebElement passwordInPersonalIinformationInputField;
    @FindBy (id = "days")
    private WebElement daysDropDownListInPersonalIinformation;
    @FindBy (id = "months")
    private WebElement monthsDropDownListInPersonalIinformation;
    @FindBy (id = "years")
    private WebElement yearsDropDownListInPersonalIinformation;
    @FindBy (id = "firstname")
    private WebElement firstNameInAddressInputField;
    @FindBy(id = "lastname")
    private WebElement lastNameInAddressInputField;
    @FindBy(id = "company")
    private WebElement companyNameInAddressInputField;
    @FindBy(id = "address1")
    private WebElement address1InAddressInputField;
    @FindBy(id = "city")
    private WebElement city1InAddressInputField;
    @FindBy (id = "id_state")
    private WebElement stateDropDownListInAddress;
    @FindBy (id = "postcode")
    private WebElement postalCodeInAddressInputField;
    @FindBy (id = "id_country")
    private WebElement countryDropDownListInAddress;
    @FindBy (id = "phone_mobile")
    private WebElement mobilePhoneInAddressInputField;
    @FindBy (id = "submitAccount")
    private WebElement submitAccountInAddressButton;
    @FindBy (xpath = "//div[@class='alert alert-danger']")
    private WebElement errorsFromBadDataPullingIntoRegisterField;

    private WebDriver driver;
    private Waits waits;
    private Select select;

    public CreateAnAccountPage(WebDriver driver){
        this.driver = driver;
        this.waits = new Waits(driver);

        PageFactory.initElements(driver,this);
    }
    private void manageDropDownList() {
        select = new Select(daysDropDownListInPersonalIinformation);
        select.selectByValue("8");
        select = new Select(monthsDropDownListInPersonalIinformation);
        select.selectByValue("3");
        select = new Select(yearsDropDownListInPersonalIinformation);
        select.selectByValue("1991");
        select = new Select(stateDropDownListInAddress);
        select.selectByValue("18");
        select = new Select(countryDropDownListInAddress);
        select.selectByValue("21");
    }
    public List<String> fillingPersonalInformationAndAddressAndClickRegisterButton(String gender, String firstName, String lastName){
        waits.waitForElementToBeClickable(submitAccountInAddressButton);
        List<String> errors = new ArrayList<>();
        Boolean errorsOccured = false;
        //Gender not Requaired. If | M=Male | W=Female | null = not selected
        //Male
        if(gender.equalsIgnoreCase("m")){
            maleGenderInPersonalIinformationRadioButton.click();
        }
        //Female
        if(gender.equalsIgnoreCase("w")){
            femaleGenderInPersonalIinformationRadioButton.click();
        }
        //FirstName Requaired - | Only Letters |
        firstNameInPersonalIinformationInputField.sendKeys(firstName);
        //Lastname Requaired - | Only Letters |
        lastNameInPersonalIinformationInputField.sendKeys(lastName);
        passwordInPersonalIinformationInputField.sendKeys("ccccc");
        manageDropDownList();
        firstNameInAddressInputField.sendKeys("aaaaaaa");
        lastNameInAddressInputField.sendKeys("bbbbbbbbb");
        companyNameInAddressInputField.sendKeys("dddddddd");
        address1InAddressInputField.sendKeys("eeeeeeeee");
        city1InAddressInputField.sendKeys("fffffffff");
        postalCodeInAddressInputField.sendKeys("00000");
        mobilePhoneInAddressInputField.sendKeys("1234567890");
        //Checking errors:
        if (!(Pattern.matches("[a-zA-Z]+",firstName)) || !(Pattern.matches("[a-zA-Z]+",lastName))) {
            errorsOccured = true;
        }
        submitAccountInAddressButton.click();
        if (errorsOccured) {
            waits.waitForElementToBeVisible(errorsFromBadDataPullingIntoRegisterField);
            if (errorsFromBadDataPullingIntoRegisterField.getText().contains("firstname is required.")) {
                errors.add("firstname is required.");
            }
            if (errorsFromBadDataPullingIntoRegisterField.getText().contains("firstname is invalid.")) {
                errors.add("firstname is invalid.");
            }
            if (errorsFromBadDataPullingIntoRegisterField.getText().contains("lastname is required.")) {
                errors.add("lastname is required.");
            }
            if (errorsFromBadDataPullingIntoRegisterField.getText().contains("lastname is invalid.")) {
                errors.add("lastname is invalid.");
            }
        }
        else{
            errors.add("Data correct");
        }

        return errors;
    }
}
