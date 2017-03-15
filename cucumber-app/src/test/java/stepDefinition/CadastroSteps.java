package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.SeleniumTestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by assis on 24/02/17.
 */
@RunWith(SpringRunner.class)
@SeleniumTestCase(pageObject = CadastroPage.class)
public class CadastroSteps {
    @Autowired
    private CadastroPage cadastroPage;

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
