package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAutomatizadoGoogle {

    public static void main(String[] args) {
        //WebDriver representa todos os drives do selenium
        //Firefox driver � um navegador
        WebDriver fDriver = new FirefoxDriver();

        //passa a url do site que vamos abrir
        fDriver.get("http://www.google.com.br");
        //WebElement recebe o campo com o nome q do browser
        WebElement campoDeTexto = fDriver.findElement(By.name("q"));
        //Insere valor no campo
        campoDeTexto.sendKeys("Guga");
        //envia informa��es para o navegador
        campoDeTexto.submit();
        System.out.println("Deu ceto!");
    }
}
