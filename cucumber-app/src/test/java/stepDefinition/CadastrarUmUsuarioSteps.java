package stepDefinition;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.Config;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by assis on 06/03/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@Transactional
@TestExecutionListeners(value = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class },
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@DatabaseSetup("CadastrarUmUsuarioSteps.xml")
public class CadastrarUmUsuarioSteps {
    private CadastroPage cadastroPage;

    @Autowired
    private DataSource dbUnitDatabaseConnection;

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
    public void initWebdriver() throws SQLException, DatabaseUnitException, IOException {
        final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);

        final IDataSet dataSet = builder.build(new File("src/test/resources/CadastrarUmUsuarioSteps.xml"));
        final DatabaseConnection databaseConnection = new DatabaseConnection(dbUnitDatabaseConnection.getConnection());

        DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);

        cadastroPage = PageFactory.initElements(webDriver, CadastroPage.class);
    }

    @After
    public void destroyWebdriver() throws Throwable {
        final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);

        final IDataSet dataSet = builder.build(new File("src/test/resources/CadastrarUmUsuarioSteps.xml"));
        final DatabaseConnection databaseConnection = new DatabaseConnection(dbUnitDatabaseConnection.getConnection());

       DatabaseOperation.DELETE_ALL.execute(databaseConnection, dataSet);
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
