package com.automationpractice.pageObject;

import com.automationpractice.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CreateAnAccountPage {
    @FindBy(id = "id_gender1")
    private WebElement maleGenderInPersonalIinformationRadioButton;
    @FindBy(id = "id_gender2")
    private WebElement femaleGenderInPersonalIinformationRadioButton;
    @FindBy(id = "customer_firstname")
    private WebElement firstNameInPersonalIinformationInputField;
    @FindBy(id = "customer_lastname")
    private WebElement lastNameInPersonalIinformationInputField;
    @FindBy(id = "passwd")
    private WebElement passwordInPersonalIinformationInputField;
    @FindBy(id = "days")
    private WebElement daysDropDownListInPersonalIinformation;
    @FindBy(id = "months")
    private WebElement monthsDropDownListInPersonalIinformation;
    @FindBy(id = "years")
    private WebElement yearsDropDownListInPersonalIinformation;
    @FindBy(id = "firstname")
    private WebElement firstNameInAddressInputField;
    @FindBy(id = "lastname")
    private WebElement lastNameInAddressInputField;
    @FindBy(id = "company")
    private WebElement companyNameInAddressInputField;
    @FindBy(id = "address1")
    private WebElement address1InAddressInputField;
    @FindBy(id = "city")
    private WebElement city1InAddressInputField;
    @FindBy(id = "id_state")
    private WebElement stateDropDownListInAddress;
    @FindBy(id = "postcode")
    private WebElement postalCodeInAddressInputField;
    @FindBy(id = "id_country")
    private WebElement countryDropDownListInAddress;
    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneInAddressInputField;
    @FindBy(id = "submitAccount")
    private WebElement submitAccountInAddressButton;
    @FindBy(xpath = "//div[@class='alert alert-danger']")
    private WebElement errorsFromBadDataPullingIntoRegisterField;

    private WebDriver driver;
    private Waits waits;
    private Select select;

    private class DateReturn {
        Boolean errorsOccured = false;
        List<String> errors = new ArrayList<>();
    }

    private static final Map<String, Integer> statesInUSA;

    static {
        Map<String, Integer> aMap = new TreeMap<>();
        aMap.put("Alabama", 1);
        aMap.put("Alaska", 2);
        aMap.put("Arizona", 3);
        aMap.put("Arkansas", 4);
        aMap.put("California", 5);
        aMap.put("Colorado", 6);
        aMap.put("Connecticut", 7);
        aMap.put("Delaware", 8);
        aMap.put("Florida", 9);
        aMap.put("Georgia", 10);
        statesInUSA = Collections.unmodifiableMap(aMap);
    }
    private static final Map<String, String> countrySaveInMap;

    static {
        Map<String, String> aMap = new TreeMap<>();
        aMap.put("United States", "21");
        aMap.put("-", "");
        countrySaveInMap = Collections.unmodifiableMap(aMap);
    }

    public CreateAnAccountPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);

        PageFactory.initElements(driver, this);
    }

    private DateReturn manageDropDownList(String Date, String stateOfUSA, String country) {
        // ------------------------------------
        // -------Date Should have  DD-MM-YYYY pattern
        //Days always have 1-31 option value, so this is the only Required
        DateReturn dateReturn = new DateReturn();
        select = new Select(countryDropDownListInAddress);
        select.selectByValue(String.valueOf(""));
        if(Pattern.matches("\\d{2}-\\d{2}-\\d{4}", Date)) {
            select = new Select(daysDropDownListInPersonalIinformation);
            if (Date.startsWith("0")) {
                select.selectByValue(Date.substring(1, 2));
            } else {
                select.selectByValue(Date.substring(0, 2));
            }
            //Months have 1-12 option value
            select = new Select(monthsDropDownListInPersonalIinformation);
            if (Date.substring(3, 4).equals("0")) {
                select.selectByValue(Date.substring(4, 5));
            } else {
                select.selectByValue(Date.substring(3, 5));
            }
            //Years are Selected strictly like | Year 1991 = "1991" | in option value
            select = new Select(yearsDropDownListInPersonalIinformation);
            select.selectByValue(Date.substring(6));
        }
        else {
            dateReturn.errors.add("Wrong birth date.");
        }
        // ------------------------------------
        // Need to country be selected first if its United States to choose State of It
        //United State is set by default
        if (countrySaveInMap.get(country) != null) {
            //Only USA can be Choose so always value 21
            select = new Select(countryDropDownListInAddress);
            select.selectByValue(String.valueOf(countrySaveInMap.get(country)));
            //There are 1-50 option value State but we will use only 10 for practise purpose
            if (statesInUSA.get(stateOfUSA) != null) {
                select = new Select(stateDropDownListInAddress);
                select.selectByValue(String.valueOf(statesInUSA.get(stateOfUSA)));
            } else {
                dateReturn.errorsOccured = true;
            }


        }
        else {
            dateReturn.errorsOccured = true;
        }
        return dateReturn;
    }

    public List<String> fillingPersonalInformationAndAddressAndClickRegisterButton(String gender, String firstName, String lastName, String passwd, String Date, String stateOfUSA, String country) {
        waits.waitForElementToBeClickable(submitAccountInAddressButton);
        List<String> errors = new ArrayList<>();
        Boolean errorsOccured = false;

        DateReturn dateReturn = new DateReturn();
        //Gender not Required. If | M=Male | W=Female | null = not selected
        //Male
        if (gender.equalsIgnoreCase("m")) {
            maleGenderInPersonalIinformationRadioButton.click();
        }
        //Female
        if (gender.equalsIgnoreCase("w")) {
            femaleGenderInPersonalIinformationRadioButton.click();
        }
        //FirstName Required - | Only Letters |
        firstNameInPersonalIinformationInputField.sendKeys(firstName);
        //LastName Required - | Only Letters |
        lastNameInPersonalIinformationInputField.sendKeys(lastName);
        //PassWord Required - | minimum 5 char |
        passwordInPersonalIinformationInputField.sendKeys(passwd);

        // | State Required - Option value from 1 to 50 | Country Required - Only USA Available |
        dateReturn = manageDropDownList(Date, stateOfUSA, country);
        errors = dateReturn.errors.stream().collect(Collectors.toList());
        errorsOccured = dateReturn.errorsOccured;

        //--------YOUR ADDRESS FIELDS----------
        //TU SIĘ PIERDOLI!!!!!!!!!!
        firstNameInAddressInputField.sendKeys("aaaaaaa");
        lastNameInAddressInputField.sendKeys("bbbbbbbbb");
        companyNameInAddressInputField.sendKeys("dddddddd");
        address1InAddressInputField.sendKeys("eeeeeeeee");
        city1InAddressInputField.sendKeys("fffffffff");
        postalCodeInAddressInputField.sendKeys("00000");
        mobilePhoneInAddressInputField.sendKeys("1234567890");
        //Checking errors:
        if (!(Pattern.matches("[a-zA-Z]+", firstName)) || !(Pattern.matches("[a-zA-Z]+", lastName)) || !(Pattern.matches(".{5,}", passwd))) {
            errorsOccured = true;
        }
        if (errorsOccured) {
            submitAccountInAddressButton.click();
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
            if (errorsFromBadDataPullingIntoRegisterField.getText().contains("passwd is required.")) {
                errors.add("passwd is required.");
            }
            if (errorsFromBadDataPullingIntoRegisterField.getText().contains("passwd is invalid.")) {
                errors.add("passwd is invalid.");
            }
            if (errorsFromBadDataPullingIntoRegisterField.getText().contains("This country requires you to choose a State.")){
                errors.add("This country requires you to choose a State.");
            }
            if (errorsFromBadDataPullingIntoRegisterField.getText().contains("Country cannot be loaded with address->id_country")){
                errors.add("Country cannot be loaded with address->id_country");
            }
            if (errorsFromBadDataPullingIntoRegisterField.getText().contains("Country is invalid")){
                errors.add("Country is invalid");
            }
        } else {
            errors.add("Data correct");
        }

        return errors;
    }
}
