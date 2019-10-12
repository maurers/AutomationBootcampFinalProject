package com.qalabs.javabasics.spotify.components.signup;

import com.google.common.base.Strings;
import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpComponent extends SpotifyComponent {

    // Attributes

    private WebDriverWait wait;

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

    private Select monthDropdownElement;
    private List<WebElement> monthOptions;

    @FindBy(how = How.ID, using = "register-dob-day")
    private WebElement dayInput;

    @FindBy(how = How.ID, using = "register-dob-year")
    private WebElement yearInput;

    @FindBy(how = How.XPATH, using = "//input[@type = 'radio']")
    private List<WebElement> genderRadioButtons;

    @FindBy(how = How.ID, using = "register-thirdparty")
    private WebElement shareCheckbox;

    @FindBy(how = How.ID, using = "register-button-email-submit")
    private WebElement signUpButton;

    private String emailErrorMessageXPathLocator = "//label[@for = 'register-email' and @class = 'has-error']";
    private String confirmEmailErrorMessageXPathLocator = "//label[@for = 'register-confirm-email' and @class = 'has-error']";
    private String passwordErrorMessageXPathLocator = "//label[@for = 'register-password' and @class = 'has-error']";
    private String nameErrorMessageXPathLocator = "//label[@for = 'register-displayname' and @class = 'has-error']";
    private String dayErrorMessageXPathLocator = "//label[@for = 'register-dob-day' and @class = 'has-error']";
    private String monthErrorMessageXPathLocator = "//label[@for = 'register-dob-month' and @class = 'has-error']";
    private String yearErrorMessageXPathLocator = "//label[@for = 'register-dob-year' and @class = 'has-error']";
    private String genderErrorMessageXPathLocator = "//label[@for = 'signup_form[gender]' and @class = 'has-error']";
    private String captchaErrorMessageXPathLocator = "//label[@for = 'captcha-hidden' and @class = 'has-error']";

    // Constructor

    public SignUpComponent(WebDriver driver) {
        super(driver);

        wait = new WebDriverWait(driver, 5);
        this.driver = driver;
    }

    // Getters

    public WebElement getEmailInputElement() {
        return this.emailInput;
    }

    public WebElement getNameInputElement() {
        return this.nameInput;
    }

    // Actions

    public void fillSpotifySignUpForm(String email, String pass, String name, String month, String day, String year, String gender) {
        emailInput.clear();
        emailInput.sendKeys(email);

        confirmEmailInput.clear();
        confirmEmailInput.sendKeys(email);

        passInput.clear();
        passInput.sendKeys(pass);

        nameInput.clear();
        nameInput.sendKeys(name);

        dayInput.clear();
        dayInput.sendKeys(day);

        monthDropdownElement = new Select(monthDropdown);
        monthOptions = monthDropdownElement.getOptions();
        for (WebElement item : monthOptions) {
            if(item.getText().toLowerCase().equals(month.toLowerCase())) {
                monthDropdownElement.selectByVisibleText(month);
            }
        }

        yearInput.clear();
        yearInput.sendKeys(year);

        for (WebElement item : genderRadioButtons) {
            item.clear();
            if(item.getAttribute("value").equals(gender)) {
                item.click();
            }
        }

        shareCheckbox.clear();
        shareCheckbox.click();
    }

    public void clickOnRegistrateButton() {
        signUpButton.click();
    }

    public List<String> validateSpotifySignUpEmptyForm(String email, String pass, String name, String month, String day, String year, String gender) {
        List<String> errors = null;
        
        if(Strings.isNullOrEmpty(email) && Strings.isNullOrEmpty(pass) && Strings.isNullOrEmpty(name) && Strings.isNullOrEmpty(month)
                && Strings.isNullOrEmpty(day) && Strings.isNullOrEmpty(year) && Strings.isNullOrEmpty(gender)) {
            errors = new ArrayList<String>() {{
                add(getEmailErrorMessageElement().getText());
                add(getConfirmEmailErrorMessageElement().getText());
                add(getPasswordErrorMessageElement().getText());
                add(getNameErrorMessageElement().getText());
                add(getDayErrorMessageElement().getText());
                add(getMonthErrorMessageElement().getText());
                add(getYearErrorMessageElement().getText());
                add(getGenderErrorMessageElement().getText());
                add(getCaptchaErrorMessageElement().getText());
            }};
        }

        return errors;
    }

    public List<String> validateSpotifySignUpInvalidDayForm(String email, String pass, String name, String month, String day, String year, String gender) {
        List<String> errors = null;

        if(!Strings.isNullOrEmpty(email) && !Strings.isNullOrEmpty(pass) && !Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(month)
                && !Strings.isNullOrEmpty(day) && !Strings.isNullOrEmpty(year) && !Strings.isNullOrEmpty(gender)) {
            if((isInteger(day) && (Integer.parseInt(day) <= 0 || Integer.parseInt(day) > 31)) || !isInteger(day)) {
                errors = new ArrayList<String>() {{
                    add(getDayErrorMessageElement().getText());
                    add(getCaptchaErrorMessageElement().getText());
                }};
            }
        }

        return errors;
    }

    public List<String> validateSpotifySignUpValidForm(String email, String pass, String name, String month, String day, String year, String gender) {
        List<String> errors = null;

        if(!Strings.isNullOrEmpty(email) && !Strings.isNullOrEmpty(pass) && !Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(month)
                && !Strings.isNullOrEmpty(day) && !Strings.isNullOrEmpty(year) && !Strings.isNullOrEmpty(gender)) {
            errors = new ArrayList<String>() {{
                add(getCaptchaErrorMessageElement().getText());
            }};
        }

        return errors;
    }

    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    // Setting dynamic elements

    private WebElement getEmailErrorMessageElement() {
        return driver.findElement(By.xpath(emailErrorMessageXPathLocator));
    }

    private WebElement getConfirmEmailErrorMessageElement() {
        return driver.findElement(By.xpath(confirmEmailErrorMessageXPathLocator));
    }

    private WebElement getPasswordErrorMessageElement() {
        return driver.findElement(By.xpath(passwordErrorMessageXPathLocator));
    }

    private WebElement getNameErrorMessageElement() {
        return driver.findElement(By.xpath(nameErrorMessageXPathLocator));
    }

    private WebElement getDayErrorMessageElement() {
        return driver.findElement(By.xpath(dayErrorMessageXPathLocator));
    }

    private WebElement getMonthErrorMessageElement() {
        return driver.findElement(By.xpath(monthErrorMessageXPathLocator));
    }

    private WebElement getYearErrorMessageElement() {
        return driver.findElement(By.xpath(yearErrorMessageXPathLocator));
    }

    private WebElement getGenderErrorMessageElement() {
        return driver.findElement(By.xpath(genderErrorMessageXPathLocator));
    }

    private WebElement getCaptchaErrorMessageElement() {
        return driver.findElement(By.xpath(captchaErrorMessageXPathLocator));
    }
}
