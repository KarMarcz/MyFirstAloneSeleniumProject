package com.automationpractice.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverProvider {
    private static void startChromeDriver () {
        System.setProperty("webdriver.chrome.driver", ChromeDriverExecutor.getPathToDriver());
    }
    public static final WebDriver WebDriverProvider() {
        startChromeDriver();
        return (WebDriver) new ChromeDriver();
    }
}
