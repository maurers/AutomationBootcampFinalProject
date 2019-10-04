package com.qalabs.javabasics.spotify.components.home;

import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import org.openqa.selenium.WebDriver;

public class HomeHeaderComponent extends SpotifyComponent {

    public HomeHeaderComponent(WebDriver driver) {
        super(driver);

        this.driver = driver;
    }
}
