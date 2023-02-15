package Testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FetchProperty {
	
	public static String PropertyFileLoad(String strProperty, String propertyfilename)
	{
		String strReadvalue = null;
		File file = null;
		
		if(strProperty.equalsIgnoreCase("testdata_SheetPath") && propertyfilename.equalsIgnoreCase("global"))
		{
			try
			{
				String country = System.getenv("Country");
				if(country.equalsIgnoreCase("") || country.equalsIgnoreCase(null) || country.isEmpty())
				{
					strProperty = "testdata_SheetPath";
				}
				else
				{
					strProperty = country+"_testdata_SheetPath";
				}
			}
			catch(Exception e)
			{
				
			}
		}
		
		try
		{
			Properties prop = new Properties();
			if(propertyfilename.equalsIgnoreCase("global"))
			{
				file = new File("./config/PropertiesFiles/global.properties");
			}
			else
			{
				file = new File("./config/PropertiesFiles/"+propertyfilename+".properties");
			}
			
			FileInputStream inputStream;
			inputStream = new FileInputStream(file);
			
			if(inputStream!=null)
			{
				prop.load(inputStream);
			
			}
			
			strReadvalue=prop.getProperty(strProperty);
			
			}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return strReadvalue;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return strReadvalue;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return strReadvalue;
		}
		
		return strReadvalue;
	}
		
		public static String Getprop(String strProperty, String propertyfilename)
		{
			String strValue = null;
			try
			{
				if(PropertyFileLoad(strProperty,propertyfilename).trim()!=null&&!PropertyFileLoad(strProperty,propertyfilename).trim().equalsIgnoreCase(""))
				{
					strValue=PropertyFileLoad(strProperty,propertyfilename).trim();
				}
				else
				{
					
				}
				return strValue;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return strValue;
		}

}
