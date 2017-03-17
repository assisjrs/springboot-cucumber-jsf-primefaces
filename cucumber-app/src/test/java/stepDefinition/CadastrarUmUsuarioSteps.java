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

import static org.junit.Assert.assertEquals;

/**
 * Created by assis on 06/03/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
@Transactional
@DatabaseSetup("CadastrarUmUsuarioSteps.xml")
@SeleniumTestCase(webDriver = ChromeDriver.class,
                  url = "http://localhost:9090/index.xhtml",
                  pageObject = CadastroPage.class)
public class CadastrarUmUsuarioSteps {
    @Autowired
    private CadastroPage cadastroPage;

    @Given("^o nome do usuario as \"([^\"]*)\" e o email \"([^\"]*)\"$")
    public void oNomeDoUsuarioAsEOEmail(String nome, String email) throws Throwable {
        cadastroPage.setNome(nome);
        cadastroPage.setEmail(email);
    }

    @When("^eu incluir o usuario Assis com email francisco.melo@concrete.com.br$")
    public void euCadastrarOUsuario() throws Throwable {
        cadastroPage.novoUsuario();
    }

    @Then("^A lista de usuarios deve ter (\\d+) item$")
    public void aListaDeUsuariosDeveTerItem(int quantidadeUsuariosCadastrados) throws Throwable {
        cadastroPage.assertThat().quantidaDeUsuariosCadastradosNaLista(quantidadeUsuariosCadastrados);
    }
}
