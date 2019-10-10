package com.qalabs.javabasics.spotify.components.signup;

import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import org.openqa.selenium.WebDriver;

public class SignUpComponent extends SpotifyComponent {

    public SignUpComponent(WebDriver driver) {
        super(driver);

        this.driver = driver;
    }
}
