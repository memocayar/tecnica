package ar.org.icaro.automatizacion.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends BasePage {

    // Selectores comunes en la página de inicio de sesión
    private final By loginButtonBy = By.cssSelector(".btn--secondary");
    private final By emailFieldBy = By.xpath("//input[@placeholder='Email']");
    private final By passwordFieldBy = By.xpath("//input[@placeholder='Password']");
    private final By continuarButtonBy = By.xpath("//button[text()='Continue']");
    private final By errorMessageBy = By.cssSelector(".invalid-feedback");

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public String getDisplayedErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageBy));
        return driver.findElement(errorMessageBy).getText();
    }

    private WebElement loginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonBy));
        return driver.findElement(loginButtonBy);
    }

    public void pressTAB(){}{
        driver.findElement(emailFieldBy).sendKeys(Keys.TAB);
    }

    public void clickLoginButton() {
        loginButton().click();
    }

    public void introduceEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailFieldBy));
        driver.findElement(emailFieldBy).sendKeys(email);
    }

    private WebElement continuarButton() {
        WebElement continuarBtn = wait.until(ExpectedConditions.presenceOfElementLocated(continuarButtonBy));
        wait.until(driver -> continuarBtn.isDisplayed() && continuarBtn.isEnabled() &&
                !continuarBtn.getAttribute("class").contains("disabled"));
        return continuarBtn;
    }

    public void clickContinuar() {
        WebElement continuarBtn = continuarButton();

        if (continuarBtn.isEnabled() && !continuarBtn.getAttribute("class").contains("disabled")) {
            continuarBtn.click();
        }
    }

    public void introducePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldBy));
        driver.findElement(passwordFieldBy).sendKeys(password);
    }
}
