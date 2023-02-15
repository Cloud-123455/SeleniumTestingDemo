package glue;


import java.io.File;


import Testing.Basepage;
import Testing.ExcelReport;
import Testing.FetchProperty;
import Testing.Gmail_Testing;
import Testing.Instagram_Testing_Demo;
import Testing.WordReport;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Step_definition extends Basepage {
	
	public Gmail_Testing GT;
	public Instagram_Testing_Demo IT;
	
	@Before
	public void beforeHook(Scenario scenario) throws Throwable
	{
		String featureName = scenario.getId();
		featureName = featureName.substring(featureName.lastIndexOf("/")+1, featureName.lastIndexOf("."));
		String scenarioName = scenario.getName();
		String[] scenarioData = scenarioName.split(":::");
		scenarioName = scenarioName.replace(":::", "_").replaceAll("\\s+", "");
		String tags = scenario.getSourceTagNames().toString().replace("[","").replace("]","").replace(", ", "_");
		scrFolder = System.getProperty("scr.folder.main").replace("/", "\\") + "snap_"+scenarioName +"_"+Thread.currentThread().getId();
		new File(scrFolder).mkdir();
		System.setProperty("scr.folder", scrFolder);
		
		SetTestExecutionData();
		Settestinputs();
		Settestinputs_testdata();
		
		String sheetpath = FetchProperty.Getprop("testdata_SheetPath", "global");
		
		environmentSheetPath = FetchProperty.Getprop("Environment_SheetPath", "global");
		environmentName = FetchProperty.Getprop("Environment_SheetName", "global");
		
		
		if(scenarioData.length == 2)
		{
			excelTestData = getDataFromExceltoHaspMap(sheetpath, scenarioData[0].trim(), scenarioData[1].trim());
			excelTestData.put("FeatureSheet_Name", scenarioData[0].trim());
			excelTestData.put("FeatureScenario_Name", scenarioData[1].trim());
		}
		else
		{
			excelTestData = getDataFromExceltoHaspMap(sheetpath, scenarioData[1].trim(), scenarioData[2].trim());
			excelTestData.put("FeatureSheet_Name", scenarioData[1].trim());
			excelTestData.put("FeatureScenario_Name", scenarioData[2].trim());
		}
		
		String TC = featureName+"_"+scenarioName+"_"+tags;
		
		if(extent == null)
		{
			ReportStart();
		}
		test = extent.createTest(TC);
		
		testExecutionData.put("ThreadID", Thread.currentThread().getId());
		testExecutionData.put("Path", scrFolder);
		testExecutionData.put("FeatureName", featureName);
		testExecutionData.put("Tags", tags);
		testExecutionData.put("TestCase", TC);
		testExecutionData.put("TestID", TC);
		testExecutionData.put("ExtentTest", test);
		testExecutionData.put("ScenarioName", scenarioName);
		
		String wordFileName = "Word_" + featureName + "_" + scenarioName;
		String excelFileName = "Excel_" + featureName + "_" + scenarioName;
		
		if(wordReport == null)
		{
			wordReport = new WordReport();
		}
		
		if(excelReport == null)
		{
			excelReport = new ExcelReport();
		}
		
		testExecutionData.put("ExtentReport", extent);
		testExecutionData.put("WordReport", wordReport);
		testExecutionData.put("ExcelReport", excelReport);
		
		testinputs.put(getTestExecutionData().get("TheadID"), testExecutionData);
		StoreTestData.put(getTestExecutionData().get("TheadID"), excelTestData);
		
		wordReport.open(scrFolder, (String) wordFileName);
		excelReport.openExcel(scrFolder+"/"+excelFileName);
		
		System.out.println("Test Started : "+ TC + " with Thread ID : "+Thread.currentThread().getId());
		
		
	}
	
	@After
	public void aferHook(Scenario scenario) throws Throwable
	{
		
		printconsole("Test Completed : "+scenario.getName()+" and Tag ID: "+ scenario.getSourceTagNames().toString());
		wordReport.close();
		wordReport=null;
		excelReport.closeExcel();
		excelReport=null;
		extent.flush();
		
	}
	
		

	

//	@Given("I want to write a step with precondition")
//	public void i_want_to_write_a_step_with_precondition() {
//	   
//	}
//
//	@Given("some other precondition")
//	public void some_other_precondition() {
//	   
//	}
//
//	@When("I complete action")
//	public void i_complete_action() {
//	    
//	}
//
//	@When("some other action")
//	public void some_other_action() {
//	    
//	}
//
//	@When("yet another action")
//	public void yet_another_action() {
//	    
//	}
//
//	@Then("I validate the outcomes")
//	public void i_validate_the_outcomes() {
//	    
//	}
//
//	@Then("check more outcomes")
//	public void check_more_outcomes() {
//	 
//	}
//
//	@Given("I want to write a step with name1")
//	public void i_want_to_write_a_step_with_name1() {
//	    
//	}

//	@When("I check for the {int} in step")
//	public void i_check_for_the_in_step(Integer int1) {
//	    
//	}

//	@Then("I verify the success in step")
//	public void i_verify_the_success_in_step() {
//	   
//	}
//
//	@Given("I want to write a step with name2")
//	public void i_want_to_write_a_step_with_name2() {
//	    
//	}
//
//	@Then("I verify the Fail in step")
//	public void i_verify_the_fail_in_step() {
//	   
//	}

		@Given("User login into Application with <UserID> and <Password>")
		public void user_login_into_application_with_user_id_and_password() throws Throwable {
			
		if(GT == null)
		{
			GT = new Gmail_Testing();
		}
		GT.user_login_into_application_with_user_id_and_password();
		}
		
		@Given("User login into Instagram Application with <UserID> and <Password>")
		public void user_login_into_application_with_user_id_and_password1() throws Throwable {
		
		if(IT == null)
		{
			IT = new Instagram_Testing_Demo();
		}
		IT.user_login_into_application_with_user_id_and_password1();
		
}
		
		@When("User send the mail")
		public void user_send_the_mail() throws Throwable {
			
		if(GT == null)
		{
			GT = new Gmail_Testing();
		}
			
		GT.user_send_the_mail();
		    
		}
		
		@When("User send the message")
		public void user_send_the_message() throws Throwable {
			
		if(IT == null)
		{
			IT = new Instagram_Testing_Demo();
		}
			
		IT.user_send_the_message();
		    
		}
		

		@And("User Logoff from the Application")
		public void user_logoff_from_the_application() throws Throwable {
			
			if(GT == null)
			{
				GT = new Gmail_Testing();
			}
				
			GT.user_logoff_from_the_application();
			
		}
		
		@And("User Logoff from the Instagram Application")
		public void user_logoff_from_the_application1() throws Throwable {
			
			if(IT == null)
			{
				IT = new Instagram_Testing_Demo();
			}
			IT.user_logoff_from_the_application1();
			    
			}
		    
	


}
