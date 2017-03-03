package stepDefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by assis on 24/02/17.
 */
public class CadastroSteps {
    private WebDriver driver;

    private String nome;

    @Before
    public void initWebdriver() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void destroyWebdriver() {
        driver.close();
    }

    @Given("^o nome do usuario as \"([^\"]*)\"$")
    public void oNomeDoUsuarioAs(String nomeVazio) throws Throwable {
        nome = nomeVazio;
    }

    @When("^eu cadastrar o usuario$")
    public void euCadastrarOUsuario() throws Throwable {
        driver.get("http://localhost:8080/leiloes/faces/index.xhtml");

        WebElement nomeInput = driver.findElement(By.name("usuario.nome"));

        nomeInput.sendKeys(nome);

        nomeInput.submit();
    }

    @Then("^O usuario deve receber a seguinte mensagem de erro \"([^\"]*)\"$")
    public void oUsuarioDeveReceberASeguinteMensagemDeErro(String mensagemDeErro) throws Throwable {
        assertTrue(driver.getPageSource().contains(mensagemDeErro));
    }
}
