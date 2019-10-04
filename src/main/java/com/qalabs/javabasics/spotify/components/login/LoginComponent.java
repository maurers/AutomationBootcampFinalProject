package com.qalabs.javabasics.spotify.components.login;

import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import org.openqa.selenium.WebDriver;

public class LoginComponent extends SpotifyComponent {

    public LoginComponent(WebDriver driver) {
        super(driver);

        this.driver = driver;
    }
}
