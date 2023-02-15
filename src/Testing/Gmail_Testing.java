package Testing;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.When;

import java.time.Duration;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Gmail_Testing {

	@Given("User login into Application with <UserID> and <Password>")
	public void user_login_into_application_with_user_id_and_password() throws Throwable
	
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gokul\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		String baseurl = "https://mail.google.com/";
		driver.get(baseurl);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		// locate "Email or phone" text box inpiut using "xpath" and enter email id
		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("Ragulpalanisamy19@gmail.com");
		
		// locate "Next" button and click
		
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		WebElement Password = driver.findElement(By.xpath("//input[@name='password']"));
		Password.sendKeys("8144415360");
		
		Password.sendKeys(Keys.TAB);
		Password.sendKeys(Keys.TAB);
		// locate "Next" button and click
		
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Not now')]")).click();
		driver.manage().window().maximize();
	}
	
	@When("User send the mail")
	public void user_send_the_mail() throws Throwable
	
	{
		WebDriver driver=new ChromeDriver();
		driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=':mx']"));
		driver.findElement(By.xpath("//*[@id=':tr']")).sendKeys("Ragulpalanisamy19@gmail.com");
		driver.findElement(By.xpath("//*[@id=':pv']")).sendKeys("Test Mail");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=':r2']")).sendKeys("Testing prurpose");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=':pl']")).click();
		Thread.sleep(2000);
		
	}
	
	@And("User Logoff from the Application")
	public void user_logoff_from_the_application() throws Throwable
	
	{
		WebDriver driver=new ChromeDriver();
		driver.findElement(By.xpath("//*[@id='gb']/div[2]/div[3]/div[1]/div[2]/div/a/img")).click();
		driver.findElement(By.xpath("//*[@id='yDmH0d']/c-wiz/div/div/div/div/div[2]/div[3]/span/a")).click();
		driver.close();
		
	}

}
