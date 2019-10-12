package com.qalabs.seleniumbasics.spotify;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        String browser = "chrome";

        driver = WebDriverFactory.getDriver(browser);
        wait = new WebDriverWait(driver, 5);
        driver.manage().window().maximize();
    }

    @AfterTest(alwaysRun = true)
    public void teardDown() {
        driver.close();
        driver.quit();
    }
}
