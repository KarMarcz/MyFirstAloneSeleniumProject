package com.automationpractice.ui;

import com.automationpractice.pageObject.AuteticationPage;
import com.automationpractice.pageObject.CreateAnAccountPage;
import com.automationpractice.pageObject.MainPage;
import com.automationpractice.utils.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;

public class HomePageTest {
    private WebDriver driver;
    private MainPage mainPage;
    private AuteticationPage auteticationPage;
    private CreateAnAccountPage createAnAccountPage;

    @BeforeEach
    public void setUp() {
        driver = WebDriverProvider.WebDriverProvider();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        auteticationPage = new AuteticationPage(driver);
        createAnAccountPage = new CreateAnAccountPage(driver);
    }
    @Test
    public void addingAllClothesAndCheckIfThePrizeIsCorrect() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");
        int numberOfAllProducts = driver.findElements(By.cssSelector("ul[id='homefeatured'] div[class='right-block'] a[class='button ajax_add_to_cart_button btn btn-default']")).size();
//        System.out.println(driver.findElements(By.cssSelector("ul[id='homefeatured'] div[class='right-block'] a[class='button ajax_add_to_cart_button btn btn-default']")).size());
        for(int i = 0 ; i < numberOfAllProducts ; i++){
            Actions action = new Actions(driver);
            WebElement element = driver.findElements(By.cssSelector("ul[id='homefeatured'] li")).get(i);
            action.moveToElement(element).build().perform();
            Thread.sleep(500);
            driver.findElements(By.cssSelector("ul[id='homefeatured'] div[class='right-block'] a[class='button ajax_add_to_cart_button btn btn-default']")).get(i).click();
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("div[id='layer_cart'] span[class='continue btn btn-default button exclusive-medium']")).click();
        }
    }
    @AfterEach
    public void tearDown () {
        driver.close();
    }
}
