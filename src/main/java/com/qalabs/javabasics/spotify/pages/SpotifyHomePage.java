package com.qalabs.javabasics.spotify.pages;

import com.qalabs.javabasics.spotify.components.home.HomeBodyComponent;
import com.qalabs.javabasics.spotify.components.home.HomeHeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

public class SpotifyHomePage extends BasePage {

    // Attributes

    private WebDriverWait wait;
    private HomeHeaderComponent homeHeaderComponent;
    private HomeBodyComponent homeBodyComponent;

    public final static String BASE_URL = "";

    // Elements

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'mh-brand-wrapper')]/a[@href='https://www.spotify.com/mx/']")
    private WebElement spotifyLogoElement;

    // Constructor

    public SpotifyHomePage(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
        this.homeHeaderComponent = new HomeHeaderComponent(driver);
        this.homeBodyComponent = new HomeBodyComponent(driver);
    }

    // Actions

    @Override
    public boolean isLoaded() {
        wait.until(ExpectedConditions.visibilityOf(this.homeHeaderComponent.getLoginButtonElement()));

        try {
            return this.homeHeaderComponent.getLoginButtonElement().isDisplayed();
        } catch(Exception ex) {
            Log.error(ex.toString());

            return false;
        }
    }

    public SpotifyHelpPage goToHelPage() {
        this.homeHeaderComponent.clickOnHelpButton();

        return new SpotifyHelpPage(this.driver);
    }

    public SpotifySignUpPage goToSignUpPage() {
        this.homeHeaderComponent.clickOnSignUpButton();

        return new SpotifySignUpPage(this.driver);
    }

    public SpotifySignUpPage goToGetSpotify() {
        this.homeBodyComponent.clickOnGetSpotifyButton();

        return new SpotifySignUpPage(this.driver);
    }

    public SpotifyLoginPage goToLoginPage() {
        this.homeHeaderComponent.clickOnLoginButton();

        return new SpotifyLoginPage(this.driver);
    }

    // ToDO: Implement this method
    public SpotifyHomePage clickOnSpotifyIcon() {
        this.spotifyLogoElement.click();

        return new SpotifyHomePage(this.driver);
    }
}
