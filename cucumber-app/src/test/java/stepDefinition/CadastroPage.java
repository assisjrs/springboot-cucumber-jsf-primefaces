package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by assis on 03/03/17.
 */
public class CadastroPage {
    private WebDriver driver;

    private String nome;

    public CadastroPage(){
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");

        driver = new ChromeDriver();
    }

    public void novoUsuario() throws InterruptedException {
        driver.get("http://localhost:8080/index.xhtml");

        final WebElement link = driver.findElement(By.linkText("Novo Usuário"));

        link.click();



        final WebElement nomeInput = driver.findElement(By.className("usuario.nome"));

        nomeInput.sendKeys(nome);

        nomeInput.submit();
    }

    public String corpo(){
        return driver.getPageSource();
    }

    @Override
    public void finalize() throws Throwable {
        driver.close();

        super.finalize();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
