package factory;

import dataProvider.ExcelDataProvider;

public class DataProviderFactory 
{

	// Method to create object of ExcelDataProvider class
	public static ExcelDataProvider getExcel()
	{
		ExcelDataProvider excel= new ExcelDataProvider("./TestData/BBTestData.xlsx");
		return excel;
	}
	
	// Method to create another object of ExcelDataProvider class
	public static ExcelDataProvider getanotherExcel()
	{
		ExcelDataProvider excel= new ExcelDataProvider("./TestData/BBTestDataProvider.xlsx");
		return excel;
	}
}
