package Testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	
		
		public void WriteExcel1(String sheetName, String cellvalue, int row, int col) throws IOException
		{
			String excelpath = "C:\\Users\\gokul\\Sample Testing\\Demo_Testing\\config\\student";
			File file = new File(excelpath);
			
			FileInputStream fis = new FileInputStream(file);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = wb.getSheet(sheetName);
			
			sheet.getRow(row).createCell(col).setCellValue(cellvalue);
			
			FileOutputStream fos = new FileOutputStream(new File(excelpath));
			
			wb.write(fos);
			
			wb.close();
			
			fos.close();
		}
		
		public static void main(String[] args) throws IOException {
			
			WriteExcel Test = new WriteExcel(); 
			Test.WriteExcel1("Student data","Ragul",1,2);
			

	}

}
