package ar.org.icaro.automatizacion.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Aqui elegimos qué features queremos que se corran mediante el argumento 'features'.
 * Este argumento toma las rutas hacia los archivos .feature que queremos correr.
 * Por ejemplo, si solo queremos correr Login.feature escribimos:
 *  features = {"src/test/resources/features/Login.feature"}
 * Si queremos correr tanto Login.feature como CrearCuenta.feature escribimos cada ruta
 * separada por una coma:
 *  features = {"src/test/resources/features/Login.feature",
 *              "src/test/resources/features/CrearCuenta.feature"}
 * También, en caso de querer todas las features dentro de un directorio, podemos
 * simplemente escribir la ruta al directorio:
 *  features = {"src/test/resources/features"}
 */
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"ar.org.icaro.automatizacion.steps"}
)
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

}