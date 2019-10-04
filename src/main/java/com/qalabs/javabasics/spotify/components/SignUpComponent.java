package com.qalabs.javabasics.spotify.components;

import com.qalabs.javabasics.spotify.page.BasePage;
import com.qalabs.javabasics.spotify.page.SpotifySignUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignUpComponent extends BasePage {

    public static final String SPOTSIGNUP_URL = "https://www.spotify.com/mx/signup/";

    @FindBy(how = How.NAME, using = "register-email")
    private WebElement emailInput;
    @FindBy(how = How.ID, using = "register-confirm-email")
    private WebElement confirmEmailInput;
    @FindBy(how = How.ID, using = "register-displayname")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "register-password")
    private WebElement passInput;
    @FindBy(how = How.ID, using = "register-dob-day")
    private WebElement dayInput;
    @FindBy(how = How.ID, using = "register-dob-month")
    private WebElement monthDropdown;
    private Select monthSelect = new Select(monthDropdown);
    @FindBy(how = How.ID, using = "register-dob-year")
    private WebElement yearInput;
    @FindBy(how = How.NAME, using = "signup_form[gender]")
    private List<WebElement> genderRadioButtons;
    @FindBy(how = How.ID, using = "register-thirdparty")
    private WebElement shareCheckBox;
    @FindBy(how = How.ID, using = "register-button-email-submit")
    private WebElement btnRegister;

    public SignUpComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public SpotifySignUpPage signUpAccount(String email, String pass, String name, String month,
                                           String day, String year, String gender) {

        emailInput.sendKeys(email);
        confirmEmailInput.sendKeys(email);
        passInput.sendKeys(pass);
        nameInput.sendKeys(name);
        dayInput.sendKeys(day);
        monthSelect.selectByVisibleText(month);
        yearInput.sendKeys(year);
        for(WebElement item: genderRadioButtons){
            if (gender == "female" && item.getAttribute("id").contains("register-female")){
                item.click();
            }
            else if(gender == "male" && item.getAttribute("id").contains("register-male")){
                item.click();
            }
            else{
                item.click();
            }
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return new SpotifySignUpPage(this.driver);
    }

    public void spotifySignUpform(String name, String pass, String email, String day, String month, String year, String gender) {
    }
}