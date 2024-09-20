package ar.org.icaro.automatizacion.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends BasePage {
//
//    // Selectores comunes en la página de inicio de sesión
    private final By loginButtonBy = By.cssSelector(".btn--secondary");
    private final By emailFieldBy = By.xpath("//input[@placeholder='Email']");
    private final By continuarButtonBy = By.xpath("//button[text()='Continue']");
    private final By passwordFieldBy = By.xpath("//input[@placeholder='Password']");
    private final By rightArrowBy = By.cssSelector(".arrow__right");
    private final By errorMessageBy = By.cssSelector(".invalid-feedback");
    private final By contraseniaIncorrectaBy = By.xpath("//div[@role='status' and text()='El email y la contraseña no coinciden']");
//
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    private WebElement loginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonBy));
        return driver.findElement(loginButtonBy);
    }

    public void clickLoginButton() {
        loginButton().click();
    }

    private WebElement getMensajeContraseniaIncorrecta(){
        return driver.findElement(contraseniaIncorrectaBy);
    }

    public boolean isMensajeContraseniaIncorrectaVisible() {
        try {
            WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(contraseniaIncorrectaBy));
            return mensaje.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false; // El mensaje no está visible
        }
    }

    public void introduceEmail(String email){
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldBy));
        emailField.clear();
        emailField.sendKeys(email);
    }

    private WebElement continuarButton() {
        WebElement continuarBtn = wait.until(ExpectedConditions.presenceOfElementLocated(continuarButtonBy));
        return continuarBtn;
    }

    public void clickContinuar() {
        continuarButton().click();
    }

    public void introducePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldBy));
        driver.findElement(passwordFieldBy).sendKeys(password);
    }


    public String getDisplayedErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageBy));
        return driver.findElement(errorMessageBy).getText();
    }

}
