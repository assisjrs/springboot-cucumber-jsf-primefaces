package stepDefinition;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.Config;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by assis on 06/03/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@Transactional
@TestExecutionListeners(value = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class }, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@DatabaseSetup("./CadastrarUmUsuarioStep.xml")
public class CadastrarUmUsuarioStep {
    private CadastroPage cadastroPage;

    @Before
    public void initWebdriver() {
        cadastroPage = new CadastroPage();
    }

    @After
    public void destroyWebdriver() throws Throwable {
        cadastroPage.finalize();
    }

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
        assertEquals(quantidadeUsuariosCadastrados, cadastroPage.quantidadeUsuarios());
    }
}
