package stepDefinition;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.Config;
import database.SeleniumTestCase;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;

/**
 * Created by CSSP on 08/03/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Config.class })
@Transactional
@DatabaseSetup("CadastrarUmUsuarioSteps.xml")
@SeleniumTestCase(webDriver = ChromeDriver.class,
                  url = "http://localhost:9090/index.xhtml",
                  pageObject = CadastroPage.class)
public class CadastraUmUsuarioEValidaSteps {
    @Autowired
    private CadastroPage cadastroPage;

    @Given("^o nome do usuario como \"([^\"]*)\" e email \"([^\"]*)\"$")
    public void oNomeDoUsuarioComoEEmail(String nome, String email) throws Throwable {
        cadastroPage.setNome(nome);
        cadastroPage.setEmail(email);
    }

    @When("^cadastrar o usuario$")
    public void euCadastrarOUsuario() throws Throwable {
        cadastroPage.novoUsuario();
    }

    @Then("^verificar se o nome \"([^\"]*)\" e email \"([^\"]*)\" se encontra na lista$")
    public void verificarSeONomeEEmailSeEncontraNaLista(String nome, String email) throws Throwable {
        cadastroPage.assertThat().verificaSeAMensagemSeEncontraNaPagina(nome, email);
    }
}
