package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Verizon {
	
	public static void main(String[] args) throws InterruptedException {
	
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\gokul\\chromedriver_win32\\chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	String baseurl = "https://secure.verizon.com/"; 
	driver.get(baseurl);
	driver.manage().window().maximize();
	Thread.sleep(3000);
	
	//*[@id='gnav20-sign-id-mobile']
	// User id
	driver.findElement(By.xpath("//*[@id='gnav20-sign-id-mobile']")).click();
	// Password
	driver.findElement(By.xpath("//*[@id='gnav20-sign-id-list-item-1-mobile']")).click();
	
	// click on login
	driver.findElement(By.xpath("//*[@id='IDToken1']")).click();

//	driver.manage().window().maximize();
	
	Thread.sleep(5000);
	
	
}
}