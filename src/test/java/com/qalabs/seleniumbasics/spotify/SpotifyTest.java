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
    
    @Test(description = "TC_US1_002 / Ir a la pagina principal de Spotify con URL incorrecta")
    public void goToHomePageFail() {
        PropertyReader propertyReader = new PropertyReader();
        String page = propertyReader.getProperty("credentials.properties", "URL_INVALID");

        this.myDriver.navigate().to(page);

        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertNotEquals(spotifyHomePage.BASE_URL, page, "Las paginas son iguales, cuando deberian ser diferente");
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
    
    @Test(description = "TC_US3_001 / Llenar campos de registros de obtén Spotify gratis se envían vacíos")
    public void freeSpotifyEmptyFields() {
        PropertyReader propertyReader = new PropertyReader();
        String homePage = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        String signUpPage = propertyReader.getProperty("credentials.properties", "URL_SIGNUP_PAGE");

        this.myDriver.navigate().to(homePage);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");

        spotifyHomePage.goToGetSpotify();
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.myDriver);
        Assert.assertTrue(spotifySignUpPage.isLoaded(), "Pagina registro no se cargo correctamente");
        spotifySignUpPage.spotifySignUpForm("", "", "", "", "", "", "");
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página no se cargo correctamente");
        Assert.assertTrue(spotifySignUpPage.BASE_URL.startsWith(signUpPage), "Logro registrarse, cuando no deberia");

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
    
    @Test(description = "TC_US6_001 / Probar icono de spotify que redireccione a home page desde home page")
    public void goToHomePage() {
        PropertyReader propertyReader = new PropertyReader();
        String homePage = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");

        this.myDriver.navigate().to(homePage);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");

        spotifyHomePage.clickOnSpotifyIcon();
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");
    }
    
    @Test(description = "TC_US6_002 / Probar icono de spotify que redireccione a home page desde help page")
    public void goToHomePage() {
        PropertyReader propertyReader = new PropertyReader();
        String homePage = propertyReader.getProperty("credentials.properties", "URL_WEBSITE");
        String helpPage = propertyReader.getProperty("credentials.properties", "URL_HELP");

        this.myDriver.navigate().to(homePage);
        SpotifyHomePage spotifyHomePage = new SpotifyHomePage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertEquals(spotifyHomePage.BASE_URL, homePage, "Cargo una pagina diferente");

        spotifyHomePage.goToHelpPage();
        SpotifyHelpPage spotifyHelpPage = new SpotifyHelpPage(this.myDriver);
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página ayuda no se cargo correctamente");
        Assert.assertTrue(spotifyHelpPage.BASE_URL.startsWith(helpPage), "Cargo una pagina diferente a Ayuda");

        spotifyHomePage.clickOnSpotifyIcon();
        Assert.assertTrue(spotifyHomePage.isLoaded(), "Página principal no se cargo correctamente");
        Assert.assertTrue(spotifyHomePage.BASE_URL.startsWith(homePage), "Cargo una pagina diferente a la principal");
    }

}
