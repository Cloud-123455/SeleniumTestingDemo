package Testing;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.awt.image.BufferedImage;
import java.awt.Robot;
import java.awt.Toolkit;



import java.awt.Rectangle;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;


import runner.Test_Runner;

public class Basepage {
	
	public String webAgentName = "WebAgent";
	public ExtentReports extent;
	public ExcelReport excelReport;
	public WordReport wordReport;
	public ExtentTest test;
	public String scrFolder;
	public String imagePath;
	WebDriver rdriver=new ChromeDriver();
	public WebDriver driver;
	@SuppressWarnings("rawtypes")
	public static Map<Object, Map> testinputs;
	public WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	public static Map<Object, Map> StoreTestData;
//	@SuppressWarnings("rawtypes")
	public Map<String, String> excelTestData;
//	@SuppressWarnings("rawtypes")
	public Map<String,Object> testExecutionData;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentSparkReporter sparkFail;
	public static String environmentSheetPath;
	public static String environmentName;
	
	
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	
	

	@SuppressWarnings("unchecked")
	public Basepage()
	{
		if(driver == null & testinputs!=null)
		{
			if(testinputs.get(Thread.currentThread().getId())!=null)
			{
				extent = (ExtentReports) testinputs.get(Thread.currentThread().getId()).get("ExtentReport");
				test = (ExtentTest) testinputs.get(Thread.currentThread().getId()).get("ExtentTest");
				wordReport = (WordReport) testinputs.get(Thread.currentThread().getId()).get("WordReport");
				excelReport = (ExcelReport) testinputs.get(Thread.currentThread().getId()).get("ExcelReport");
				scrFolder = (String) testinputs.get(Thread.currentThread().getId()).get("Path");
				
			}
		}
		
		if(StoreTestData!=null)
		{
			if(StoreTestData.get(Thread.currentThread().getId())!=null)
			{
				excelTestData = StoreTestData.get(Thread.currentThread().getId());
			}
		}
		
	}
	
//	public static String getURLfromExcel(String sheetPath, String sheetName, String fetchColumnName, String getColumnValue, String givenEnvironmentName)
//	{
//	
//		String URL = "";
//		
//		Map<Integer, List<String>> sheetMap = ExcelHelper.readExcelSheet(sheetPath, sheetName);
//		List<String> sheetHeader = ExcelHelper.getRow(sheetMap, 1);
//		int colSize = sheetHeader.size();
//		
//		int colIndex = 0;
//		
//		for(int j=0;j<colSize; j++)
//		{
//			if(fetchColumnName.trim().equals(sheetHeader.get(j).trim()))
//			{
//				colIndex = j+1;
//				break;
//			}
//		}
//		
//		List<String> getEnvNamefromExcel = ExcelHelper.getColumn(sheetMap, colIndex);
//		
//		int RowSize = getEnvNamefromExcel.size();
//		int k=0;
//		
//		for(int i=0; i<RowSize;i++)
//		{
//			if(givenEnvironmentName.trim().equals(getEnvNamefromExcel.get(i).trim()))
//			{
//				k=i+1;
//				break;
//			}
//		}
//		
//		if(k>0)
//		{
//			URL = ExcelHelper.getCell(sheetMap, k, getColumnValue);
//		}
//		return URL;
//	}
	
//	public Map<String, String> getDataFromExceltoHaspMap(String SheetPath, String sheetName, int rowNmber) throws Throwable
//	{
//		Map<String, String> excelData = new HashMap<String, String>();
//		Map<Integer, List<String>> sheetMap = ExcelHelper.readExcelSheet(SheetPath, sheetName);
//		List<String> sheetHeader = ExcelHelper.getRow(sheetMap, 1);
//		List<String> dataRow = ExcelHelper.getRow(sheetMap, rowNumber);
//		int listSize = sheetHeader.size();
//		
//		for(int i=0; i<listSize; i++)
//		{
//			excelData.put(sheetHeader.get(i), dataRow.get(i));
//		}
//		
//		
//		return excelData;
//	}
//	
//	public Map<String, String> getDataFromExceltoHaspMap(String SheetPath, String sheetName, String scenarioName) throws Throwable
//	{
//		Map<String, String> excelData = new HashMap<String, String>();
//		Map<Integer, List<String>> sheetMap = ExcelHelper.readExcelSheet(SheetPath, sheetName);
//		List<String> sheetHeader = ExcelHelper.getRow(sheetMap, 1);
//		List<String> getScenarioNamefromExcel = ExcelHelper.getColumn(sheetMap, 1);
//		int ColumnRowSize = getScenarioNamefromExcel.size();
//		
//		int k=0;
//		for(int i=0; i<ColumnRowSize; i++)
//		{
//			if(scenarioName.trim().equals(getScenarioNamefromExcel.get(i).trim()))
//			{
//				k=i+1;
//				break;
//			}
//		}
//		
//		String temp="";
//		List<String> dataRow = ExcelHelper.getRow(sheetMap, k);
//		int listSize = sheetHeader.size();
//		
//		for(int i=0; i<listSize; i++)
//		{
//			try
//			{
//				temp = dataRow.get(i);
//			}
//			catch(Exception ty)
//			{
//				temp="";
//			}
//			excelData.put(sheetHeader.get(i), temp);
//		}
//		
//		return excelData;
//	}
	
	public Map<String, String> getDataFromExceltoHaspMap(String SheetPath, String sheetName, int rowNmber) throws Throwable
	{
		Map<String, String> excelData = new HashMap<String, String>();
		Map<Integer, List<String>> sheetMap = Excel_Loader.readExcelSheet(SheetPath, sheetName);
		List<String> sheetHeader = Excel_Loader.getRow(sheetMap, 1);
		List<String> dataRow = Excel_Loader.getRow(sheetMap, 1);
		int listSize = sheetHeader.size();
		
		for(int i=0; i<listSize; i++)
		{
			excelData.put(sheetHeader.get(i), dataRow.get(i));
		}
		
		
		return excelData;
	}
	
	public Map<String, String> getDataFromExceltoHaspMap(String SheetPath, String sheetName, String scenarioName) throws Throwable
	{
		Map<String, String> excelData = new HashMap<String, String>();
		Map<Integer, List<String>> sheetMap = Excel_Loader.readExcelSheet(SheetPath, sheetName);
		List<String> sheetHeader = Excel_Loader.getRow(sheetMap, 1);
		List<String> getScenarioNamefromExcel = Excel_Loader.getColumn(sheetMap, 1);
		int ColumnRowSize = getScenarioNamefromExcel.size();
		
		int k=0;
		for(int i=0; i<ColumnRowSize; i++)
		{
			if(scenarioName.trim().equals(getScenarioNamefromExcel.get(i).trim()))
			{
				k=i+1;
				break;
			}
		}
		
		String temp="";
		List<String> dataRow = Excel_Loader.getRow(sheetMap, k);
		int listSize = sheetHeader.size();
		
		for(int i=0; i<listSize; i++)
		{
			try
			{
				temp = dataRow.get(i);
			}
			catch(Exception ty)
			{
				temp="";
			}
			excelData.put(sheetHeader.get(i), temp);
		}
		
		return excelData;
	}
	
	public static String getURLfromExcel(String sheetPath, String sheetName, String fetchColumnName, String getColumnValue, String givenEnvironmentName) throws Throwable
	{
	
		String URL = "";
		
		Map<Integer, List<String>> sheetMap = Excel_Loader.readExcelSheet(sheetPath, sheetName);
		List<String> sheetHeader = Excel_Loader.getRow(sheetMap, 1);
		int colSize = sheetHeader.size();
		
		int colIndex = 0;
		
		for(int j=0;j<colSize; j++)
		{
			if(fetchColumnName.trim().equals(sheetHeader.get(j).trim()))
			{
				colIndex = j+1;
				break;
			}
		}
		
		List<String> getEnvNamefromExcel = Excel_Loader.getColumn(sheetMap, colIndex);
		
		int RowSize = getEnvNamefromExcel.size();
		int k=0;
		
		for(int i=0; i<RowSize;i++)
		{
			if(givenEnvironmentName.trim().equals(getEnvNamefromExcel.get(i).trim()))
			{
				k=i+1;
				break;
			}
		}
		
		if(k>0)
		{
			URL = Excel_Loader.getCell(sheetMap, k, getColumnValue);
		}
		return URL;
	}

	public void ReportStart() throws Throwable
	{
		htmlReporter = new ExtentSparkReporter(".Reports/Results_"+Test_Runner.timestamp+"/ExtentReport.html").viewConfigurer().viewOrder().as(new ViewName[]
				{
					ViewName.DASHBOARD,
					ViewName.TEST,
					ViewName.AUTHOR,
					ViewName.DEVICE,
					ViewName.EXCEPTION,
					ViewName.LOG
				}).apply();
		
		sparkFail = new ExtentSparkReporter(".Reports/Results_"+Test_Runner.timestamp+"/ExtentReport.html").filter().statusFilter().as(new Status[] { Status.FAIL }).apply();
	
		extent = new ExtentReports();
		
		extent.attachReporter(sparkFail, htmlReporter);
		
		extent.setSystemInfo("SYSTEM", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("HOST NAME", System.getProperty("COMPUTERNAME"));
		extent.setSystemInfo("JAVA Version", System.getProperty("java.version"));
		
		htmlReporter.config().setDocumentTitle("SAMPLE TESTING");
		htmlReporter.config().setReportName("Automation Report");
		
	
	
	}
	
	public void SetTestExecutionData()
	{
		testExecutionData = new HashMap<String, Object>();
		
	}
	
	@SuppressWarnings("rawtypes")
	public void Settestinputs()
	{
		testinputs = new HashMap<Object, Map>();
		
	}
	
	@SuppressWarnings("rawtypes")
	public void Settestinputs_testdata()
	{
		StoreTestData = new HashMap<Object, Map>();
		
	}
	
	public Map<String, Object> getTestExecutionData()
	{
		return testExecutionData;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<Object, Map> gettestinputs_testdata()
	{
		return StoreTestData;
	}
	
	public void printconsole(String content)
	{
		System.out.println(content);
		
	}
	
	public void Save_To_NotePad(String FilePath, String result) throws IOException
	{
		FileWriter fr = new FileWriter(FilePath, true);
		BufferedWriter br = new BufferedWriter(fr);
		br.write(result);
		br.close();
	}
	
	public boolean isAlertPresent()
	{
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(0));
		try
		{
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		}
		catch (Throwable eTO)
		{
			foundAlert = false;
		}
		return foundAlert;
	}
	
	public void takeScreenshot(String methodname) throws Throwable
	{
		
		String targetFileName = scrFolder + "/" + new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime()).toString()+ methodname + ".png";
		File desFile = new File(targetFileName);
		
		if(!isAlertPresent())
		{
			try
			{
				File scrFile = ((TakesScreenshot) rdriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, desFile);
			}
			catch (Exception e)
			{
				File scrFile = ((TakesScreenshot) rdriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, desFile);
			}
		}
		else
		{
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture = new Robot().createScreenCapture(screenRect);
			ImageIO.write(capture, "png", new File(targetFileName));
			
		}
		
		imagePath = desFile.getAbsolutePath();
		
		Thread.sleep(2000);
		wordReport.insertImage(targetFileName);
		excelReport.insertImageFile(targetFileName);
		
	}
	
	public void Report_Log(String StepName, String Stepstatus) throws Throwable
	{
		Report_log(StepName, "","", Stepstatus);
	}
	public void Report_log(String StepName, String Expected, String Actual, String Stepstatus) throws Throwable
	{
		try
		{
			wordReport.report(StepName,Expected,Actual,Stepstatus);
			excelReport.stepReport(StepName,Expected,Actual,Stepstatus);
			
			String status_message = Stepstatus.toUpperCase() + "_" + StepName;
			if(!Expected.equals("") || !Actual.equals(""))
			{
				System.out.println("Expected : " + Expected);
				System.out.println("Actual : " + Actual);
			}
			
			printconsole(status_message);
			Save_To_NotePad(scrFolder + "\\Log.txt", status_message+"\n");
			
			if(status_message.length() > 20)
			{
				status_message = status_message.substring(0,20).replace("\\", "").replace("/", "").replace(":", "").replace("*", "").replace("?", "").replace("\"", "").replace("<", "").replace(">", "").replace("|", "").replace("'", "");
				if(Stepstatus.equalsIgnoreCase("LOG"))
				{
					System.out.println("NO NEED OF IMAGE INSERT JUST LOG THE STEP");
				
				}
				else
				{
					takeScreenshot(status_message);
				}
				
				switch(Stepstatus.toUpperCase().trim())
				{
				case "PASSED":
					test.log(Status.PASS, StepName, MediaEntityBuilder.createScreenCaptureFromBase64String(imagePath).build());
					break;
				case "FAILED":
					test.log(Status.FAIL, StepName, MediaEntityBuilder.createScreenCaptureFromBase64String(imagePath).build());
//					assertTrue(false);
					break;
				case "INFO":
					test.log(Status.INFO, StepName, MediaEntityBuilder.createScreenCaptureFromBase64String(imagePath).build());
					break;
				case "WARNING":
					test.log(Status.WARNING, StepName, MediaEntityBuilder.createScreenCaptureFromBase64String(imagePath).build());
					break;
				case "LOG":
					test.log(Status.SKIP, StepName, MediaEntityBuilder.createScreenCaptureFromBase64String(imagePath).build());
					break;
				default:
					test.log(Status.INFO, StepName, MediaEntityBuilder.createScreenCaptureFromBase64String(imagePath).build());
					break;
					
				}
			}
		}catch (Exception e)
			{
				e.printStackTrace();
//				test.log(Status.FAIL, StepName);
			}
			
		}

	}


