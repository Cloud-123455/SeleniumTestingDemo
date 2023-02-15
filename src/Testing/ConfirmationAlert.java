package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConfirmationAlert {

	public static void main(String[] args) throws InterruptedException {

				System.setProperty("webdriver.chrome.driver", "C:\\Users\\gokul\\chromedriver_win32\\chromedriver.exe");
				WebDriver driver=new ChromeDriver();
				driver.get("https://demoqa.com/alerts"); 
				driver.manage().window().maximize();
				driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
				Thread.sleep(4000);
				driver.switchTo().alert().dismiss();
				driver.close();

	}

}
