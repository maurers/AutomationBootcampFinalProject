package com.qalabs.seleniumbasics.spotify;
import com.qalabs.seleniumbasics.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SpotifyTest extends BaseTest {
  
    private String spotifyUrl;
  
    @BeforeTest
    public void initSetup(){
        PropertyReader propertyReader= new PropertyReader();
        spotifyUrl= propertyReader.getProperty("test.properties", "TEST_URL");
    }
  
    @Test(description = "TC_US1_001 / Ingresar a la página de Spotify y poder visualizar el contenido de la pantalla de inicio")
    public void goToHomePage() {
        PropertyReader propertyReader = new PropertyReader();
        String page = propertyReader.getProperty("credentials.properties", "SPOTIFY_PAGE");

        myDriver.navigate().to(page);

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, page, "Cargo una pagina diferente");
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

    @Test(description = "TC_US2_005 / Acceder al formulario de Inicio de Sesión e ingresar sus credenciales de manera manual, utilizando un username, sin emplear algún método de autenticación de terceros")
    public void loginAtSpotify(){

        PropertyReader propertyReader = new PropertyReader();
        String homePage = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        String loginPage = propertyReader.getProperty("credentials.properties", "URL_LOGIN");

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
  
    @Test(alwaysRun = true,description = "TC_US4_001/ Llenar el formulario para crear cuenta nueva")
        public void invalidSignUpInInSpotify() {
        String email;
        String pass;
        String name;
        String day ;
        String month;
        String year;
        String pageSignUp;

        myDriver.navigate().to(spotifyUrl);
        PropertyReader propertyReader = new PropertyReader();
        email=propertyReader.getProperty("credentials.properties", "EMAIL");
        pass=propertyReader.getProperty("credentials.properties", "PASSWORD");
        name=propertyReader.getProperty("credentials.properties","NAME");
        day=propertyReader.getProperty("credentials.properties", "DAY");
        month=propertyReader.getProperty("credentials.properties","MONTH");
        year=propertyReader.getProperty("credentials.properties", "YEAR_VALID");
        pageSignUp=propertyReader.getProperty("credentials.properties", "URL_SIGNUP_PAGE");

        SpotifySignUpPage spotifySingUpPage = new SpotifySingUpPage(myDriver);
        spotifySingUpForm.signUpAccount(email,pass,name,day,month,year);
        Assert.assertTrue( SpotifySignUpPage.isLoaded(), "The page is not loaded");
        Assert.assertFalse(pageSignUp.startsWith(myDriver.getCurrentUrl()));

    }

}