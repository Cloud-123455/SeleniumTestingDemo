package Testing;

//import java.time.Duration;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.When;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;



public class Instagram_Testing_Demo extends Basepage{

	WebDriver driver=new ChromeDriver();
	
	@Given("User login into Application with <UserID> and <Password>")
	public void user_login_into_application_with_user_id_and_password1() throws InterruptedException
	
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gokul\\chromedriver_win32\\chromedriver.exe");
		
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
	}
	
	@When("User send the message")
	public void user_send_the_message() throws Throwable
	
	{
		
		driver.findElement(By.xpath("//*[@aria-label='Messenger']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(text(), 'Ragul')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@placeholder='Message...']")).sendKeys("Hi");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[contains(text(), 'Send')]")).click();
		Thread.sleep(2000);
		
		Report_Log("testing","Passed");
		
	}
	
	@And("User Logoff from the Application")
	public void user_logoff_from_the_application1() throws InterruptedException
	
	{		
		driver.findElement(By.xpath("//*[@aria-label='Settings']")).click();
		
		driver.findElement(By.xpath("//*[contains(text(), 'Log out')]")).click();
		Thread.sleep(2000);
		
		driver.close();
		
	}

	
	public static void main(String[] args) throws Throwable{  
        
		Instagram_Testing_Demo Test = new Instagram_Testing_Demo();              
		Test.user_login_into_application_with_user_id_and_password1();                                              
		Test.user_send_the_message();                                       
		Test.user_logoff_from_the_application1();                                             

}
}
