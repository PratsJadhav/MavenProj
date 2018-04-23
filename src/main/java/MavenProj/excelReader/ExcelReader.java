package MavenProj.excelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public FileOutputStream fileOut = null;
	public String path;
	public FileInputStream fileIn;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	public ExcelReader(String path) throws IOException
	{
		this.path = path;
		
		try
		{
		fileIn = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileIn);
	    }
		catch(Exception e)
		{
		 e.printStackTrace();  
		}
	}
	
	public String[][] getDataFromSheet(String sheetName, String excelName)
	{
		String dataSets[][] = null;
		
			
		//get sheet from excel workbook
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum()+1;
		System.out.println(""+totalRows);
		int totalColumns = sheet.getRow(0).getLastCellNum();
		System.out.println(""+totalColumns);
		
		
		dataSets = new String[totalRows - 1][totalColumns];
		
		//for loop to run on rows
		for(int i = 1; i < totalRows; i++)
		{
			XSSFRow rows  = sheet.getRow(i);
			
			//for loop to run on the columns of selected row
			for(int j = 0; j < totalColumns ; j++)
			{
				XSSFCell cell = rows.getCell(j);
				
				if(cell.getCellType() == Cell.CELL_TYPE_STRING)
				{
					dataSets[i-1][j] = cell.getStringCellValue();
				}
				else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
				{
				   String cellText = String.valueOf(cell.getNumericCellValue());
				   dataSets[i-1][j] = cellText;
				}
				else
				{
					dataSets[i-1][j] = String.valueOf(cell.getBooleanCellValue());
				}
				
			}
		
		}
		
		return dataSets;
	}
	
	
	public String getCellData(String sheetName, String colName, int rowNum)
	{
		
		int col_Name = 0;
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		XSSFRow row = sheet.getRow(0);
		
		for(int i = 0; i < row.getLastCellNum(); i++)
		{
			if(row.getCell(i).getStringCellValue().equals(colName))
			{
				col_Name = i;
				break;
			}
			
		}
		
		row = sheet.getRow(rowNum - 1);
		
		XSSFCell cell = row.getCell(col_Name);
		if(cell.getCellType()==Cell.CELL_TYPE_STRING)
		{
			return cell.getStringCellValue();
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
		{
			return "";
		}
		
		return null;
	}
	
}
