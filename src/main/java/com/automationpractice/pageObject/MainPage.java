package com.automationpractice.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    private WebElement singInButton;
}
