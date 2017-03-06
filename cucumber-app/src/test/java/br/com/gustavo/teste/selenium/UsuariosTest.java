package br.com.gustavo.teste.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class UsuariosTest {
    public static final String ADDRESS = "http://localhost";
    public static final String PORT = ":8083";
    public static final String NEW_USUARIO = "/usuarios/new";


    private WebDriver driver;

    @Before
    public void inicializarBrowser() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void finalizarBrowser() {
        driver.close();
    }

    @Test
    public void campoNuloDeveSerObrigatorio() {
        driver.get(ADDRESS+PORT+NEW_USUARIO);
        WebElement email = driver.findElement(By.name("usuario.email"));
        email.sendKeys("ronaldo2009@terra.com.br");
        email.submit();

        assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
    }

    @Test
    public void deveAdicionarUsuario() {
        driver.get(ADDRESS+PORT+NEW_USUARIO);

        WebElement nome = driver.findElement(By.name("usuario.nome"));
        WebElement email = driver.findElement(By.name("usuario.email"));

        nome.sendKeys("Ronaldo Luiz de Albuquerque");
        email.sendKeys("ronaldo2009@terra.com.br");

        WebElement btnSalvar = driver.findElement(By.id("btnSalvar"));

        btnSalvar.click();

        boolean achouNome = driver.getPageSource().contains("Ronaldo Luiz de Albuquerque");

        assertTrue(achouNome);
    }
}
