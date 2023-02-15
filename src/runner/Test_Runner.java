package runner;

	
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    
        plugin = {"pretty","html:target/cucumber-report/report.html", "json:target/cucumber-parallel/1.json"},
        features = {"feature"},
        glue = {"glue"},
        tags = {"@Instagram_Testing"}

        )
public class Test_Runner {
	
	public static String reportsPath = System.getProperty("user.dir") + "/Reports/Results_";
	public static String timestamp;
	public static String Reportfilepath;
	
	@BeforeClass
	public static void beforeSuite()
	{
		try
		{
			timestamp = new SimpleDateFormat("yyyyMMdd-hhmmss").format(new Date());
			String filePath = reportsPath+timestamp;
			Reportfilepath = filePath;
			File dir = new File(filePath);
			dir.mkdirs();
			String scrFolderMain = filePath + "/";
			System.setProperty("scr.folder.main", scrFolderMain);
			
		}
		catch (Exception e)
		{
			System.out.println("Re-Run");
		}
	}
	public Test_Runner()
	{
		
	}

}