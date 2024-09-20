package ar.org.icaro.automatizacion.reporte;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Reporte implements IReporter {

    private static final String PLANTILLA_FILA =
            "<tr class=\"%s\">" +
                    "<td>%s</td>" +
                    "<td>%s</td>" +
                    "<td>%s</td>" +
                    "<td>%s</td>" +
                    "<td>%s</td>" +
                    "</tr>";

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String directorioDestino) {

        String plantillaReporte = inicializarPlantilla();

        String cuerpo = "";
        for (ISuite suite : suites) {
            for (Map.Entry<String, ISuiteResult> resultado : suite.getResults().entrySet()) {
                cuerpo = cuerpo.concat(resultadoAFilas(resultado.getValue(),
                        resultado.getKey(), suite.getName()));
            }
        }
        cuerpo = cuerpo.concat("</tbody>");

        String reporte = plantillaReporte.replaceFirst("</tbody>", cuerpo);
        guardarReporte(directorioDestino, reporte);
    }

    private String inicializarPlantilla() {
        String rutaPlantila = "src/test/resources/plantillaReporte.html";
        try {
            byte[] plantillaReporte = Files.readAllBytes(Paths.get(rutaPlantila));
            return new String(plantillaReporte, "UTF-8");
        } catch (IOException e) {
            System.err.println("Error al leer la plantilla: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private void guardarReporte(String directorioDestino, String contenidoReporte) {
        new File(directorioDestino).mkdirs();
        try {
            File archivo = new File(directorioDestino, "reporte.html");
            PrintWriter reportWriter = new PrintWriter(new BufferedWriter(
                    new FileWriter(archivo)));
            reportWriter.println(contenidoReporte);
            reportWriter.flush();
            reportWriter.close();
        } catch (IOException e) {
            System.err.println("Error al guardar el reporte: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String resultadoAFilas(ISuiteResult resultadoSuite, String nombreTest, String nombreSuite) {
        ITestContext testContext = resultadoSuite.getTestContext();

        Set<ITestResult> resultadosPruebas = new HashSet<>();
        resultadosPruebas.addAll(testContext.getFailedTests().getAllResults());
        resultadosPruebas.addAll(testContext.getPassedTests().getAllResults());
        resultadosPruebas.addAll(testContext.getSkippedTests().getAllResults());

        String filas = "";
        for (ITestResult resultadoPrueba : resultadosPruebas) {
            filas = filas.concat(construyeFila(resultadoPrueba, resultadoPrueba.getName(),
                    nombreSuite)).concat("\n");
        }

        return filas;
    }

    private String construyeFila(ITestResult resultado, String nombreTest, String nombreSuite) {
        switch (resultado.getStatus()) {
            case ITestResult.FAILURE:
                return String.format(PLANTILLA_FILA, "danger", nombreSuite, nombreTest,
                        resultado.getName(), "FALLO", "NA");

            case ITestResult.SUCCESS:
                return String.format(PLANTILLA_FILA, "success", nombreSuite, nombreTest,
                        resultado.getName(), "EXITO",
                        resultado.getEndMillis() - resultado.getStartMillis());

            case ITestResult.SKIP:
                return String.format(PLANTILLA_FILA, "warning", nombreSuite, nombreTest,
                        resultado.getName(), "OMITIDO", "NA");

            default:
                return "";
        }
    }

}