package com.qalabs.seleniumbasics.spotify;

import com.qalabs.javabasics.spotify.page.SpotifySignUpPage;
import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SpotifyTest  extends BaseTest {
    private String spotifyUrl = null;
    private String spotifySignUpUrl= null;
    private String spotifyLoginUrl = null

    @BeforeTest
    public void initSetup(){
        com.qalabs.seleniumbasics.utils.PropertyReader propertyReader= new com.qalabs.seleniumbasics.utils.PropertyReader();
        spotifyUrl= propertyReader.getProperty("credentials.properties", "SPOTIFY_URL");
        spotifySignUpUrl= propertyReader.getProperty("credentials.properties", "SPOTIFY_SIGNUP_URL");
        spotifyLoginUrl = propertyReader.getProperty("credentials.properties", "SPOTIFY_LOGIN_URL");
    }

    @Test(description = "TC_US2_003 / Acceder al formulario de Inicio de Sesión de Spotify")
    public void loggerInSpotify() {
        String name;
        String pass;

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(this.myDriver);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this,myDriver);

        myDriver.navigate().to(spotifyUrl);
        //Assert.assertTrue(facebookLoginPage.isLoaded(), "Google results page is not loaded");
        Assert.assertEquals(myDriver.getCurrentUrl(), SpotifyHomePage.BASE_URL);


        com.qalabs.seleniumbasics.utils.PropertyReader propertyReader= new PropertyReader();
        name= propertyReader.getProperty("credentials.properties", "TC_US2_003_USSER_NAME");
        pass=propertyReader.getProperty("credentials.properties", "TC_US2_003_PASSWORD");

        //myDriver.navigate().to(spotifyLoginUrl);
        SpotifyHomePage.goToLoginPage();
        Assert.assertEquals(myDriver.getCurrentUrl(), SpotifyLoginPage.LOGIN_URL);

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(myDriver);
        SpotifyLoginPage.spotifyLoginForm(name,pass);
        Assert.assertTrue(myDriver.getCurrentUrl().startsWith(spotifyLoginUrl),"No se realizo el login");
    }

    @Test(description = "TC_US4_002 / Formulario de Crear cuenta nueva no se envia por dia de nacimiento invalido")
    public void loggerInSpotifySignUp() {
        String name= null;
        String pass = null;
        String email=null;
        String day= null;
        String year= null;
        String month= null;
        String gender= null;
        com.qalabs.seleniumbasics.utils.PropertyReader propertyReader= new PropertyReader();
        name= propertyReader.getProperty("credentials.properties", "TC_US4_002_USSER_NAME");
        pass=propertyReader.getProperty("credentials.properties", "TC_US4_002_PASSWORD");
        email=propertyReader.getProperty("credentials.properties", "TC_US4_002_EMAIL");
        day=propertyReader.getProperty("credentials.properties", "TC_US4_002_DAY");
        month=propertyReader.getProperty("credentials.properties", "TC_US4_002_MONTH");
        year=propertyReader.getProperty("credentials.properties", "TC_US4_002_YEAR");
        gender=propertyReader.getProperty("credentials.properties", "TC_US4_002_GENDER");
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.myDriver);

        myDriver.navigate().to(spotifyUrl);
        //Assert.assertTrue(facebookLoginPage.isLoaded(), "Google results page is not loaded");
        Assert.assertEquals(myDriver.getCurrentUrl(), SpotifyHomePage.BASE_URL);

        SpotifyHomePage.goToSignUpPage();
        //Assert.assertTrue(facebookLoginPage.isLoaded(), "Google results page is not loaded");
        Assert.assertEquals(myDriver.getCurrentUrl(), SpotifySignUpPage.SPOTIFY_SIGNUP_URL);



        spotifySignUpPage.spotifySignUpform(name,pass,email,day,month,year,gender);
        Assert.assertNotEquals(myDriver.getCurrentUrl(),SpotifySignUpPage.SPOTIFY_SIGNUP_URL);
    }


}
