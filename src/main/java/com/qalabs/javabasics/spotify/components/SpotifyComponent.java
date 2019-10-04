package com.qalabs.javabasics.spotify.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SpotifyComponent {

    protected WebDriver driver;

    public SpotifyComponent(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(this.driver, this);
    }
}
