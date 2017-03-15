package stepDefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * Created by CSSP on 08/03/2017.
 */
public class CadastrarUsuarioEmailSteps {
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
