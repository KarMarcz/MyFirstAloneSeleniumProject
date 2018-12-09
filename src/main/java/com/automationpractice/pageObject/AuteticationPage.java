package com.automationpractice.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuteticationPage {
    @FindBy(id = "email_create")
    private WebElement emailAddressInputForRegistrationField;
    @FindBy(id = "SubmitCreate")
    private WebElement createAnAccountButton;


}
