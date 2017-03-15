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

import static org.junit.Assert.assertTrue;

/**
 * Created by CSSP on 08/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@Transactional
@TestExecutionListeners(value = { DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class },
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@DatabaseSetup("CadastrarUmUsuarioSteps.xml")
public class CadastraUmUsuarioEValidaSteps {
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
        assertTrue(cadastroPage.corpo().contains(nome));
        assertTrue(cadastroPage.corpo().contains(email));
    }
}
