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

import static org.assertj.core.api.Assertions.assertThat;

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
        //size of all buyable clothes
        int numberOfAllProducts = driver.findElements(By.cssSelector("ul[id='homefeatured'] div[class='right-block']")).size();
        Actions action = new Actions(driver);
        //for totalPrice for assertion
        Double totalPrice = 0.00;
        //loop that add all all clothes, check its price and add the Price to totalPrice
        for(int i = 0 ; i < numberOfAllProducts ; i++){
            WebElement element = driver.findElements(By.cssSelector("ul[id='homefeatured'] li")).get(i);
            totalPrice = totalPrice + Double.parseDouble(driver.findElements(By.cssSelector("ul[id='homefeatured'] div[class='right-block'] span[class='price product-price']")).get(i).getText().substring(1));
            action.moveToElement(element).build().perform();
            Thread.sleep(1000);
            driver.findElements(By.cssSelector("ul[id='homefeatured'] div[class='right-block'] a[class='button ajax_add_to_cart_button btn btn-default']")).get(i).click();
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("div[id='layer_cart'] span[class='continue btn btn-default button exclusive-medium']")).click();
        }
        //adding Shipping charge
        if(totalPrice > 0.00){
            totalPrice += 2.00;
        }
        //steps to get the total value from dropDown Cart Menu
        WebElement element1 = driver.findElement(By.cssSelector("div[class='shopping_cart'] a[title='View my shopping cart']"));
        action.moveToElement(element1).build().perform();
        Thread.sleep(5000);
        WebElement totalInCart = driver.findElement(By.xpath("//span[@class='price cart_block_total ajax_block_cart_total']"));
        Double totalPriceInCart = Double.parseDouble(totalInCart.getText().substring(1));
        assertThat(totalPrice).isEqualTo(totalPriceInCart).as("Not Working");
    }
    @AfterEach
    public void tearDown () {
        driver.close();
    }
}
