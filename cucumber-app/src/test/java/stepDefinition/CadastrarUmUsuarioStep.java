package stepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * Created by assis on 06/03/17.
 */
public class CadastrarUmUsuarioStep {
    private CadastroPage cadastroPage;

    @Before
    public void initWebdriver() {
        cadastroPage = new CadastroPage();
    }

    @After
    public void destroyWebdriver() throws Throwable { cadastroPage.finalize(); }

    @Given("^o nome do usuario as \"([^\"]*)\" e o email \"([^\"]*)\"$")
    public void oNomeDoUsuarioAsEOEmail(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^A lista de usuarios deve ter (\\d+) item$")
    public void aListaDeUsuariosDeveTerItem(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
