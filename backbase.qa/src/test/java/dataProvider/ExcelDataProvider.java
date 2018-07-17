package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider 
{

	XSSFWorkbook wb;
    public  String path;
   
	//Constructor to load test data file
	public ExcelDataProvider(String path)
	{
		this.path=path;
		try 
		{
			wb= new XSSFWorkbook(new FileInputStream(new File(path)));
					
		} 
		catch (Exception e) 
		{
			System.out.println("File not found or not loaded properly "+e.getMessage());
		}
		 
	}
	
	//Method to get stringdata from excel
	public String getstringdata(String sheetName, int row, int column )
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}
	
	//Method to get numeric data from excel
	public int getnumericintdata(String sheetName, int row, int column )
	{
		return (int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}
	
	//Method to get date from excel
	public String getDate(String sheetName, int row, int column )
	{
		SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
		return date.format(wb.getSheet(sheetName).getRow(row).getCell(column).getDateCellValue());
	}
	
	//Method to get number of rows from excel
	public int getNumberOfRows(String SheetName)
	{
		return wb.getSheet(SheetName).getPhysicalNumberOfRows();
	}
}
