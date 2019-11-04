package com.qalabs.seleniumbasics.spotify;

import com.qalabs.seleniumbasics.spotify.automationresources.BrowserType;
import com.qalabs.seleniumbasics.spotify.automationresources.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.Log;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Log.info("I am in Before Method! Test is starting!");

        driver = WebDriverFactory.getDriver(BrowserType.Chrome);
        wait = new WebDriverWait(driver, 5);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void teardDown() {
        Log.info("I am in After Method! Test is ending!");

        driver.close();
        driver.quit();
    }
}
