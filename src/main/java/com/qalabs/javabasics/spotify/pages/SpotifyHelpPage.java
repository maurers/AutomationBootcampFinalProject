package com.qalabs.javabasics.spotify.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class SpotifyHelpPage extends BasePage {

    // Attributes

    private WebDriverWait wait;

    public final static String BASE_URL = "https://support.spotify.com/mx/?_ga=2.47006823.1304134064.1570227736-386534741.1570227736";

    // Elements

    @FindBy(how = How.ID, using = "nav-link-help")
    private WebElement spHelpElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'navbar-logo')]/a[@href='http://www.spotify.com/mx/?_ga=2.20450680.1304134064.1570227736-386534741.1570227736']")
    private WebElement spIconLogo;


    // Constructor

    public SpotifyHelpPage(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
    }

    // Actions

    public boolean isLoaded() {
        wait.until(elementToBeClickable(spHelpElement));

        try {
            return this.spHelpElement.isDisplayed();
        } catch(RuntimeException ex) {
            Log.error(ex.toString());

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