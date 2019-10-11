package com.qalabs.javabasics.spotify.pages;

import com.qalabs.javabasics.spotify.components.signup.SignUpComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.util.List;

public class SpotifySignUpPage extends BasePage {

    // Attributes

    private WebDriverWait wait;
    private SignUpComponent signUpComponent;
    public final static String BASE_URL = "https://www.spotify.com/mx/signup/";

    // Elements

    @FindBy(how = How.XPATH, using = "//div[@class = 'l-box-content']/descendant::a[@class = 'spotify-logo' and @href = '/mx/']")
    private WebElement spotifyLogoElement;

    // Constructor

    public SpotifySignUpPage(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
        this.signUpComponent = new SignUpComponent(driver);
    }

    // Actions

    public boolean isLoaded() {
        wait.until(ExpectedConditions.visibilityOf(this.signUpComponent.getEmailInputElement()));

        try {
            return this.signUpComponent.getEmailInputElement().isDisplayed();
        } catch(Exception ex) {
            Log.error(ex.toString());

            return false;
        }
    }

    public void fillSpotifySignUpForm(String email, String pass, String name, String month, String day, String year, String gender) {
        this.signUpComponent.fillSpotifySignUpForm(email, pass, name, month, day, year, gender);
    }

    public void clickOnRegistrateButton() {
        this.signUpComponent.clickOnRegistrateButton();
    }

    public List<String> validateSpotifySignUpForm(String email, String pass, String name, String month, String day, String year, String gender) {
        return this.signUpComponent.validateSpotifySignUpForm(email, pass, name, month, day, year, gender);
    }

    // ToDO: Implement this method
    public SpotifyHomePage clickOnSpotifyIcon() {
        this.spotifyLogoElement.click();

        return new SpotifyHomePage(this.driver);
    }
}
