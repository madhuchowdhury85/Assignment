package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import base.BaseTest;
import factory.DataProviderFactory;
import library.Utility;
import pageObjects.AddComputerPage;
import pageObjects.EditComputerPage;
import pageObjects.HomePage;

public class Computer_NegativeTC extends BaseTest {

	WebDriver driver;
	HomePage homeP;
	AddComputerPage addComP;
	EditComputerPage editP;

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

	@Test
	public void createComputerwithnoName() {

		// Start a report with test case name and create a logger
		logger = report.startTest("Try to Create Computer with no Computer Name");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// Get original colour of computer Name label
		String orgComNamecolor = (String) addComP.computerNameLabelColor();

		String comName = "";

		// Fetch the data from test data excel and fill data in Add computer except
		// computer name
		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));

		logger.log(LogStatus.INFO, "Data entered in add computer page except computer name");

		// Hit create computer button, it will try to save data
		homeP = addComP.createComputer();

		// Halt program for 1 second
		Utility.sleep(1);

		// Get change colour of computer Name label
		String changeComNamecolor = (String) addComP.computerNameLabelColor();

		// Verify original and change color of computer name label
		if (!orgComNamecolor.equals(changeComNamecolor)) {
			// Click cancel
			addComP.cancelComputer();

			logger.log(LogStatus.PASS, "Computer not created without computername");
		}

		else {

			logger.log(LogStatus.FAIL, "New computer created with no computer name");
		}

		// End of logger
		report.endTest(logger);

	}

	@Test
	public void creatComputerwithInvalidIntroDate() {

		// Start a report with test case name and create a logger
		logger = report.startTest("Try to Create Computer with invalid introduced date");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// Create a computer name string
		String comName = "Computer" + Utility.generateRandomString(4);

		// Create a introduced date string
		String introDate = "2019/09/10";

		// Get original colour of introduced date label
		String orgIntroDatecolor = (String) addComP.introDateLabelColor();

		// Fetch the data from test data excel and fill data in Add computer with
		// invalid introduced date
		addComP.fillDataComputer(comName, introDate, DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));

		logger.log(LogStatus.INFO, "Data entered in add computer page with invalid introduced date");

		// Hit create computer button, it will try to save data
		homeP = addComP.createComputer();

		// Halt program for 1 second
		Utility.sleep(1);

		// Get change colour of introduced date label
		String changeIntroDatecolor = (String) addComP.introDateLabelColor();

		// Verify original and change color of introduced date label
		if (!orgIntroDatecolor.equals(changeIntroDatecolor)) {
			// Click cancel
			addComP.cancelComputer();
			logger.log(LogStatus.PASS, "Computer not created with invalid introduced date");
		}

		else {

			logger.log(LogStatus.FAIL, "New computer created with invalid introduced date");
		}

		// End of logger
		report.endTest(logger);

	}

	@Test
	public void creatComputerwithInvalidDiscontinueDate() {

		// Start a report with test case name and create a logger
		logger = report.startTest("Try to Create Computer with invalid discontinued date");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// Create a computer name string
		String comName = "Computer" + Utility.generateRandomString(4);

		// Create a discontinue date string
		String discontinueDate = "02-15-1985";

		// Get original colour of discontinue date label
		String orgDisColor = (String) addComP.disconDateLabelColor();

		// Fetch the data from test data excel and fill data in Add computer with
		// invalid discontinue date
		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0), discontinueDate,
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));

		logger.log(LogStatus.INFO, "Data entered in add computer page with invalid Discontinued date");

		// Hit create computer button, it will try to save data
		homeP = addComP.createComputer();

		// Halt program for 1 second
		Utility.sleep(1);

		// Get change colour of discontinue date label
		String changeDisColor = (String) addComP.disconDateLabelColor();

		// Verify original and change color of discontinue date label
		if (!orgDisColor.equals(changeDisColor)) {
			// Click cancel
			addComP.cancelComputer();
			logger.log(LogStatus.PASS, "Computer not created with invalid Discontinued date");
		}

		else {

			logger.log(LogStatus.FAIL, "New computer created with invalid Discontinued date");
		}

		// End of logger
		report.endTest(logger);

	}

}
