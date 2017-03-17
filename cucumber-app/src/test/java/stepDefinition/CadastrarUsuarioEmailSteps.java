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
 * Created by CSSP on 08/03/2017.
 */
@RunWith(SpringRunner.class)
@SeleniumTestCase(pageObject = CadastroPage.class)
public class CadastrarUsuarioEmailSteps {
    @Autowired
    private CadastroPage cadastroPage;

    @Given("^o nome do usuario como \"([^\"]*)\" e email como \"([^\"]*)\"$")
    public void oNomeDoUsuarioComoEEmailComo(String nome, String email) throws Throwable {
        cadastroPage.setEmail(email);
        cadastroPage.setNome(nome);

    }

    @When("^eu cadastrar o usuario com o email vazio$")
    public void euCadastrarOUsuarioComOEmailVazio() throws Throwable {
        cadastroPage.novoUsuario();
    }

    @Then("^O sistema deve exibir a mensagem de erro \"([^\"]*)\"$")
    public void oSistemaDeveExibirAMensagemDeErro(String mensagem) throws Throwable {
        cadastroPage.assertThat().verificaSeAMensagemSeEncontraNaPagina(mensagem);
    }
}
