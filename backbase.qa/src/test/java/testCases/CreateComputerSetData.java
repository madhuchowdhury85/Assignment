package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import factory.DataProviderFactory;
import pageObjects.AddComputerPage;
import pageObjects.HomePage;

public class CreateComputerSetData extends BaseTest
{
	WebDriver driver;
	HomePage homeP;
	AddComputerPage addComP;
	
	@BeforeClass
	public void openApplication()
	{
		driver= BaseTest.startBrowser();
		homeP = new HomePage();
		Assert.assertTrue(homeP.isPageLoaded(), "User is not successfully logged in");
	}
	
	@AfterClass
	public void tearDown()
	{
		
		driver.quit();
		
	}
		
	@DataProvider (name = "createcomputersetofdata")
	public Object[][] getLoginData1() 
	{
		int rowcount=DataProviderFactory.getanotherExcel().getNumberOfRows("Computer");
		
		Object[][] data = new Object[rowcount-1][4]; 
				
		for(int i=0;i<rowcount-1;i++)
		{
			data[i][0]=DataProviderFactory.getanotherExcel().getstringdata("Computer", i+1, 0);
			data[i][1]=DataProviderFactory.getanotherExcel().getDate("Computer", i+1, 1);
			data[i][2]= DataProviderFactory.getanotherExcel().getDate("Computer", i+1, 2);
			data[i][3]=DataProviderFactory.getanotherExcel().getstringdata("Computer", i+1, 3);
		}
		
		return data;
	}

	
	@Test(dataProvider = "createcomputersetofdata")
	public void computerCreateSetofData(String cName, String iDate, String dDate, String company  )
	{
    		
		logger = report.startTest("Create Computer with multiple set of data");
				
		addComP=homeP.navigateToAddComputerPage();
		
		addComP.fillDataComputer(cName, iDate, dDate, company);
				
		homeP= addComP.createComputer();
		
		logger.log(LogStatus.INFO, "Data entered in add computer page and saved");
		
		boolean isMsgFound= homeP.presenceOfMsg();
		
		if(isMsgFound)
		{
					
			logger.log(LogStatus.PASS, "New computer created with name as " + cName);
		}
		
		else
		{
			logger.log(LogStatus.FAIL, "New computer not created with name as " + cName);
		}
		
		report.endTest(logger);	
				
		}
}
