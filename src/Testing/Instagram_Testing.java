package Testing;

//import java.time.Duration;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Select;

public class Instagram_Testing {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gokul\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		String baseurl = "https://instagram.com/"; 
		driver.get(baseurl);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		// User id
		driver.findElement(By.xpath("//*[@id='loginForm']/div/div[1]/div/label/input")).sendKeys("__ragul__19");
		// Password
		driver.findElement(By.xpath("//*[@id='loginForm']/div/div[2]/div/label/input")).sendKeys("Ragul@123");
		
		// click on login
		driver.findElement(By.xpath("//*[@id='loginForm']/div/div[3]/button/div")).click();
	
		driver.manage().window().maximize();
		
		Thread.sleep(5000);
		
		// click on login
		driver.findElement(By.xpath("//*[contains(text(), 'Not Now')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(text(), 'Not Now')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@aria-label='Messenger']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(text(), 'Ragul')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@placeholder='Message...']")).sendKeys("Hi");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[contains(text(), 'Send')]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@aria-label='Settings']")).click();
		
		driver.findElement(By.xpath("//*[contains(text(), 'Log out')]")).click();
		Thread.sleep(2000);
		
		driver.close();
	
	}

}
