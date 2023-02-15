package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Demo_Testing {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gokul\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		String baseurl = "https://demo.guru99.com/test/newtours/";
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = "";
		
		driver.get(baseurl);
		driver.manage().window().maximize();
		
		actualTitle = driver.getTitle();
		
		if(actualTitle.contentEquals(expectedTitle))
		{
			System.out.println("Passed: Test Passed!");
		}
		else
		{
			System.out.println("Failed: Test Failed!");
		}	
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a")).click();
		driver.findElement(By.name("firstName")).sendKeys("Ragul");
		driver.findElement(By.name("lastName")).sendKeys("Palanisamy");
		driver.findElement(By.name("userName")).sendKeys("Ragulpalanisamy198@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.name("city")).sendKeys("Coimbatore");
		driver.findElement(By.name("state")).sendKeys("Tamilnadu");
		Thread.sleep(2000);
		Select drpcountry = new Select(driver.findElement(By.name("country")));
		drpcountry.selectByVisibleText("INDIA");
//		drpcountry.selectByValue("HONG KONG");
//		drpcountry.selectByIndex(1);
		Thread.sleep(1000);
		driver.findElement(By.name("submit")).click();
		Thread.sleep(1000);
		driver.close();
//		driver.quit();
		
		
		
		
	}

}
