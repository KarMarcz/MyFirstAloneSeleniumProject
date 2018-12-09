package com.automationpractice.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy (id = "postcode")
    private WebElement postalCodeInAddressInputField;
    @FindBy (id = "phone_mobile")
    private WebElement mobilePhoneInAddressInputField;
    @FindBy (id = "submitAccount")
    private WebElement submitAccountInAddressButton;
}
