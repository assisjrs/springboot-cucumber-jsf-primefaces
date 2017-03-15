package stepDefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeanUtils;

import static org.junit.Assert.assertTrue;

/**
 * Created by assis on 24/02/17.
 */
public class CadastroSteps {
    private CadastroPage cadastroPage;

    private WebDriver webDriver;

    @Before
    public void beforeClass(){
        if (webDriver == null)
            webDriver = BeanUtils.instantiate(ChromeDriver.class);
    }

    @After
    public void afterClass(){
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
    }

    @Before
    public void initWebdriver() {
        cadastroPage = PageFactory.initElements(webDriver, CadastroPage.class);
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
