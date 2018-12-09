package com.automationpractice.pageObject;

import com.automationpractice.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    private WebElement singInButton;

    private WebDriver driver;
    private Waits waits;

    public MainPage (WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);

        PageFactory.initElements(driver,this);
    }
    public void clickOnSingInButton(){
        waits.waitForElementToBeClickable(singInButton);
        singInButton.click();
    }
}
