package com.automationpractice.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waits {
    private static final int EXPLICIT_WAIT_TIMEOUT = 5;
    private WebDriver driver;
    public Waits(WebDriver driver){
        this.driver = driver;
    }

    public void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

   public void implicitWait(){
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
   }

    public void waitForElementToBeVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
