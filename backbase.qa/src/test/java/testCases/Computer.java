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
		driver = BaseTest.startBrowser();
		homeP = new HomePage();
		Assert.assertTrue(homeP.isPageLoaded(), "User is not successfully logged in");
	}

	@AfterClass
	public void tearDown() {

		driver.quit();

	}

	@Test
	public void addComputer() {
		
		logger = report.startTest("Add Computer");    
		
		addComP = homeP.navigateToAddComputerPage();
		String comName = "Computer" + Utility.generateRandomString(4);

		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));
				
		logger.log(LogStatus.INFO, "Data entered in add computer page and saved");

		homeP = addComP.createComputer();
		
		logger.log(LogStatus.INFO, "Entered data saved");

		boolean isMsgFound = homeP.presenceOfMsg();

		if (isMsgFound) {
			String actualSuccessMsgText = homeP.successMsgText();
			String expectedSuccessMsgText = "Done! Computer " + comName + " has been created";
			assertEquals(actualSuccessMsgText, expectedSuccessMsgText);

			logger.log(LogStatus.PASS, "New computer created with name as " + comName);
		}

		else {
			logger.log(LogStatus.FAIL, "New computer not created with name as " + comName);
		}
		
		report.endTest(logger);

	}

	@Test
	public void cancelCreateComputer() {
		
		logger = report.startTest("Cancel Create Computer");
		
		addComP = homeP.navigateToAddComputerPage();
		String comName = "Computer" + Utility.generateRandomString(4);

		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));
		
		logger.log(LogStatus.INFO, "Data entered in add computer page");

		homeP = addComP.cancelComputer();
		
		logger.log(LogStatus.INFO, "Data not saved and canceled");

		boolean isMsgFound = homeP.presenceOfMsg();

		if (isMsgFound) {
			String actualSuccessMsgText = homeP.successMsgText();

			System.out.println(actualSuccessMsgText);

			String expectedSuccessMsgText = "Done! Computer " + comName + " has been created";
			assertEquals(actualSuccessMsgText, expectedSuccessMsgText);

			logger.log(LogStatus.FAIL, "New computer created with name as " + comName);
		}

		else {
			logger.log(LogStatus.PASS, "New computer not created with name as " + comName);
		}
		report.endTest(logger);

	}

	@Test
	public void createandsearchComputerbyName() {
		
		logger = report.startTest("Create and Search Computer");
		
		
		addComP = homeP.navigateToAddComputerPage();
		String comName = "Computer" + Utility.generateRandomString(4);

		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));
		
		logger.log(LogStatus.INFO, "Data entered in add computer page");

		homeP = addComP.createComputer();
		logger.log(LogStatus.INFO, "Entered data saved");
		
		boolean isComFound = homeP.searchComputerByName(comName);
		if (isComFound) {
			
			logger.log(LogStatus.PASS, "Successfully Computer found name as " + comName);
			
		} else {
			logger.log(LogStatus.FAIL, "Computer not found name as " + comName);
		}
		report.endTest(logger);

	}

	@Test
	public void createandeditComputerName() {
		
		logger = report.startTest("Create and Edit Computer Name");
		
		addComP = homeP.navigateToAddComputerPage();
		String comName = "Computer" + Utility.generateRandomString(4);

		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));
		
		logger.log(LogStatus.INFO, "Data entered in add computer page");

		homeP = addComP.createComputer();
		
		logger.log(LogStatus.INFO, "Entered data saved");

		EditComputerPage editP = homeP.navigateToeditComputerPage(comName);

		String comNewName = "NewComputer" + Utility.generateRandomString(2);
		editP.editComputerName(comNewName);
		
		homeP = editP.saveComputer();
		
		logger.log(LogStatus.INFO, "Computer name edited and saved");

		boolean isMsgFound = homeP.presenceOfMsg();

		if (isMsgFound) {
			String actualSuccessMsgText = homeP.successMsgText();
			String expectedSuccessMsgText = "Done! Computer " + comNewName + " has been updated";
			assertEquals(actualSuccessMsgText, expectedSuccessMsgText);
			
			logger.log(LogStatus.PASS, "Computer name as " + comName + " updated to " + comNewName);

			}

		else {
			logger.log(LogStatus.FAIL, "Computer name not updated");
			
		}
		report.endTest(logger);

	}

	@Test
	public void createandeditComputerCompany() {
		
		logger = report.startTest("Create and Edit Computer CompanyName");
		
		
		addComP = homeP.navigateToAddComputerPage();
		String comName = "Computer" + Utility.generateRandomString(4);

		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));
		
		logger.log(LogStatus.INFO, "Data entered in add computer page");		

		homeP = addComP.createComputer();
		
		logger.log(LogStatus.INFO, "Entered data saved");

		EditComputerPage editP = homeP.navigateToeditComputerPage(comName);

		editP.editComputercompany(DataProviderFactory.getExcel().getstringdata("Computer", 2, 2));

		homeP = editP.saveComputer();
		
		logger.log(LogStatus.INFO, "Company name for computer edited and saved");

		homeP.searchComputer(comName);

		List<WebElement> compRows = driver
				.findElements(By.xpath("//table[@class='computers zebra-striped']//tbody//tr[last()]"));
		for (WebElement allRows : compRows) {
			String newCompany = allRows.findElement(By.xpath("//td[4]")).getText();
			
			if (newCompany.equals(DataProviderFactory.getExcel().getstringdata("Computer", 2, 2))) 
			{
				logger.log(LogStatus.PASS, "Computer name as " + comName + " updated companyname");
			} else {
				
				logger.log(LogStatus.FAIL, "Computer name as " + comName + " not updated companyname");
			}
		}
		report.endTest(logger);

	}



	@Test
	public void createandeditComputerintroDate() {
		
		logger = report.startTest("Create and Edit Introduced Date");
		
		addComP = homeP.navigateToAddComputerPage();
		String comName = "Computer" + Utility.generateRandomString(4);

		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));
		
		logger.log(LogStatus.INFO, "Data entered in add computer page");

		homeP = addComP.createComputer();
		
		logger.log(LogStatus.INFO, "Entered data saved");

		EditComputerPage editP = homeP.navigateToeditComputerPage(comName);

		editP.editComputerintroDate(DataProviderFactory.getExcel().getDate("Computer", 2, 0));

		homeP = editP.saveComputer();
		
		logger.log(LogStatus.INFO, "Introduced date for computer edited and saved");

		homeP.searchComputer(comName);

		List<WebElement> compRows = driver
				.findElements(By.xpath("//table[@class='computers zebra-striped']//tbody//tr[last()]"));
		for (WebElement allRows : compRows) {
			String actualIntroDate = allRows.findElement(By.xpath("//td[2]")).getText();
			
			String introDate = String.valueOf(DataProviderFactory.getExcel().getDate("Computer", 2, 0));
			
			String expectedIntroDate=Utility.dateFormat(introDate);
				
			Assert.assertTrue(actualIntroDate.contains(expectedIntroDate), "Computer name as " + comName + " not updated introdate");
			
			logger.log(LogStatus.PASS, "Computer name as " + comName + " updated introdate successfully");
					
		}
		report.endTest(logger);

	}
	
	@Test
	public void createandeditComputerdiscontinueDate() {
		
		logger = report.startTest("Create and Edit Discontinued Date");
		
		addComP = homeP.navigateToAddComputerPage();
		String comName = "Computer" + Utility.generateRandomString(4);

		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 5, 0),
				DataProviderFactory.getExcel().getDate("Computer", 5, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 5, 2));
		
		logger.log(LogStatus.INFO, "Data entered in add computer page");

		homeP = addComP.createComputer();
		
		logger.log(LogStatus.INFO, "Entered data saved");

		EditComputerPage editP = homeP.navigateToeditComputerPage(comName);

		editP.editComputerdisconDate(DataProviderFactory.getExcel().getDate("Computer", 7, 0));

		homeP = editP.saveComputer();
		
		logger.log(LogStatus.INFO, "Discontinued date for computer edited and saved");

		homeP.searchComputer(comName);

		List<WebElement> compRows = driver
				.findElements(By.xpath("//table[@class='computers zebra-striped']//tbody//tr[last()]"));
		for (WebElement allRows : compRows) {
			String actualdisconDate = allRows.findElement(By.xpath("//td[3]")).getText();
			
			String disconDate = String.valueOf(DataProviderFactory.getExcel().getDate("Computer", 7, 0));
			
			String expecteddisconDate=Utility.dateFormat(disconDate);
				
			Assert.assertTrue(actualdisconDate.contains(expecteddisconDate), "Computer name as " + comName + " not updated discontinue date");
			
			logger.log(LogStatus.PASS, "Computer name as " + comName + " updated discontinue date successfully");
			
		}
		report.endTest(logger);

	}
	
	@Test
	public void createandeleteComputer() {
		
		logger = report.startTest("Create and Delete Computer");
		
		
		addComP = homeP.navigateToAddComputerPage();
		String comName = "Computer" + Utility.generateRandomString(4);

		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));
		
		logger.log(LogStatus.INFO, "Data entered in add computer page");

		homeP = addComP.createComputer();
		
		logger.log(LogStatus.INFO, "Entered data saved");

		EditComputerPage editP = homeP.navigateToeditComputerPage(comName);
		homeP = editP.deleteComputer(comName);
		
		logger.log(LogStatus.INFO, "Computer deleted using delete button");

		boolean isMsgFound = homeP.presenceOfMsg();

		if (isMsgFound) {
			String actualSuccessMsgText = homeP.successMsgText();
			String expectedSuccessMsgText = "Done! Computer has been deleted";
			assertEquals(actualSuccessMsgText, expectedSuccessMsgText);
			
			logger.log(LogStatus.PASS, "New computer created with name as " + comName + "has been deleted");

			}

		else {
			logger.log(LogStatus.FAIL, "New computer created with name as " + comName + "has not been deleted");
			}
		
		report.endTest(logger);
	}

}
