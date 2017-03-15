package stepDefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by assis on 24/02/17.
 */
public class CadastroSteps {
    private CadastroPage cadastroPage;

    @Before
    public void initWebdriver() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");

        cadastroPage = new CadastroPage(new ChromeDriver());
    }

    @After
    public void destroyWebdriver() throws Throwable {
        cadastroPage.finalize();
    }

    @Given("^o nome do usuario as \"([^\"]*)\"$")
    public void oNomeDoUsuarioAs(String nomeVazio) throws Throwable {
        cadastroPage.setNome(nomeVazio);
        cadastroPage.setEmail("");
    }

    @When("^eu cadastrar o usuario$")
    public void euCadastrarOUsuario() throws Throwable {
        cadastroPage.novoUsuario();
    }

    @Then("^O usuario deve receber a seguinte mensagem de erro \"([^\"]*)\"$")
    public void oUsuarioDeveReceberASeguinteMensagemDeErro(String mensagemDeErro) throws Throwable {
        assertTrue(cadastroPage.mensagemErro().contains(mensagemDeErro));
    }
}
