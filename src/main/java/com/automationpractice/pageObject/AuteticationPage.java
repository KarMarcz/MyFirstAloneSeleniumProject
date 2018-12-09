package com.automationpractice.pageObject;

import com.automationpractice.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuteticationPage {
    @FindBy(id = "email_create")
    private WebElement emailAddressInputForRegistrationField;
    @FindBy(id = "SubmitCreate")
    private WebElement createAnAccountButton;

    private WebDriver driver;
    private Waits waits;
    private String email = "asda@kjn.bh";

    public AuteticationPage(WebDriver driver){
        this.driver = driver;
        waits = new Waits(driver);

        PageFactory.initElements(driver,this);
    }

    public void fillEmailFieldAndClickcreateAnAccountButton(){
        waits.waitForElementToBeClickable(createAnAccountButton);
        emailAddressInputForRegistrationField.sendKeys(email);
        createAnAccountButton.click();
    }
}
