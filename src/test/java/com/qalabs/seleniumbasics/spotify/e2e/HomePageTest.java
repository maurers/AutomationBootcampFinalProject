package com.qalabs.seleniumbasics.spotify.e2e;

import com.qalabs.javabasics.spotify.pages.SpotifyHomePage;
import com.qalabs.seleniumbasics.spotify.BaseTest;
import com.qalabs.seleniumbasics.spotify.utilities.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest extends BaseTest {

    private String spotifyUrl;
    private String invalidUrl;

    @BeforeTest
    public void initSetup(){
        PropertyReader propertyReader= new PropertyReader();
        spotifyUrl= propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        invalidUrl = propertyReader.getProperty("credentials.properties", "URL_INVALID");
    }

    @Test(description = "TC_US1_001 / Ingresar a la página de Spotify y poder visualizar el contenido de la pantalla de inicio")
    public void goToHomePage() {

        driver.navigate().to(spotifyUrl);

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(driver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, spotifyUrl, "Cargó una página diferente");
    }
    @Test(description = "TC_US1_002 / Ir a la pagina principal de Spotify con URL incorrecta")
    public void goToHomePageFail() {

        this.driver.navigate().to(invalidUrl);

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.driver);
        Assert.assertNotEquals(spotifyHomePage.BASE_URL, invalidUrl, "Las páginas son iguales, cuando deberían ser diferentes");
    }
}
