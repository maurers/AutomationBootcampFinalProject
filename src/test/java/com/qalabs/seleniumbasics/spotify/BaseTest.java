package com.qalabs.seleniumbasics.spotify;

import com.qalabs.seleniumbasics.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

    public class BaseTest {
        protected WebDriver myDriver;
        @BeforeClass(alwaysRun=true)
        public void setup(){
            //define which browser to use
            String browser= "chrome";
            //get corrent driver for disere browser
            myDriver= WebDriverFactory.getDriver(browser);
        }
        @AfterClass(alwaysRun=true)
        public void tearDown(){
            myDriver.close();
            myDriver.quit();
        }
    }

