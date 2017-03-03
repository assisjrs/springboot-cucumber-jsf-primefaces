package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteChrome {
	public static void main(String[] args) {
		//download drive chrome http://chromedriver.storage.googleapis.com/index.html?path=2.13/
		//set a propriedade do google chrome
		System.setProperty("webdriver.chrome.driver", "D:\\Projetos e Servidores\\2015 Projetos\\test-selenium\\lib\\chromedriver.exe");
		WebDriver wd = new ChromeDriver();
		
		//passa a url do site que vamos abrir 
		wd.get("http://www.google.com.br");
		//WebElement recebe o campo com o nome q do browser
		WebElement campoDeTexto =  wd.findElement(By.name("q"));
		//Insere valor no campo
		campoDeTexto.sendKeys("Gustavo");
		//envia informa��es para o navegador
		campoDeTexto.submit();
	}
}

// S�o varias as vantagens de termos testes de sistema automatizado:
//
// O teste automatizado � muito mais r�pido do que um ser humano.
// A partir do momento que voc� escreveu o teste, voc� poder� execut�-lo
// infinitas vezes a um custo baix�ssimo.
// Mais produtividade, afinal, voc� gastar� menos tempo testando (escrever um
// teste automatizado gasta menos tempo do que testar manualmente diversas vezes
// a mesma funcionalidade) e mais tempo desenvolvendo.
// Bugs encontrados mais r�pido pois, j� que sua bateria de testes roda r�pido,
// voc� a executar� a todo instante, encontrando poss�veis partes do sistema que
// deixaram de funcionar devido a novas implementa��es.
//
// O Selenium � uma excelente ferramenta para automatizar os testes. Sua API �
// bem clara e f�cil de usar, al�m da grande quantidade de documenta��o que pode
// ser encontrada na internet.
