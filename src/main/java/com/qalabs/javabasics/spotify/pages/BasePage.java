package com.qalabs.javabasics.spotify.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage implements Page {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public void open() {
        return;
    }

    public boolean isLoaded() {
        return false;
    }

    public void close() {
        this.driver.close();
        this.driver.quit();
    }

    public abstract SpotifyHomePage clickOnSpotifyIcon();
}
