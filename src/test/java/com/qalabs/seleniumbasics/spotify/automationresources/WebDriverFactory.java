package com.qalabs.seleniumbasics.spotify.automationresources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Log;

import static com.qalabs.seleniumbasics.spotify.utilities.OperatingSystem.getOperatingSystem;

import java.io.File;

public class WebDriverFactory {

    public static WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case Chrome:
                return getChromeDriver();
            case Firefox:
                return getFirefoxDriver();
            default:
                Log.error("No such browser exists!");
                return  null;
        }
    }

    private static WebDriver getChromeDriver() {
        File rootPath = new File("src/test/resources/lib-thirdparty/driversforwin");
        File chromeFilePath;
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");

        switch (getOperatingSystem()) {
            case WINDOWS:
                chromeFilePath = new File(rootPath, "chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", chromeFilePath.getPath());
            break;
            case MAC:
                chromeFilePath = new File(rootPath, "chromedriver");
                System.setProperty("webdriver.chrome.driver", chromeFilePath.getPath());
            break;
            default:
                Log.error("Unknown Operating System!");
            break;
        }

        return new ChromeDriver(options);
    }

    private static WebDriver getFirefoxDriver() {
        File rootPath = new File("src/test/resources/lib-thirdparty/driversforwin");
        File firefoxFilePath;

        switch (getOperatingSystem()) {
            case WINDOWS:
                firefoxFilePath = new File(rootPath, "geckodriver.exe");
                System.setProperty("webdriver.gecko.driver", firefoxFilePath.getPath());
            break;
            case MAC:
                firefoxFilePath = new File(rootPath, "geckodriver");
                System.setProperty("webdriver.gecko.driver", firefoxFilePath.getPath());
            break;
            default:
                Log.error("Unknown Operating System!");
            break;
        }

        return new FirefoxDriver();
    }
}
