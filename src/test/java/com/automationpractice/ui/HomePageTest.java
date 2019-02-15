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

import java.util.List;

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
    public void addingAllClothesAndCheckIfThePrizeIsCorrect(){
        driver.get("http://automationpractice.com/index.php");
        Double sumOfPriceCollectincWhenAddingClothes = mainPage.addingAllClothesAndReturnPrice();
        Double totalPriceFromDropDownMenuCart = mainPage.priceFromCartDropDownMenu();
        assertThat(sumOfPriceCollectincWhenAddingClothes).isEqualTo(totalPriceFromDropDownMenuCart).as("The Cost of Clothes sum is Diffrent to the sum in DropDownMenuCart");
    }
    @Test
    public void clickOnEveryLinkInInformationSectionAndCheckIfItsGoesToProperSite(){
        driver.get("http://automationpractice.com/index.php");
        List<String> allLinksBeforeTabsOpen = mainPage.getAllTitlesBeforeOpenTabs();
        List<String> allLinksTitle = mainPage.clickOnEveryLinkInInformationSection();
        assertThat(allLinksTitle).isEqualTo(allLinksBeforeTabsOpen);
    }
    @AfterEach
    public void tearDown () {
        driver.quit();
    }
}
