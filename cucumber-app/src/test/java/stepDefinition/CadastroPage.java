package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by assis on 03/03/17.
 */
public class CadastroPage {
    public static final String ADDRESS = "http://localhost";
    public static final String PORT = ":9090";
    public static final String INDEX = "/index.xhtml";

    private WebDriver driver;
    private WebDriverWait wait;

    private String nome;
    private String email;

    public CadastroPage(){
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 60000);
    }

    public void novoUsuario() throws InterruptedException {
        driver.get(ADDRESS+PORT+INDEX);

        WebElement link = driver.findElement(By.linkText("Novo Usuário"));
        link.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usuarioForm")));

        final WebElement nomeInput = driver.findElement(By.className("usuario.nome"));
        nomeInput.sendKeys(nome);

        final WebElement emailInput = driver.findElement(By.className("usuario.email"));
        nomeInput.sendKeys(email);

        WebElement salvar = driver.findElement(By.id("usuarioForm:salvar"));

        salvar.click();
    }

    public String mensagemErro(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"usuarioForm:messages\"]/div/ul/li/span[1]")));

        return corpo();
    }

    public String corpo(){
        return driver.getPageSource();
    }

    public int quantidadeUsuarios(){
        return driver.findElements(By.xpath("//table[@id='Datatable']/tbody/tr")).size();
    }

    @Override
    public void finalize() throws Throwable {
        driver.close();

        super.finalize();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
