package com.qalabs.seleniumbasics.spotify;

import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SpotifyTest extends BaseTest{
    private String spotifyUrl;

    @BeforeTest
    public void initSetup(){
        PropertyReader propertyReader= new PropertyReader();
        spotifyUrl= propertyReader.getProperty("test.properties", "TEST_URL");
    }
    @Test(alwaysRun = true,description = "TC_US2_003/Acceder al formulario de Inicio de Sesión de Spotify")
    public void validLoggerInSpotify(){
        String email;
        String pass;
        String pageLogin;
        myDriver.navigate().to(spotifyUrl);
        PropertyReader propertyReader = new PropertyReader();
        email=propertyReader.getProperty("credentials.properties", "EMAIL_ADDRESS");
        pass=propertyReader.getProperty("credentials.properties", "PASSWORD");
        pageLogin=propertyReader.getProperty("credentials.properties", "URL_LOGIN_PAGE");
        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(myDriver);
        sporifyLoginPage.spotifyLoginPageFrom(email,pass);
        Assert.assertTrue(sporifyLoginPage.isLoaded(), "The page is not loaded");
        Assert.assertFalse(page.startsWith(myDriver.getCurrentUrl()));
    }

    @Test(alwaysRun = true,description = "TC_US4_001/ Llenar el formulario para crear cuenta nueva")
        public void invalidSignUpInInSpotify() {
        String email;
        String pass;
        String name;
        String day ;
        String month;
        String year;
        String pageSingUp;

        myDriver.navigate().to(spotifyUrl);
        PropertyReader propertyReader = new PropertyReader();
        email=propertyReader.getProperty("credentials.properties", "EMAIL");
        pass=propertyReader.getProperty("credentials.properties", "PASSWORD");
        name=propertyReader.getProperty("credentials.properties","NAME");
        day=propertyReader.getProperty("credentials.properties", "DAY");
        month=propertyReader.getProperty("credentials.properties","MONTH");
        year=propertyReader.getProperty("credentials.properties", "YEAR_VALID");
        pageSingUp=propertyReader.getProperty("credentials.properties", "URL_SIGNUP_PAGE");

        SpotifySignUpPage spotifySingUpPage = new SpotifySingUpPage(myDriver);
        spotifySingUpForm.sidnUpAccount(email,pass,name,day,month,year);
        Assert.assertTrue( SpotifySignUpPage.isLoaded(), "The page is not loaded");
        Assert.assertFalse(pageSingUp.startsWith(myDriver.getCurrentUrl()));

    }

}
