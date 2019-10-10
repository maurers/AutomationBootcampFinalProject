package com.qalabs.javabasics.spotify.pages;

import com.qalabs.javabasics.spotify.components.signup.SignUpComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
    List<WebElement> Options = monthDropdown.getOption();
        for (WebElement option:Options){


    }

    @FindBy(how = How.ID, using = "")
    private WebElement monthSelect;

    @FindBy(how = How.ID, using = "register-dob-day")
    private WebElement dayInput;

    @FindBy(how = How.ID, using = "register-dob-year")
    private WebElement yearInput;

    @FindBy(how = How.XPATH, using = "//input[@type='radio']")
    private List<WebElement> genderRadioButtons;

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

    public SpotifySignUpPage passInput(String pass){
        this.passInput.sendKeys(pass);
        return null;
    }

    public SpotifySignUpPage nameInput(String name){
        this.nameInput.sendKeys(name);
        return null;
    }
    public SpotifySignUpPage shareCheckBox(boolean shareCheckBox){
        this.shareCheckBox.isSelected();
        return boolean
    }
    public SpotifySignUpPage signUpButton(){
        this.signUpButton.click();
    }
    }

}
