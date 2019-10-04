package com.qalabs.javabasics.spotify.components.home;

import com.qalabs.javabasics.spotify.components.SpotifyComponent;
import org.openqa.selenium.WebDriver;

public class HomeBodyComponent extends SpotifyComponent {

    public HomeBodyComponent(WebDriver driver) {
        super(driver);

        this.driver = driver;
    }
}
