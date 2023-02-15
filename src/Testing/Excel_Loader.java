package Testing;

//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel_Loader {

public static final String Excelfileloation = "./config/excelData/Sample_TestData.xlsx";

private static final String BLANK = null;
	
//	public static FileInputStream fis;
//	public static XSSFWorkbook workbook;
//	public static XSSFSheet sheet;
//	public static XSSFRow row;
//	
//	public static void loadExcel() throws Exception
//	{
//		System.out.println("Loading Excel data....");
//		
//		File file = new File(Excelfileloation);
//		fis = new FileInputStream(file);
//		workbook = new XSSFWorkbook(fis);
//		sheet = workbook.getSheet("TestData");
//		fis.close();
//			
//	}
//	
//	public static Map<String, Map<String, String>> getDataMap() throws Exception
//	{
//		if(sheet == null)
//		{
//		}
//			Map<String, Map<String, String>> superMap = new HashMap<String, Map<String, String>>();
//			Map<String, String> myMap = new HashMap<String, String>();
//			
//			for(int i = 1; i<sheet.getLastRowNum()+1; i++)
//			{
//				row = sheet.getRow(i);
//				String keyCell = row.getCell(i).getStringCellValue();
//				
//				int colNum = row.getLastCellNum();
//				for(int j=1; j<colNum; j++)
//				{
//					String value = row.getCell(j).getStringCellValue();
//					myMap.put(keyCell, value);
//					
//				}
//				superMap.put("MASTERDATE", myMap);
//			}
//			
//			return superMap;
//		}
//	
//	public static String getValue(String key) throws Exception
//	{
//		Map<String, String> myVal = getDataMap().get("MASTERDATA");
//		String retValue = myVal.get(key);
//		
//		return retValue;
//	}
//	
//	public static void main(String[] args) throws Exception
//	{
//		System.out.println(getValue(""));
//	}

	public static Map<Integer, List<String>> readExcelSheet(String sheetPath, String sheetName) throws Exception {
		
//		File src = new File(sheetPath);
//		
//		FileInputStream fis = new FileInputStream(src);
//		
//		XSSFWorkbook xsf = new XSSFWorkbook(fis);
//		
//		String entry1 = sheetName.getRow(1).getCell(1).getStringCellValue();
//		
//		xsf.close();
		
		try(InputStream excelInputStream = new FileInputStream(sheetPath);
			Workbook workbook = WorkbookFactory.create(excelInputStream))
				{
			
		return getWorkSheet(workbook, sheetName);		
				}
		
		catch (FileNotFoundException e)
		{
		
		return new HashMap<>();
					}
	
	}
	
	
	private static Map<Integer, List<String>> getWorkSheet(Workbook workbook, String sheetName) {
		
		Map<Integer, List<String>> sheetData = new HashMap<>();
		
		int rowIndex = 1;
		Sheet sheet = workbook.getSheet(sheetName);
		int firstRowNum = sheet.getFirstRowNum();
		int lastCellNum = sheet.getRow(firstRowNum).getLastCellNum();
		for (Row row: sheet)
		{
			List<String> rowData = new ArrayList<>();
			for(int cn=0; cn<lastCellNum; cn++)
			{
				Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				CellType cellType = cell.getCellType();
				switch(cellType)
				{
				case NUMERIC:
					if(DateUtil.isCellDateFormatted(cell))
					{
						SimpleDateFormat sdf = getDateValue(cell);
						double value = cell.getNumericCellValue();
						Date date = DateUtil.getJavaDate(value);
						rowData.add(sdf.format(date));
						}
					else
					{
						rowData.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
					}
					break;
				case STRING:
					rowData.add(cell.getStringCellValue());
					break;
				case FORMULA:
					rowData.add(cell.getCellFormula());
					break;
				case BOOLEAN:
					rowData.add(Boolean.toString(cell.getBooleanCellValue()));
					break;
				default:
					rowData.add(BLANK);
					break;
				
				}
			}
			sheetData.put(rowIndex, rowData);
			rowIndex++;
		}
		return sheetData;
	}

	private static SimpleDateFormat getDateValue(Cell cell) {
		
		short format = cell.getCellStyle().getDataFormat();
		SimpleDateFormat sdf = null;
		if(format == 14 || format == 31 || format == 57 || format == 58)
		{
			sdf= new SimpleDateFormat("yyyy-MM-dd");
		}
		else if(format == 20 || format == 32 || format == 21)
		{
			sdf= new SimpleDateFormat("HH:mm:ss");
		}
		else 
		{
			sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		
		return sdf;
	}

	public static List<String> getRow(Map<Integer, List<String>> sheetMap, int i) {
		
		
		if(sheetMap == null || i<=0)
		{
			return new ArrayList<>(0);
		}
		
		if(i > sheetMap.keySet().size())
		{
			return new ArrayList<>(0);
		}
		
		return sheetMap.get(i);
	}

	public static List<String> getColumn(Map<Integer, List<String>> sheetMap, int i) {
		
		
		if(sheetMap == null)
		{
			return new ArrayList<>(0);
		}
		
		List<String> columnData = new ArrayList<>();
		Set<Map.Entry<Integer, List<String>>> entries = sheetMap.entrySet();
		Iterator<Map.Entry<Integer, List<String>>> iterator = entries.iterator();
		
		while(iterator.hasNext())
		{
			Map.Entry<Integer, List<String>> rowData = iterator.next();
			List<String> row = rowData.getValue();
			if(i > row.size())
			{
				return new ArrayList<>(0);
			}
			
			columnData.add(row.get(i-1));
		}
		
		
		return columnData;
	}

	public static String getCell(Map<Integer, List<String>> sheetMap, int k, String getColumnValue) {
		
		if(k > sheetMap.keySet().size() || k <= 0 || null == getColumnValue || getColumnValue.isEmpty())
		{
			return null;
		}
		
		List<String> columns = getRow(sheetMap, 1);
		int columnIndex = -1;
		for(int i=0; i < columns.size(); i++)
		{
			if(columns.get(i).equals(getColumnValue))
			{
				columnIndex = i+1;
			}
		}
		
		if(columnIndex != -1)
		{
			return getCell(sheetMap,k,columnIndex);
		}
		
		return null;
	}
	
	public static String getCell(Map<Integer, List<String>> sheetMap, int k, int columnIndex) {
		
		if(sheetMap == null || k <= 0 || columnIndex <= 0)
		{
			return null;
		}
		
		List<String> row = getRow(sheetMap, k);
		return row.get(columnIndex - 1);
		
	
	}

}
