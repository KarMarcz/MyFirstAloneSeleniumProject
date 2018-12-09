package com.automationpractice.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chrome/chromedriver_win32.exe");
        driver = new ChromeDriver();
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
