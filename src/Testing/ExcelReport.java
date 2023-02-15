package Testing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelReport {

	int pictureIdx;
	String strFileName = "";
	CreationHelper helper;
	SXSSFDrawing drawing;
	ClientAnchor anchor;
	
	FileInputStream inputstream;
	FileOutputStream fileOut;
	
	byte[] bytes;
	int col1 = 1;
	int col2 = 17;
	int row1 = 2;
	int row2 = 27;
	
	SXSSFWorkbook wb;
	SXSSFSheet sheet;
	SXSSFSheet summarysheet;
	public int stepcount = 1;
	public int PassedSteps=0, FailedSteps=0, InfoSteps=0,WarnSteps=0,TotalSteps=0;
	
	public void insertImageFile(String imgFile) throws Throwable
	{
		
		inputstream = new FileInputStream(imgFile);
		
		bytes = IOUtils.toByteArray(inputstream);
		
		pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
		
		inputstream.close();
		
		drawing = sheet.createDrawingPatriarch();
		
		anchor = helper.createClientAnchor();
		
		anchor.setCol1(col1);
		anchor.setRow1(row1);
		anchor.setCol2(col2);
		anchor.setRow2(row2);
		
		
		
		drawing.createPicture(anchor, pictureIdx);
		
		row1 = row1 + 30;
		row2 = row2 + 30;
	}
	
	
	public void stepReport(String stepName, String Expected, String Actual, String stepStatus)
	{
		String strWrite = stepStatus.toUpperCase();
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		int typeRow = row1 - 1;
		SXSSFRow row = sheet.createRow(typeRow);
		row.createCell(0).setCellValue(helper.createRichTextString(String.valueOf(stepcount)));
		if (strWrite.equalsIgnoreCase("PASSED") || strWrite.equalsIgnoreCase("PASS"))
		{
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			row.createCell(1).setCellValue(helper.createRichTextString(strWrite));
			row.getCell(1).setCellStyle(style);
			
		}
		else if (strWrite.equalsIgnoreCase("FAILED") || strWrite.equalsIgnoreCase("WARNING") || strWrite.equalsIgnoreCase("WARN") || strWrite.equalsIgnoreCase("FAIL"))
		{
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			row.createCell(1).setCellValue(helper.createRichTextString(strWrite));
			row.getCell(1).setCellStyle(style);
			
		}
		else
		{
			style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			row.createCell(1).setCellValue(helper.createRichTextString(strWrite));
			row.getCell(1).setCellStyle(style);
			
		}
		
		row.createCell(2).setCellValue(helper.createRichTextString(stepName));
		
		if(!Expected.equalsIgnoreCase("") || Expected.equalsIgnoreCase(null) || Expected.isEmpty())
		{
			row.createCell(3).setCellValue(helper.createRichTextString(Expected));
		}
		if(!Actual.equalsIgnoreCase("") || Actual.equalsIgnoreCase(null) || Actual.isEmpty())
		{
			row.createCell(4).setCellValue(helper.createRichTextString(Actual));
		}
		updateStepCounters(stepStatus);
		stepcount++;
	}
	
	
	public void openExcel(String fileName)
	{
		strFileName = fileName + "_" + new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime()).toString() + ".xlsx";
		wb = new SXSSFWorkbook();
		sheet = wb.createSheet("ScreenshotResults");
		sheet.setDefaultColumnWidth(10);
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		helper = wb.getCreationHelper();
		
		SXSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue(helper.createRichTextString("STEP_NO"));
		row.getCell(0).setCellStyle(style);
		
		row.createCell(1).setCellValue(helper.createRichTextString("STEP_STATUS"));
		row.getCell(1).setCellStyle(style);
		
		row.createCell(2).setCellValue(helper.createRichTextString("STEP_NAME"));
		row.getCell(2).setCellStyle(style);
		
		row.createCell(3).setCellValue(helper.createRichTextString("EXPECTED_VALUE"));
		row.getCell(3).setCellStyle(style);
		
		row.createCell(4).setCellValue(helper.createRichTextString("ACTUAL_VALUE"));
		row.getCell(4).setCellStyle(style);
		
		inputSummaryDetails();
	}
	
	
	public void inputSummaryDetails()
	{
		summarysheet = wb.createSheet("SummarySheet");
		summarysheet.setDefaultColumnWidth(18);
		CellStyle summaryStyle = wb.createCellStyle();
		Font font = wb.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		summaryStyle.setFont(font);
		summaryStyle.setAlignment(HorizontalAlignment.CENTER);
		summaryStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		summaryStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		summaryStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		helper = wb.getCreationHelper();
		
		SXSSFRow summaryRow = sheet.createRow(1);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Test Case ID"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString((String) Basepage.testinputs.get(Thread.currentThread().getId()).get("TestID")));
		
		summaryRow = sheet.createRow(2);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Status"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		
		summaryRow = sheet.createRow(3);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Execution Date"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString(new SimpleDateFormat("dd-MMM-YY HH:mm:ss").format(Calendar.getInstance().getTime()).toString()));
		
		summaryRow = sheet.createRow(4);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Feature File"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString((String) Basepage.testinputs.get(Thread.currentThread().getId()).get("FeatureName")));
		
		summaryRow = sheet.createRow(5);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Feature file Tag"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString((String) Basepage.testinputs.get(Thread.currentThread().getId()).get("Tags")));
		
		summaryRow = sheet.createRow(6);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Tester"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString(System.getProperty("user.name")));
		
		summaryRow = sheet.createRow(7);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Status"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString(System.getenv("COMPUTERNAME")));
		
		summaryRow = sheet.createRow(8);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Browser"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		
		summaryRow = sheet.createRow(9);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Total Steps"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		
		summaryRow = sheet.createRow(10);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Passed Steps"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		
		summaryRow = sheet.createRow(11);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Failed Steps"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		
		summaryRow = sheet.createRow(12);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Info Steps"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		
		summaryRow = sheet.createRow(13);
		summaryRow.createCell(1).setCellValue(helper.createRichTextString("Warn Steps"));
		summaryRow.getCell(1).setCellStyle(summaryStyle);
		
	}
	
	public void updateStepCounters(String Status)
	{
		switch(Status.toUpperCase().trim())
		{
		case "PASSED":
		case "PASS":
			PassedSteps++;
			TotalSteps++;
			break;
		case "FAILED":
		case "FAIL":
			FailedSteps++;
			TotalSteps++;
			break;
		case "INFO":
		case "LOG":
			InfoSteps++;
			TotalSteps++;
			break;
		case "WARNING":
		case "WARN":
			WarnSteps++;
			TotalSteps++;
			break;
		default:
			TotalSteps++;
			break;
		}
		
	}
	
	public void closeExcel() throws Throwable
	{
		SXSSFRow summaryRow = summarysheet.getRow(8);
		summaryRow.createCell(2).setCellValue("Chrome");
		
		summaryRow = summarysheet.getRow(9);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString(Integer.toString(TotalSteps)));
		
		summaryRow = summarysheet.getRow(10);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString(Integer.toString(PassedSteps)));
		
		summaryRow = summarysheet.getRow(11);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString(Integer.toString(FailedSteps)));
		
		summaryRow = summarysheet.getRow(12);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString(Integer.toString(InfoSteps)));
		
		summaryRow = summarysheet.getRow(13);
		summaryRow.createCell(2).setCellValue(helper.createRichTextString(Integer.toString(WarnSteps)));
		
		CellStyle summaryStyle = wb.createCellStyle();
		Font font = wb.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		summaryStyle.setFont(font);
		summaryStyle.setAlignment(HorizontalAlignment.CENTER);
		summaryStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		summaryRow = summarysheet.getRow(2);
		
		if(FailedSteps > 0)
		{
			summaryStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			summaryStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			summaryRow.createCell(2).setCellValue(helper.createRichTextString("FAILED"));
			summaryRow.getCell(2).setCellStyle(summaryStyle);
		}
		else
		{
			summaryStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			summaryStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			summaryRow.createCell(2).setCellValue(helper.createRichTextString("PASSED"));
			summaryRow.getCell(2).setCellStyle(summaryStyle);
		}
		
		fileOut = new FileOutputStream(strFileName);
		wb.write(fileOut);
		wb.close();
		fileOut.close();
		row1 = 2;
		row2 = 27;
		stepcount = 1;
		PassedSteps = 0;
		FailedSteps = 0;
		InfoSteps = 0;
		WarnSteps = 0;
		TotalSteps = 0;
	}
	
}
