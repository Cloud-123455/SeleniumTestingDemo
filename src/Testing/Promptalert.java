package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Promptalert {
	
	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gokul\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demoqa.com/alerts"); 
		driver.manage().window().maximize();
		WebElement prompt=driver.findElement(By.xpath("//button[@id='promtButton']"));
		Thread.sleep(1000);
		prompt.click();
		driver.switchTo().alert().sendKeys("Hi");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		driver.close();
		
	}


}
