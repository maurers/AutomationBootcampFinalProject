package com.qalabs.javabasics.spotify.pages;

import com.qalabs.javabasics.spotify.components.signup.SignUpComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpotifySignUpPage extends BasePage {

    private WebDriverWait wait;
    private SignUpComponent signUpComponent;

    public final static String BASE_URL = "";

    public SpotifySignUpPage(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
        this.signUpComponent = new SignUpComponent(driver);
    }

    // ToDO: Implement this method
    public SpotifyHomePage clickOnSpotifyIcon() {
        return null;
    }

    @FindBy(how = How.ID, using = "register-email")
    private WebElement emailInput;

    @FindBy(how = How.ID, using = "register-confirm-email")
    private WebElement confirmEmailInput;

    @FindBy(how = How.ID, using = "register-password")
    private WebElement passInput;

    @FindBy(how = How.ID, using = "register-displayname")
    private WebElement nameInput;

    @FindBy(how = How.ID, using = "register-dob-month")
    private WebElement monthDropdown;

    @FindBy(how = How.ID, using = "")
    private WebElement monthSelect;

    @FindBy(how = How.ID, using = "register-dob-day")
    private WebElement dayInput;

    @FindBy(how = How.ID, using = "register-dob-year")
    private WebElement yearInput;

    @FindBy(how = How.ID, using = "")
    private WebElement genderRadioButtons;

    @FindBy(how = How.ID, using = "register-thirdparty")
    private WebElement ShareCheckBox;

    @FindBy(how = How.ID, using = "register-button-email-submit")
    private WebElement signUpButton;



    public SpotifySignUpPage emailInput(String email) {

        this.emailInput.sendKeys(email);
        return null;
    }

    public SpotifySignUpPage confirmEmailInput(String email){
        this.confirmEmailInput.sendKeys(email);
        return null;
    }
    

}
