package com.automationpractice.pageObject;

import com.automationpractice.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.regex.Pattern;

public class AuteticationPage {
    @FindBy(id = "email_create")
    private WebElement emailAddressInputForRegistrationField;
    @FindBy(id = "SubmitCreate")
    private WebElement createAnAccountButton;
    @FindBy(id = "create_account_error")
    private WebElement errorSyntaxEmail;

    private WebDriver driver;
    private Waits waits;

    public AuteticationPage(WebDriver driver){
        this.driver = driver;
        waits = new Waits(driver);

        PageFactory.initElements(driver,this);
    }

    public String fillEmailFieldAndClickcreateAnAccountButton(String email){
        if(Pattern.matches(".*@.*\\..*",email)) {
            waits.waitForElementToBeClickable(createAnAccountButton);
            emailAddressInputForRegistrationField.sendKeys(email);
            createAnAccountButton.click();
            return "Syntax correct for Email";
        }
        else{
            waits.waitForElementToBeClickable(createAnAccountButton);
            emailAddressInputForRegistrationField.sendKeys(email);
            createAnAccountButton.click();
            waits.waitForElementToBeVisible(errorSyntaxEmail);
            return errorSyntaxEmail.getText();
        }
    }
}
