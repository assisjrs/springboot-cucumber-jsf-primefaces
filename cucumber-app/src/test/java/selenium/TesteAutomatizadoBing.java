package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAutomatizadoBing {
	public static void main(String[] args) {
		WebDriver fDriver = new FirefoxDriver();
		fDriver.get("http://www.bing.com/");
		
		WebElement campoBusca = fDriver.findElement(By.name("q"));
		
		campoBusca.sendKeys("F");
		campoBusca.submit();
		System.out.println("funcionou!");
	}
}
