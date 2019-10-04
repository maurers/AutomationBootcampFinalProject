package com.qalabs.seleniumbasics.spotify;
import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpotifyTest extends BaseTest {

    @Test(description = "TC_US1_001 / Ingresar a la página de Spotify y poder visualizar el contenido de la pantalla de inicio")
    public void goToHomePage() {
        PropertyReader propertyReader = new PropertyReader();
        String page = propertyReader.getProperty("credentials.properties", "SPOTIFY_PAGE");

        myDriver.navigate().to(page);

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, page, "Cargo una pagina diferente");
    }

    @Test(description = "TC_US2_005 / Acceder al formulario de Inicio de Sesión e ingresar sus credenciales de manera manual, utilizando un username, sin emplear algún método de autenticación de terceros")
    public void loginAtSpotify(){

        PropertyReader propertyReader = new PropertyReader();
        String homePage = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        String loginPage = propertyReader.getProperty("credentials.properties", "URL_HELP");

        myDriver.navigate().to(homePage);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");

        spotifyHomePage.goToLoginPage();


        String user = propertyReader.getProperty("credentials.properties", "USERNAME");
        String pass = propertyReader.getProperty("credentials.properties", "PASSWORD");

        SpotifyLoginPage spotifyLoginPage = new SpotifyLoginPage(myDriver);
        Assert.assertTrue(spotifyLoginPage.isLoaded(), "Página de login no se cargo correctamente");
        spotifyLoginPage.spotifyLoginForm(user,pass);
        Assert.assertFalse( spotifyLoginPage.isLoaded(), "No inició sesión, sigue en la página de login");
    }




}