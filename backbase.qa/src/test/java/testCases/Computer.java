package testCases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class Computer extends BaseTest {

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
	public void addComputer() {

		// Start a report with test case name and create a logger
		logger = report.startTest("Add Computer");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// Create a computer name string
		String comName = "Computer" + Utility.generateRandomString(4);

		// Fetch the data from test data excel and fill data in Add computer
		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));

		logger.log(LogStatus.INFO, "Data entered in add computer page and saved");

		// Hit create computer button, it will save data and will return to Home page
		homeP = addComP.createComputer();

		logger.log(LogStatus.INFO, "Entered data saved");

		// Verify the success message in Home Page
		boolean isMsgFound = homeP.presenceOfMsg();

		if (isMsgFound) {

			// Actual success message text in application
			String actualSuccessMsgText = homeP.successMsgText();

			// Expected success message text as per requirement
			String expectedSuccessMsgText = "Done! Computer " + comName + " has been created";

			// Verify the actual and expected
			assertEquals(actualSuccessMsgText, expectedSuccessMsgText);

			logger.log(LogStatus.PASS, "New computer created with name as " + comName);
		}

		else {
			logger.log(LogStatus.FAIL, "New computer not created with name as " + comName);
		}

		// End of logger
		report.endTest(logger);

	}

	@Test
	public void createandsearchComputerbyName() {

		// Start a report with test case name and create a logger
		logger = report.startTest("Create and Search Computer");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// Create a computer name string
		String comName = "Computer" + Utility.generateRandomString(4);

		// Fetch the data from test data excel and fill data in Add computer
		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));

		logger.log(LogStatus.INFO, "Data entered in add computer page");

		// Hit create computer button, it will save data and will return to Home page
		homeP = addComP.createComputer();

		logger.log(LogStatus.INFO, "Entered data saved");

		// Verify the search result page if the computer is present
		boolean isComFound = homeP.searchComputerByName(comName);

		if (isComFound) {

			logger.log(LogStatus.PASS, "Successfully Computer found name as " + comName);

		} else {
			logger.log(LogStatus.FAIL, "Computer not found name as " + comName);
		}

		// End of logger
		report.endTest(logger);

	}

	@Test
	public void createandeditComputerName() {

		// Start a report with test case name and create a logger
		logger = report.startTest("Create and Edit Computer Name");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// Create a computer name string
		String comName = "Computer" + Utility.generateRandomString(4);

		// Fetch the data from test data excel and fill data in Add computer
		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));

		logger.log(LogStatus.INFO, "Data entered in add computer page");

		// Hit create computer button, it will save data and will return to Home page
		homeP = addComP.createComputer();

		logger.log(LogStatus.INFO, "Entered data saved");

		// Navigate to Edit Computer Page
		editP = homeP.navigateToeditComputerPage(comName);

		// Create a new computer name string
		String comNewName = "NewComputer" + Utility.generateRandomString(2);

		// Edit computer name with the new computer name
		editP.editComputerName(comNewName);

		// Hit save this computer button, it will save data and will return to Home page
		homeP = editP.saveComputer();

		logger.log(LogStatus.INFO, "Computer name edited and saved");

		// Verify the success message in Home Page
		boolean isMsgFound = homeP.presenceOfMsg();

		if (isMsgFound) {
			// Actual success message text in application
			String actualSuccessMsgText = homeP.successMsgText();

			// Expected success message text as per requirement
			String expectedSuccessMsgText = "Done! Computer " + comNewName + " has been updated";

			// Verify the actual and expected
			assertEquals(actualSuccessMsgText, expectedSuccessMsgText);

			logger.log(LogStatus.PASS, "Computer name as " + comName + " updated to " + comNewName);

		}

		else {
			logger.log(LogStatus.FAIL, "Computer name not updated");

		}

		// End of logger
		report.endTest(logger);

	}

	@Test
	public void createandeditComputerCompany() {

		// Start a report with test case name and create a logger
		logger = report.startTest("Create and Edit Computer CompanyName");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// Create a computer name string
		String comName = "Computer" + Utility.generateRandomString(4);

		// Fetch the data from test data excel and fill data in Add computer
		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));

		logger.log(LogStatus.INFO, "Data entered in add computer page");

		// Hit create computer button, it will save data and will return to Home page
		homeP = addComP.createComputer();

		logger.log(LogStatus.INFO, "Entered data saved");

		// Navigate to Edit Computer Page
		editP = homeP.navigateToeditComputerPage(comName);

		// Fetch the company name from test data excel and fill data in edit computer
		editP.editComputercompany(DataProviderFactory.getExcel().getstringdata("Computer", 2, 2));

		// Hit save this computer button, it will save data and will return to Home page
		homeP = editP.saveComputer();

		logger.log(LogStatus.INFO, "Company name for computer edited and saved");

		// Filter with computer name and verify the search result
		homeP.searchComputer(comName);

		// List of webelements
		List<WebElement> compRows = driver
				.findElements(By.xpath("//table[@class='computers zebra-striped']//tbody//tr[last()]"));

		for (WebElement allRows : compRows) {

			// Get the company name from the search result page
			String newCompany = allRows.findElement(By.xpath("//td[4]")).getText();

			// Verify the company name in application with the name entered from excel
			if (newCompany.equals(DataProviderFactory.getExcel().getstringdata("Computer", 2, 2))) {
				logger.log(LogStatus.PASS, "Computer name as " + comName + " updated companyname");
			} else {

				logger.log(LogStatus.FAIL, "Computer name as " + comName + " not updated companyname");
			}
		}

		// End of logger
		report.endTest(logger);

	}

	@Test
	public void createandeditComputerintroDate() {

		// Start a report with test case name and create a logger
		logger = report.startTest("Create and Edit Introduced Date");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// Create a computer name string
		String comName = "Computer" + Utility.generateRandomString(4);

		// Fetch the data from test data excel and fill data in Add computer
		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));

		logger.log(LogStatus.INFO, "Data entered in add computer page");

		// Hit create computer button, it will save data and will return to Home page
		homeP = addComP.createComputer();

		logger.log(LogStatus.INFO, "Entered data saved");

		// Navigate to Edit Computer Page
		editP = homeP.navigateToeditComputerPage(comName);

		// Fetch the introduced date from test data excel and fill data in edit computer
		editP.editComputerintroDate(DataProviderFactory.getExcel().getDate("Computer", 2, 0));

		// Hit save this computer button, it will save data and will return to Home page
		homeP = editP.saveComputer();

		logger.log(LogStatus.INFO, "Introduced date for computer edited and saved");

		// Filter with computer name and verify the search result
		homeP.searchComputer(comName);

		// List of webelements
		List<WebElement> compRows = driver
				.findElements(By.xpath("//table[@class='computers zebra-striped']//tbody//tr[last()]"));

		for (WebElement allRows : compRows) {
			// Get the introduced date from the search result page
			String actualIntroDate = allRows.findElement(By.xpath("//td[2]")).getText();

			// Introduced date that is fetched from excel
			String introDate = String.valueOf(DataProviderFactory.getExcel().getDate("Computer", 2, 0));

			String expectedIntroDate = Utility.dateFormat(introDate);

			// Verify the actual and expected
			Assert.assertTrue(actualIntroDate.contains(expectedIntroDate),
					"Computer name as " + comName + " not updated introdate");

			logger.log(LogStatus.PASS, "Computer name as " + comName + " updated introdate successfully");

		}

		// End of logger
		report.endTest(logger);

	}

	@Test
	public void createandeditComputerdiscontinueDate() {

		// Start a report with test case name and create a logger
		logger = report.startTest("Create and Edit Discontinued Date");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// Create a computer name string
		String comName = "Computer" + Utility.generateRandomString(4);

		// Fetch the data from test data excel and fill data in Add computer
		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 5, 0),
				DataProviderFactory.getExcel().getDate("Computer", 5, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 5, 2));

		logger.log(LogStatus.INFO, "Data entered in add computer page");

		// Hit create computer button, it will save data and will return to Home page
		homeP = addComP.createComputer();

		logger.log(LogStatus.INFO, "Entered data saved");

		// Navigate to Edit Computer Page
		editP = homeP.navigateToeditComputerPage(comName);

		// Fetch the discontinue date from test data excel and fill data in edit
		// computer
		editP.editComputerdisconDate(DataProviderFactory.getExcel().getDate("Computer", 7, 0));

		// Hit save this computer button, it will save data and will return to Home page
		homeP = editP.saveComputer();

		logger.log(LogStatus.INFO, "Discontinued date for computer edited and saved");

		// Filter with computer name and verify the search result
		homeP.searchComputer(comName);

		// List of webelements
		List<WebElement> compRows = driver
				.findElements(By.xpath("//table[@class='computers zebra-striped']//tbody//tr[last()]"));

		for (WebElement allRows : compRows) {

			// Get the discontinue date from the search result page
			String actualdisconDate = allRows.findElement(By.xpath("//td[3]")).getText();

			// discontinue date that is fetched from excel
			String disconDate = String.valueOf(DataProviderFactory.getExcel().getDate("Computer", 7, 0));

			String expecteddisconDate = Utility.dateFormat(disconDate);

			// Verify the actual and expected
			Assert.assertTrue(actualdisconDate.contains(expecteddisconDate),
					"Computer name as " + comName + " not updated discontinue date");

			logger.log(LogStatus.PASS, "Computer name as " + comName + " updated discontinue date successfully");

		}

		// End of logger
		report.endTest(logger);

	}

	@Test
	public void createandeleteComputer() {

		// Start a report with test case name and create a logger
		logger = report.startTest("Create and Delete Computer");

		// Navigate to Add Computer Page
		addComP = homeP.navigateToAddComputerPage();

		// Create a computer name string
		String comName = "Computer" + Utility.generateRandomString(4);

		// Fetch the data from test data excel and fill data in Add computer
		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));

		logger.log(LogStatus.INFO, "Data entered in add computer page");

		// Hit create computer button, it will save data and will return to Home page
		homeP = addComP.createComputer();

		logger.log(LogStatus.INFO, "Entered data saved");

		// Navigate to Edit Computer Page
		editP = homeP.navigateToeditComputerPage(comName);

		// Click on delete button and it will navigate to Home Page
		homeP = editP.deleteComputer(comName);

		logger.log(LogStatus.INFO, "Computer deleted using delete button");

		// Verify the success message in Home Page
		boolean isMsgFound = homeP.presenceOfMsg();

		if (isMsgFound) {

			// Actual success message text in application
			String actualSuccessMsgText = homeP.successMsgText();

			// Expected success message text as per requirement
			String expectedSuccessMsgText = "Done! Computer has been deleted";

			// Verify the actual and expected
			assertEquals(actualSuccessMsgText, expectedSuccessMsgText);

			logger.log(LogStatus.PASS, "New computer created with name as " + comName + "has been deleted");

		}

		else {
			logger.log(LogStatus.FAIL, "New computer created with name as " + comName + "has not been deleted");
		}

		// End of logger
		report.endTest(logger);
	}

}
