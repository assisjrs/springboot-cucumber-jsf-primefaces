package stepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by CSSP on 08/03/2017.
 */
public class CadastrarUsuarioEmailStep {

    private CadastroPage cadastroPage;



    @Before
    public void initWebdriver() {
        cadastroPage = new CadastroPage();
    }

    @After
    public void destroyWebdriver() throws Throwable {
        cadastroPage.finalize();
    }

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
        assertTrue(cadastroPage.mensagemErro().contains(mensagem));
    }


}
