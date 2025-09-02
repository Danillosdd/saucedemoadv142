package stepsPO; // Define o pacote do arquivo

import org.junit.runner.RunWith; // Importa anotação para definir o runner do JUnit

import io.cucumber.junit.Cucumber; // Importa o runner do Cucumber para JUnit
import io.cucumber.junit.CucumberOptions; // Importa opções de configuração do Cucumber

@RunWith(Cucumber.class) // Indica que os testes serão executados com o runner do Cucumber
@CucumberOptions(
        features = "src/test/resources/featuresPO", // Caminho dos arquivos de feature
        glue = "stepsPO", // Pacote onde estão os steps (implementações dos cenários)
        plugin = {"pretty", "html:target/cucumber-report.html"}, // Plugins para relatório (console e HTML)
        tags = "@" // Filtro de tags para execução (executa todos os cenários)
)
public class Runner { // Classe runner para executar os testes do Cucumber
}
