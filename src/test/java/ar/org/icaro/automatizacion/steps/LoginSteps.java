package ar.org.icaro.automatizacion.steps;

import ar.org.icaro.automatizacion.pages.HomePage;
import ar.org.icaro.automatizacion.pages.SignInPage;
import ar.org.icaro.automatizacion.steps.driver.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.es.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {

    private WebDriver driver;
    private SignInPage signInPage;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = DriverManager.driver;
    }

    @Dado("un usuario que esta en la pagina de crear cuenta y presiona iniciar sesion")
    public void unUsuarioQueEstaEnLaPaginaDeCrearCuentaQuePresionaIniciarSesion() {
        driver.get("https://app.dev.wallbit.io/sign-in");
        signInPage = new SignInPage(driver);
        signInPage.clickLoginButton();
    }

    @E("introduce {string} como email y {string} como contrasenia")
    public void introduceComoEmailYComoContrasenia(String email, String contrasenia) {
        signInPage.introduceEmail(email);
        signInPage.clickContinuar();
        signInPage.introducePassword(contrasenia);
        signInPage.clickContinuar();
        homePage = new HomePage(driver);
    }

    @Entonces("el usuario puede ver la pagina de Descarga de la App")
    public void elUsuarioPuedeVerLaPaginaDeDescargaDeLaApp() {
        Assert.assertTrue(homePage.isTitleDescargaLaAppVisible(), "El título 'Descarga la app' no es visible.");
    }

    @Entonces("el sistema debe mostrar un mensaje de error que dice {string}")
    public void elSistemaDebeMostrarUnMensajeDeErrorQueDice(String mensajeEsperado) {
        String mensajeActual = signInPage.getDisplayedErrorMessage();
        Assert.assertEquals(mensajeActual, mensajeEsperado,
                "El mensaje de error no es correcto. Se esperaba: '" + mensajeEsperado + "' pero se obtuvo: '" + mensajeActual + "'");
    }

    @Cuando("introduce {string} como email")
    public void introduceComoEmail(String email) {
        signInPage.introduceEmail(email);
    }

    @E("introduce {string} como contrasenia")
    public void introduceComoContrasenia(String contrasenia) {
        signInPage.introducePassword(contrasenia);
        signInPage.clickContinuar();
    }
}
