package Testing;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Actionclass {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gokul\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.guru99.com/test/newtours/index.php"); 
		driver.manage().window().maximize();
		WebElement username = driver.findElement(By.xpath("//input[@name='userName']"));
		Thread.sleep(2000);
		Actions act=new Actions(driver);
		Action singlework=act.moveToElement(username).click().sendKeys(username, "hello").keyDown(username, Keys.SHIFT).sendKeys(username,"welcome").keyUp(username, Keys.SHIFT).build();
		singlework.perform();
		driver.close();
	}

}
