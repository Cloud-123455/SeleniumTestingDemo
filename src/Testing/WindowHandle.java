package Testing;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandle {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gokul\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.guru99.com/popup.php"); 
		driver.manage().window().maximize();
		String main=driver.getWindowHandle();
		System.out.println(main);
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		Thread.sleep(5000);
		Set<String> wind=driver.getWindowHandles();
		java.util.Iterator<String> i=wind.iterator();
		while(i.hasNext())
		{
			String child=i.next();
			System.out.println(i.next());
			if(!main.equalsIgnoreCase(child))
			{
				System.out.println(child);
				driver.switchTo().window(child);
				Thread.sleep(5000);
				driver.close();
				
			}
		}
		driver.switchTo().window(main);
		Thread.sleep(3000);
		driver.quit();
		
	}

}
