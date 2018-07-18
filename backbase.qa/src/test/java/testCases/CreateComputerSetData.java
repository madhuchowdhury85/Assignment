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

public class CreateComputerSetData extends BaseTest {
	WebDriver driver;
	HomePage homeP;
	AddComputerPage addComP;

	@BeforeClass
	public void openApplication() {
		// Initiate browser
		driver = BaseTest.startBrowser();

		// Navigate to Home Page
		homeP = new HomePage();

		// Verify Home Page loaded
		Assert.assertTrue(homeP.isPageLoaded(), "User is not successfully landed to Home Page");
	}

	@AfterClass
	public void tearDown() {
		// Close the browser
		driver.quit();

	}

	@DataProvider(name = "createcomputersetofdata")
	public Object[][] getLoginData1() {
		
		// Number of rows in test data excel
		int rowcount = DataProviderFactory.getanotherExcel().getNumberOfRows("Computer");

		// Create data of 2d object
		Object[][] data = new Object[rowcount - 1][4];

		for (int i = 0; i < rowcount - 1; i++) {
			// Fetch the data from excel and keep it in 2d data
			data[i][0] = DataProviderFactory.getanotherExcel().getstringdata("Computer", i + 1, 0);
			data[i][1] = DataProviderFactory.getanotherExcel().getDate("Computer", i + 1, 1);
			data[i][2] = DataProviderFactory.getanotherExcel().getDate("Computer", i + 1, 2);
			data[i][3] = DataProviderFactory.getanotherExcel().getstringdata("Computer", i + 1, 3);
		}

		// Return data
		return data;
	}

	@Test(dataProvider = "createcomputersetofdata")
	public void computerCreateSetofData(String cName, String iDate, String dDate, String company) {

		// Start a report with test case name and create a logger
		logger = report.startTest("Create Computer with multiple set of data");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// It will fetch data from data provider and fill in Add computer
		addComP.fillDataComputer(cName, iDate, dDate, company);

		// Hit create computer button, it will save data and will return to Home page
		homeP = addComP.createComputer();

		// Added info in logger
		logger.log(LogStatus.INFO, "Data entered in add computer page and saved");

		// Verify the success message in Home Page
		boolean isMsgFound = homeP.presenceOfMsg();

		if (isMsgFound) {

			logger.log(LogStatus.PASS, "New computer created with name as " + cName);
		}

		else {
			logger.log(LogStatus.FAIL, "New computer not created with name as " + cName);
		}

		// End of logger
		report.endTest(logger);

	}
}
