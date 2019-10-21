package com.qalabs.seleniumbasics.spotify.e2e;

import com.qalabs.javabasics.spotify.pages.SpotifyHomePage;
import com.qalabs.javabasics.spotify.pages.SpotifySignUpPage;
import com.qalabs.seleniumbasics.spotify.BaseTest;
import com.qalabs.seleniumbasics.spotify.utilities.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GetSpotifyTest extends BaseTest{
    private String spotifyUrl;

    @BeforeMethod
    public void initSetup(){
        PropertyReader propertyReader= new PropertyReader();
        spotifyUrl= propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        driver.navigate().to(spotifyUrl);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(driver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        spotifyHomePage.goToGetSpotify();
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(driver);
        Assert.assertTrue( spotifySignUpPage.isLoaded(), "Página de Login no se cargo correctamente" );
    }

    @Test(description = "TC_US3_001 / Llenar campos de registros de obtén Spotify gratis se envían vacíos")
    public void freeSpotifyEmptyFields() {
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.driver);
        spotifySignUpPage.fillSpotifySignUpForm("", "", "", "", "", "", "");
        spotifySignUpPage.clickOnRegistrateButton();
        List<String> listOfErrors = spotifySignUpPage.getAllSpotifySignUpFormErrorMessages();

        Assert.assertFalse(listOfErrors.isEmpty(), "No arrojo errores");
    }

    @Test(description = "TC_US3_002/Campos de registro de obtén spotify gratis se envían llenos y sin validar captcha", alwaysRun = true, priority = 0)
    public void spotifySignUpNoCaptcha(){

        PropertyReader propertyReader = new PropertyReader();
        String email=propertyReader.getProperty("credentials.properties", "TC_US_03_002_EMAIL");
        String pass=propertyReader.getProperty("credentials.properties", "TC_US_03_002_PASSWORD");
        String name=propertyReader.getProperty("credentials.properties","TC_US_03_002_NAME");
        String day=propertyReader.getProperty("credentials.properties", "TC_US_03_002_DAY");
        String month=propertyReader.getProperty("credentials.properties","TC_US_03_002_MONTH");
        String year=propertyReader.getProperty("credentials.properties", "TC_US_03_002_YEAR");
        String gender=propertyReader.getProperty("credentials.properties","TC_US_03_002_GENDER");

        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(driver);
        spotifySignUpPage.fillSpotifySignUpForm(email,pass,name,month,day,year,gender);
        spotifySignUpPage.clickOnRegistrateButton();
        List<String> listOfErrors = spotifySignUpPage.getAllSpotifySignUpFormErrorMessages();

        Assert.assertTrue(listOfErrors.get(0).contains("Confirma que no eres un robot."), "Mensaje de Error Diferente al de Captcha");
    }

    @Test(description = "TC_US3_003/Campos de registro de obtén spotify gratis se envían llenos y con fecha futura", alwaysRun = true, priority = 0)
    public void spotifySignUpFuturo(){

        PropertyReader propertyReader = new PropertyReader();
        String email=propertyReader.getProperty("credentials.properties", "TC_US_03_003_EMAIL");
        String pass=propertyReader.getProperty("credentials.properties", "TC_US_03_003_PASSWORD");
        String name=propertyReader.getProperty("credentials.properties","TC_US_03_003_NAME");
        String day=propertyReader.getProperty("credentials.properties", "TC_US_03_003_DAY");
        String month=propertyReader.getProperty("credentials.properties","TC_US_03_003_MONTH");
        String year=propertyReader.getProperty("credentials.properties", "TC_US_03_003_YEAR");
        String gender=propertyReader.getProperty("credentials.properties","TC_US_03_003_GENDER");

        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(driver);
        spotifySignUpPage.fillSpotifySignUpForm(email,pass,name,month,day,year,gender);
        spotifySignUpPage.clickOnRegistrateButton();
        List<String> listOfErrors = spotifySignUpPage.getAllSpotifySignUpFormErrorMessages();

        Assert.assertTrue(listOfErrors.get(0).contains("Lo sentimos, para crear una cuenta debes tener al menos trece años."), "Mensaje de Error Diferente al de minimo de años");
    }
}