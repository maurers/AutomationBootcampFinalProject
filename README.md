# Proyecto Final Bootcamp 2019

### Requerimientos Proyecto Final Bootcamp 2019

Página Web: https://www.spotify.com/mx
Fecha: **30 de Septiembre 2019**

#### Use Cases
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y visualizar la pantalla de inicio.
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y despues iniciar sesion en “Inicio de Sesion” y despues ingresar mis credenciales.
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y dar click en “Obten Spotify Gratis” para ir a la pagina de registro.
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y despues registrarme en “Registrate” y después llenar el formulario para crear una nueva cuenta.
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y despues recibir ayuda en “Ayuda” y después, eso te llevara a la página de Soporte Técnico.
- Como usuario, puedo ingresar a la pagina https://www.spotify.com/mx y regresar a la pagina principal dando click en icono de Spotify.

#### Entregables
- Documento de Test Plan.
- Repositorio con el framework de automatización y pruebas automatizadas.
- Documento con nombre de autores y aporte al proyecto.
<<<<<<< HEAD
=======

## Diagrama de clases y documentación

![](https://es.trate.ga/Spotify-Diagram.jpeg)

El diagrama de clases para nuestro framework está dividido en dos categorías, la primera son las clases enfocadas a las páginas y la segunda son los componentes que se derivan de la abstracción de cada una de las páginas.

## Clases de Páginas

### BasePage
La clase **BasePage** es una clase abstracta que contiene lo siguiente:

**Atributos:**
- driver: Genera una instancia del WebDriver.

**Métodos:**
- BasePage(driver:WebDriver) = Constructor que define el WebDriver el cual posteriormente será inicializado con el PageFactory.
- open() = Método con el se abre la página a probar.
- isLoaded() = El método isLoaded que retorna un boolean si la página ha cargado correctamente.
- close() = Este método que tiene la finalidad de cerrar la ventana del navegador en la que se está enfocando el driver y posteriormente cerrar todas las ventanas del navegador y terminar la sesión de manera segura.
- clickOnSpotifyIcon() = Método abstracto con el cual se dará click al logo para regresar a home y que retorna un valor de tipo SpotifyHomePage.

### SpotifyHomePage
Clase es pública que extendiende BasePage.

**Atributos:**
- homeHeaderComponent: Instancia de la clase HomeHeaderComponent la cual nos ayuda a acceder a los atributos y métodos de la misma.
- homeBodyComponent: Instancia de la clase HomeBodyComponent la cual nos ayuda a acceder a los atributos y métodos de la misma.
- wait: Es un atributo de tipo WebdriverWait el cual será utilizado para agregar un explicit wait en el método isLoaded.
- BASE_URL: Atributo static final de tipo String el cual contiene la URL de la página que estamos probando.

**Métodos**
- SpotifyHomePage(driver:WebDriver) = Método constructor de la clase el cual recibe como parámetro un elemento de tipo WebDriver.
- isLoaded() = Método que retorna un valor tipo booleano para determinar si la página Spotify esta desplegada o no.
- goToHelpPage() = Método para ir a la ayuda de la página, retorna un elemento SpotifyHelpPage.
- goToSignUpPage() = Método para crear una cuenta en Spotify, retorna un elemento SpotifySingUpPage.
- goToGetPage() = Método para obtener Spotify, retorna un elemento SpotifySingUpPage.
- goToLoginPage() = Método para iniciar sección en Spotify, retorna un elemento SpotifyLoginPage.
- clickOnSpotifyIcon() = Método para dar click en el logo y regresar a la página principal de Spotify, retorna un elemento de tipo SpotifyHomePage.

### SpotifyLoginPage
Clase es pública que extendiende BasePage.

**Atributos:**
- LoginConponent: Instancia de la clase LoginComponent, que nos ayuda para acceder a los atributos y métodos de la misma clase.
- wait: Es un atributo de tipo WebdriverWait el cual será utilizado para agregar un explicit wait en el método isLoaded.
- BASE_URL: Atributo static final de tipo String la cual contiene la URL de la página que estamos probando.

**Métodos:**
- SpotifyLoginPage(driver:WebDriver) =  Método constructor de la clase el cual recibe como parámetro un elemento de tipo webdriver
- isLoaded() = Método que retorna un valor tipo booleano para determinar si la página Spotify esta desplegada o no.
- spotifyLoginFrom(user:String, pass:String) = Método para hacer Login una cuenta en Spotify, retorna un elemento de tipo SpotityLoginPage
- clickOnSpotifyIcon() = Método para dar click en el logo y regresar a la página principal de Spotify, retorna un elemento de tipo SpotifyHomePage.

### SpotifySignUpPage
Clase es pública que extendiende BasePage.

**Atributos:**
- singUpComponent: este es el componente que genera el formulario de registro del usuario a la pagina.
- wait: Es un atributo de tipo WebdriverWait el cual será utilizado para agregar un explicit wait en el método isLoaded.
- BASE_URL: atributo static final de tipo String la cual contiene la URL de la página que estamos probando.

**Metodos:**
- SpotifySingUpPage(driver:WebDriver) = Método constructor de la clase el cual recibe como parámetro un elemento de tipo webdriver.
- isLoad() = Este método se utiliza para identificar que la página se ha cargado.
- spotifySingUpForm(email:String, pass:String, name:String, year:String, gender:char) = SpotifySignUpPage = En este metodo se generan los campos a ser llenados por el usuario con sus credenciales.
- clickOnSpotifyIcon() = Método para dar click en el logo y regresar a la página principal de Spotify, retorna un elemento de tipo SpotifyHomePage.

### SpotifyHelpPage
Clase es pública que extendiende BasePage.

**Atributos:**
- wait: Es un atributo de tipo WebdriverWait el cual será utilizado para agregar un explicit wait en el método isLoaded.
- BASE_URL: atributo static final de tipo String la cual contiene la URL de la página que estamos probando.

**Métodos:**
- SpotifyHelpPage(driver:WebDriver) = Método constructor de la clase el cual recibe como parámetro un elemento de tipo webdriver.
- isLoad() = Este método boolean que se utiliza para identificar que la página se ha cargado.
- clickOnSpotifyIcon() = Método para dar click en el logo y regresar a la página principal de Spotify, retorna un elemento de tipo SpotifyHomePage.

## Clases de Componentes

### SpotifyComponent
Clase es pública

**Atributos:**
- driver: Genera una instancia del WebDriver.

**Métodos:**
- SpotifyComponent() = Método constructor de la clase el cual recibe como parámetro un elemento de tipo webdriver.

### HomeHeaderComponent
Clase es pública que extendiende SpotifyComponent.

**Atributos:**
- loginButton: WebElement del botón para que redirecciona al usuario a la página de incio de sesión.
- singUpButton: WebElement del botón que redirige a la pagina de registro de un usuario nuevo.
- helpButton: WebElement del botón que muestra la ayuda a los usuarios.

**Métodos:**
- HomeHeaderComponent(driver:WebDriver) = Método constructor de la clase el cual recibe como parámetro un elemento de tipo webdriver.
- clickLoginButton() = Este método para iniciar sesión.
- clickSignUpButton() = Este método crear nuevo usuario.
- clickHelpButton() = Este método para obtener ayuda de la plataforma.

### LoginComponent
Clase es pública que extendiende SpotifyComponent.

**Atributos:**
- emailInput: WebElement del campo para ingresar la dirección de usuario.
- passInput: WebElement para ingresar la contraseña del usuario
- loginButton: WebElement del botón que genera el inicio de sesión  con las credenciales ingresadas por el usuario.

**Metodos:**
- LoginAccount(driver:WebDriver) = Método constructor de la clase el cual recibe como parámetro un elemento de tipo webdriver.
- LoginComponent(user:String, pass:String) = Método para iniciar sesión.

### HomeBodyComponent
Clase es pública que extendiende SpotifyComponent.

**Atributos:**
- getSpotifyButton: WebElement para navegar a la pagina de registro de la plataforma.

**Métodos:**
- HomeBodyComponent(driver:WebDriver) = Método constructor de la clase el cual recibe como parámetro un elemento de tipo webdriver.
- clickOnGetSpotifyButton() = Este método te redirecciona a SpotifySignUpPage.

### SignUpComponent
Clase es pública que extendiende SpotifyComponent.

**Atributos:**
- emailInput: WebElement del campo para ingresar la dirección de email del usuario.
- confirmPassInput: WebElement para reingresar la contraseña de email del usuario.
- passInput: WebElement para ingresar la contraseña del usuario
- nameInput: WebElement para ingresar el nombre del usuario
- monthDropdown: WebElement que despliega los nombres de los meses a elegir por el usuario para su fecha de nacimiento.
- monthSelect: WebElement List, para generar la acción de elegir el mes correspondiente.
- dayInput: WebElement para ingresar el día correspondiente a la fecha de nacimiento del usuario.
- yearInput: WebElement para ingresar el año correspondiente a la fecha de nacimiento del usuario.
- genderRadioButtons: WebElement List para que el usuario elija el género (Hombre, Mujer, No binario).
- shareCheckBox: WebElement para dar permiso de que la plataforma de spotify pueda compartir con sus proveedores los datos del usuario con fines de marketing.
- signUpButton: WebElement del botón que genera el registro con las credenciales ingresadas por el usuario.

**Métodos:**
- SingUpComponent(driver:WebDriver) = Método constructor de la clase el cual recibe como parámetro un elemento de tipo webdriver.
- signUpAccount(email:String, pass:String, name:String, year:String, gender:char) = Método para generar un nuevo registro.
>>>>>>> 2dbae9343aff60b616af679e7abc275d89ef65fa
