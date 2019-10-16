/**
 * This class was created to validate
 * if the login was successfully
 */

package com.qalabs.javabasics.spotify.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

public class SpotifyOverviewUserAccountPage extends BasePage {

    private WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//button[@ng-click='logout()']")
    private WebElement logoutButton;

    public static final String BASE_URL = "https://accounts.spotify.com/es/status/";

    public SpotifyOverviewUserAccountPage(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
    }

    public SpotifyHomePage clickOnSpotifyIcon() {
        return null;
    }

    @Override
    public boolean isLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(logoutButton));

            return logoutButton.isDisplayed();
        } catch (Exception ex) {
            Log.error(ex.toString());

            return false;
        }
    }
}
