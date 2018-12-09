package com.automationpractice.ui;

import com.automationpractice.pageObject.AuteticationPage;
import com.automationpractice.pageObject.CreateAnAccountPage;
import com.automationpractice.pageObject.MainPage;
import com.automationpractice.utils.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationTest {
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
    public void registrationTest () {
        driver.get("http://automationpractice.com/index.php");
        mainPage.clickOnSingInButton();
        auteticationPage.fillEmailFieldAndClickcreateAnAccountButton();
        createAnAccountPage.fillingPersonalInformationAndAddressAndClickRegisterButton();
    }
    @AfterEach
    public void tearDown () {
        driver.close();
    }
}
