package com.qalabs.seleniumbasics.spotify;

import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qalabs.javabasics.spotify.components.*;
import com.qalabs.javabasics.spotify.page.*;



public class SpotifyTest extends BaseTest {

    private String spotifyUrl;
    @BeforeTest
    public void initSetup(){
        PropertyReader propertyReader = new PropertyReader();
        this.spotifyUrl = propertyReader.getProperty("credentials.properties", "TEST_URL");
    }


    @Test(description = "TC_US5_001 / Ir a la pagina de soporte tecnico atraves del link de Ayuda en el Header")
    // Mi duda es... si el link a support esta en el header..
    // no seria mejor hacer las pruebas directo al header y evitar duplicar codigo pra probar desde cada URL
    public void HelpPageLoadingCheck() {
        PropertyReader propertyReader = new PropertyReader();
        String helpPageUrl = propertyReader.getProperty("credentials.properties", "URL_HELP");
        String homePageUrl = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");

        //instantiate  a SpotifyHomePage with myDriver
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página de ayuda no se cargo correctamente");


        //Ininstantiate SpotifyHelpPage with the result of the spotifyHomePage.goToHelpPage method
        SpotifyHelpPage spotifyHelpPage = spotifyHomePage.goToHelpPage();

        //checks if spotifyHelpPage has finish loading with the isLoaded method and its BASE_URL
        Assert.assertTrue(spotifyHelpPage.isLoaded(), "Página de ayuda no se cargo correctamente");
        //Checks if the URL of mydrive is the same as the one in credentials
        Assert.assertTrue(myDriver.getCurrentUrl().startsWith(helpPageUrl));
    }


    // assert homepage


    @Test(description = "TC_US2_004/Acceder al formulario de Inicio de Sesión de Spotify ")
    public void LoginSpotifyCheck(){
        //this.myDriver.navigate().to(spotifyUrl);
        PropertyReader propertyReader = new PropertyReader();
        String pageLoginUrl = propertyReader.getProperty("credentials.properties","URL_LOGIN_PAGE");

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        SpotifyLoginPage spotifyLoginPage = spotifyHomePage.goToLoginPage();

        //checks if spotifyLoginPage has finish loading with the isLoaded method and its BASE_URL
        Assert.assertTrue(spotifyLoginPage.isLoaded(), "Página de login no se cargo correctamente");
        //Checks if the URL of mydrive is the same as the one in credentials
        Assert.assertEquals(pageLoginUrl,myDriver.getCurrentUrl());
    }
    @Test(description = "TC_US2_004/Verificar informacion de login")
    public void validLoggerInSpotify(){

        PropertyReader propertyReader = new PropertyReader();

        String email = propertyReader.getProperty("credentials.properties", "EMAIL_ADDRESS");
        String pass = propertyReader.getProperty("credentials.properties", "PASSWORD");
        String pageLoginUrl = propertyReader.getProperty("credentials.properties","URL_LOGIN_PAGE");

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(this.myDriver);
        spotifyLoginPage = spotifyLoginPage.spotifyLoginForm(email,pass);

        //Hardcoded url for account login

        Assert.assertEquals(myDriver.getCurrentUrl(),"https://accounts.spotify.com/es/status");

    }

}