package com.qalabs.seleniumbasics.spotify.e2e;

import com.qalabs.javabasics.spotify.pages.SpotifyHomePage;
import com.qalabs.javabasics.spotify.pages.SpotifyOverviewUserAccountPage;
import com.qalabs.javabasics.spotify.pages.SpotifyLoginPage;
import com.qalabs.seleniumbasics.spotify.BaseTest;
import com.qalabs.seleniumbasics.spotify.utilities.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LoginPageTest extends BaseTest {
    private String spotifyUrl;

    @BeforeMethod
    public void initSetup(){
        PropertyReader propertyReader= new PropertyReader();
        spotifyUrl= propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        driver.navigate().to(spotifyUrl);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(driver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        spotifyHomePage.goToLoginPage();
        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(driver);
        Assert.assertTrue( spotifyLoginPage.isLoaded(), "Página de Login no se cargo correctamente" );

    }
    @Test(description = "TC_US_02_001/Acceder al formulario de Inicio de Sesión de Spotify", alwaysRun = true)
    public void inputLogInSpotify(){

        PropertyReader propertyReader = new PropertyReader();
        String user=propertyReader.getProperty("credentials.properties", "TC_US_02_001_USER_NAME");
        String pass=propertyReader.getProperty("credentials.properties", "TC_US_02_001_PASSWORD");

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(driver);
        spotifyLoginPage.fillSpotifyLogInForm(user,pass);

        List<String> listOfErrors = spotifyLoginPage.getAllSpotifyLogInFormErrorMessages();
        Assert.assertTrue(listOfErrors.isEmpty(), "Bad credentials");

        SpotifyOverviewUserAccountPage spotifyOverviewUserAccountPage = new SpotifyOverviewUserAccountPage(this.driver);
        Assert.assertFalse(spotifyOverviewUserAccountPage.isLoaded(), "Hizo Login");
    }

    @Test(alwaysRun = true,description = "TC_US2_002/Acceder al formulario de Inicio de Sesión de Spotify")
    public void justInputLogInSpotify(){

        PropertyReader propertyReader = new PropertyReader();
        String user=propertyReader.getProperty("credentials.properties", "TC_US_02_002_USER_NAME");
        String pass=propertyReader.getProperty("credentials.properties", "TC_US_02_002_PASSWORD");

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(driver);
        spotifyLoginPage.fillSpotifyLogInForm(user,pass);

        List<String> listOfErrors = spotifyLoginPage.getAllSpotifyLogInFormErrorMessages();
        Assert.assertTrue(listOfErrors.isEmpty(), "Bad credentials");

        SpotifyOverviewUserAccountPage spotifyOverviewUserAccountPage = new SpotifyOverviewUserAccountPage(this.driver);
        Assert.assertFalse(spotifyOverviewUserAccountPage.isLoaded(), "Hizo Login");
    }

    @Test(description = "TC_US2_003 / Acceder al formulario de Inicio de Sesión de Spotify")
    public void incompleteLogInSpotify(){

        PropertyReader propertyReader = new PropertyReader();
        String user=propertyReader.getProperty("credentials.properties", "TC_US_02_003_USER_NAME");
        String pass=propertyReader.getProperty("credentials.properties", "TC_US_02_003_PASSWORD");

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(driver);
        spotifyLoginPage.fillSpotifyLogInForm(user,pass);
        spotifyLoginPage.clickOnLoginButton();

        List<String> listOfErrors = spotifyLoginPage.getAllSpotifyLogInFormErrorMessages();
        Assert.assertTrue(listOfErrors.get(0).contains("Incorrect username or password"), "Mensaje de Error Diferente ");

        SpotifyOverviewUserAccountPage spotifyOverviewUserAccountPage = new SpotifyOverviewUserAccountPage(this.driver);
        Assert.assertFalse(spotifyOverviewUserAccountPage.isLoaded(), "Hizo Login");
    }

    @Test(description = "TC_US2_004/Acceder al formulario de Inicio de Sesión de Spotify ")
    public void blankLogInSpotify(){

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(driver);
        spotifyLoginPage.fillSpotifyLogInForm("", "");
        spotifyLoginPage.clickOnLoginButton();

        List<String> listOfErrors = spotifyLoginPage.getAllSpotifyLogInFormErrorMessages();
        Assert.assertFalse(listOfErrors.isEmpty(), "No arrojo errores");

        SpotifyOverviewUserAccountPage spotifyOverviewUserAccountPage = new SpotifyOverviewUserAccountPage(this.driver);
        Assert.assertFalse(spotifyOverviewUserAccountPage.isLoaded(), "Hizo Login");
    }

    @Test(description = "TC_US2_005 / Acceder al formulario de Inicio de Sesión e ingresar sus credenciales de manera manual, utilizando un username, sin emplear algún método de autenticación de terceros")
    public void validLogInSpotify(){

        PropertyReader propertyReader = new PropertyReader();
        String user=propertyReader.getProperty("credentials.properties", "TC_US_02_005_USER_NAME");
        String pass=propertyReader.getProperty("credentials.properties", "TC_US_02_005_PASSWORD");

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(driver);
        spotifyLoginPage.fillSpotifyLogInForm(user,pass);
        spotifyLoginPage.clickOnLoginButton();

        List<String> listOfErrors = spotifyLoginPage.getAllSpotifyLogInFormErrorMessages();
        Assert.assertTrue(listOfErrors.isEmpty(), "Bad credentials");

        SpotifyOverviewUserAccountPage spotifyOverviewUserAccountPage = new SpotifyOverviewUserAccountPage(this.driver);
        Assert.assertTrue(spotifyOverviewUserAccountPage.isLoaded(), "No pudo hacer login");
    }
}
