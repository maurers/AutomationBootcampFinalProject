package com.qalabs.javabasics.spotify.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qalabs.javabasics.spotify.components.SignUpComponent;

public class SpotifySignUpPage extends BasePage {
    public static final String SPOTIFY_SIGNUP_URL = "https://www.spotify.com/mx/signup";
    private SignUpComponent signUpComponent;
    public SpotifySignUpPage(WebDriver driver) {
        super(driver);
    }
    public SpotifySignUpPage spotifySignUpform(String name, String pass, String email, String day,
                                               String month, String year, String gender){
        this.signUpComponent.spotifySignUpform(name,pass,email,day,month,year,gender);
        return new SpotifySignUpPage(this.driver);
    }
}
