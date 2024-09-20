package ar.org.icaro.automatizacion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private final By titleBy = By.xpath("//h1[@class='title']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleDescargaLaAppVisible() {
        // Espera hasta que el texto sea visible y correcto
        return wait.until(ExpectedConditions.textToBe(titleBy, "Descarga la app"));
    }
}
