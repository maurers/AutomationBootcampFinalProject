package com.qalabs.javabasics.spotify.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import utils.Log;

public class SpotifyHelpPage extends BasePage {
    @FindBy(how = How.ID, using = "nav-link-help")
    private WebElement spHelpElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'navbar-logo')]/a[@href='http://www.spotify.com/mx/?_ga=2.20450680.1304134064.1570227736-386534741.1570227736']")
    private WebElement spIconLogo;

    private WebDriverWait wait;
    public final static String BASE_URL = "https://support.spotify.com/mx/?_ga=2.47006823.1304134064.1570227736-386534741.1570227736";

    public SpotifyHelpPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 5);
    }

    public boolean isLoaded() {
        try {
            wait.until(elementToBeClickable(spHelpElement));
            return true;
        } catch(RuntimeException exception) {
            System.out.println("Spotify Help page is not loaded");
            return false;
        }
    }
    // ToDO: Implement this method
    public SpotifyHomePage clickOnSpotifyIcon() {
        if(spIconLogo.isDisplayed()) {
            spIconLogo.click();
            return new SpotifyHomePage(driver);
        }
        return null;
    }
}
