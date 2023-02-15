package Testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

public class WordReport {

	FileOutputStream foWord;
	XWPFDocument document;
	
	public String FilePath;
	public int stepcount = 1;
	public XWPFTable SummaryTable;
	public int PassedSteps =0, FailedSteps=0, TotalSteps=0;
	
	public void writeText (String line) throws Throwable
	{
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addBreak();
		run.setText(line);
	}
	
	public void insertImage(String imgFile) throws Throwable
	{
		FileInputStream is = new FileInputStream(imgFile);
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addBreak();
		run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(520), Units.toEMU(260));
		run.addBreak();
		paragraph.setPageBreak(true);
		is.close();
	}
	
	public void report(String StepName, String Expected, String Actual,  String Status) throws Throwable
	{
		Status = Status.toUpperCase();
		if(Expected!="")
		{
			insertTable( new String [][] {{"Step No", "Step Name", "Expected", "Actual", "Step Status"}, {Integer.toString(stepcount), StepName, Expected,Actual,Status}});
			
		}
		else
		{
			insertTable( new String [][] {{"Step No", "Step Name", "Step Status"}, {Integer.toString(stepcount), StepName,Status}});
			updateStepCounters(Status);
			stepcount++;
		}
	}
		
		public XWPFTable insertTable(String[][] list)
		{
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.addBreak();
			XWPFTable table = document.createTable(list.length, list[0].length);
			table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
			for (int i = 0 ;i < list.length; i++ )
			{
				XWPFTableRow tableRow;
				tableRow = table.getRow(i);
				XWPFTableCell tableCell;
				for (int j = 0; j< list[i].length;j++)
				{
					tableCell = tableRow.getCell(j);
					XWPFParagraph cellPara = tableCell.addParagraph();
					tableCell.removeParagraph(0);
					setSingleLineSpacing(cellPara);
					XWPFRun cellrun = cellPara.createRun();
					cellrun.setText(list[i][j]);
					tableCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
				}
			}
				
			run.addBreak();
			return table;
		}
		
		public void close() throws Throwable
		{
			try
			{
				SummaryTable.getRow(6).getCell(1).setText(System.getenv("COMPUTERNAME"));
				SummaryTable.getRow(7).getCell(1).setText("Chrome");
				SummaryTable.getRow(8).getCell(1).setText(Integer.toString(TotalSteps));
				SummaryTable.getRow(9).getCell(1).setText(Integer.toString(PassedSteps));
				SummaryTable.getRow(10).getCell(1).setText(Integer.toString(FailedSteps));
				if(FailedSteps > 0)
				{
					SummaryTable.getRow(1).getCell(1).setText("FAILED");
					SummaryTable.getRow(1).getCell(1).setText("FF0000");
				}
				else
				{
					SummaryTable.getRow(1).getCell(1).setText("PASSED");
					SummaryTable.getRow(1).getCell(1).setText("008000");
				}
				document.write(foWord);
				if(document!=null)
					document.close();
				if(foWord!=null)
					foWord.close();
				FilePath = null;
				PassedSteps=0;
				FailedSteps=0;
				TotalSteps=0;
			}
			catch (Exception e)
			{
				
			}
		}
		
		public void open(String folder, String TestID)
		
		{
			try
			{
				FilePath = folder + "\\" + TestID + "_" + new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime()).toString() + ".docx";
				foWord = new FileOutputStream(new File(FilePath));
				document = new XWPFDocument();
				insertSummaryTable();
				stepcount=1;
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		
	
		public void insertSummaryTable()
		{
			String [][] SummaryArray = new String [11][2];
			SummaryArray[0][0] = "Test Case ID";
			SummaryArray[0][1] = (String) Basepage.testinputs.get(Thread.currentThread().getId()).get("TestID");
			SummaryArray[1][0] = "Status";
			SummaryArray[2][0] = "Execution Date";
			SummaryArray[2][1] = new SimpleDateFormat("dd-MMM-YY HH:mm:ss").format(Calendar.getInstance().getTime()).toString();
			SummaryArray[3][0] = "Feature File";
			SummaryArray[3][1] = (String) Basepage.testinputs.get(Thread.currentThread().getId()).get("FeatureName");
			SummaryArray[4][0] = "Feature file Tag";
			SummaryArray[4][1] = (String) Basepage.testinputs.get(Thread.currentThread().getId()).get("Tags");
			SummaryArray[5][0] = "Tester";
			SummaryArray[5][1] = System.getProperty("user.name");
			SummaryArray[6][0] = "Host Name";
			SummaryArray[7][0] = "Browser";
			SummaryArray[8][0] = "Total Steps";
			SummaryArray[9][0] = "Passed Steps";
			SummaryArray[10][0] = "Failed Steps";
		
			SummaryTable = insertTable(SummaryArray);
		}
		
		public void updateStepCounters(String Status)
		{
			switch(Status.toUpperCase().trim())
			{
			case "PASSED":
				PassedSteps++;
				TotalSteps++;
				break;
			case "FAILED":
				FailedSteps++;
				TotalSteps++;
				break;
			case "INFO":
				TotalSteps++;
				break;
			case "WARNING":
				TotalSteps++;
				break;
			default:
				TotalSteps++;
				break;
			}
		}
		
		
		public void setSingleLineSpacing(XWPFParagraph para)
		{
			para.setFirstLineIndent(350);
			CTPPr ppr = para.getCTP().getPPr();
			if (ppr == null)		
			ppr = para.getCTP().addNewPPr();
			CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
			spacing.setAfter(BigInteger.valueOf(0));
			spacing.setBefore(BigInteger.valueOf(0));
			spacing.setLineRule(STLineSpacingRule.AUTO);
			spacing.setLine(BigInteger.valueOf(240));
			
		}
		
		
	}


