//package Testing;
//
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class SuiteClass {
//	
//	String reportsPath = System.getProperty("user.dir") + "/Reports/Results_";
//	public static String timestamp;
//	public static String Reportfilepath;
//	
//	
//	public void beforeSuite()
//	{
//		try
//		{
//			timestamp = new SimpleDateFormat("yyyyMMdd-hhmmss").format(new Date());
//			String filePath = reportsPath+timestamp;
//			Reportfilepath = filePath;
//			File dir = new File(filePath);
//			dir.mkdirs();
//			String scrFolderMain = filePath + "/";
//			System.setProperty("scr.folder.main", scrFolderMain);
//			
//		}
//		catch (Exception e)
//		{
//			System.out.println("Re-Run");
//		}
//	}
//
//}
