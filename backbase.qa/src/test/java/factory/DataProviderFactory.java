package factory;

import dataProvider.ExcelDataProvider;

public class DataProviderFactory {

	// Method to create object of ExcelDataProvider class and will return excel
	public static ExcelDataProvider getExcel() {
		
		// Create an object of ExcelDataProvider class
		ExcelDataProvider excel = new ExcelDataProvider("./TestData/BBTestData.xlsx");
		return excel;
	}

	// Method to create another object of ExcelDataProvider class and will return
	// excel
	public static ExcelDataProvider getanotherExcel() {
		
		// Create an object of ExcelDataProvider class
		ExcelDataProvider excel = new ExcelDataProvider("./TestData/BBTestDataProvider.xlsx");
		return excel;
	}
}
