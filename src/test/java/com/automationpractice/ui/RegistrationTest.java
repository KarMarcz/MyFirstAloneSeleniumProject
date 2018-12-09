package com.automationpractice.ui;

import com.automationpractice.utils.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class RegistrationTest {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        driver = WebDriverProvider.WebDriverProvider();
        driver.manage().window().maximize();
    }
    @Test
    public void registrationTest () {
        driver.get("http://automationpractice.com/index.php");
    }
    @AfterEach
    public void tearDown () {
        driver.close();
    }
}
