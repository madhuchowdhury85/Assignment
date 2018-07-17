package testCases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
		driver = BaseTest.startBrowser();
		homeP = new HomePage();
		Assert.assertTrue(homeP.isPageLoaded(), "User is not successfully logged in");
	}

	@AfterClass
	public void tearDown() {

		driver.quit();

	}

	@Test
	public void createComputerwithnoName() {
		
		logger = report.startTest("Try to Create Computer with no Computer Name");
		
		addComP = homeP.navigateToAddComputerPage();
		
		String orgComNamecolor= (String) addComP.computerNameLabelColor();
		String comName = "";

		addComP.fillDataComputer(comName, DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));
				
		logger.log(LogStatus.INFO, "Data entered in add computer page except computer name");

		homeP = addComP.createComputer();
		
		Utility.sleep(1);
		String changeComNamecolor= (String) addComP.computerNameLabelColor();
		
		if (!orgComNamecolor.equals(changeComNamecolor)) 
		{
			addComP.cancelComputer();
			logger.log(LogStatus.PASS, "Computer not created without computername");
		}

		else {
			
			logger.log(LogStatus.FAIL, "New computer created with no name");
		}
		
		report.endTest(logger);

	}

	
	@Test
	public void creatComputerwithInvalidIntroDate() {
		
		logger = report.startTest("Try to Create Computer with invalid introduced date");
				
		addComP = homeP.navigateToAddComputerPage();
		String comName = "Computer" + Utility.generateRandomString(4);
		
		String introDate="2019/09/10";
		String orgIntroDatecolor=(String) addComP.introDateLabelColor();

		addComP.fillDataComputer(comName, introDate,
				DataProviderFactory.getExcel().getDate("Computer", 1, 1),
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));
		
		logger.log(LogStatus.INFO, "Data entered in add computer page with invalid date format in introduced date");

		homeP = addComP.createComputer();
		
		Utility.sleep(1);
		
		String changeIntroDatecolor=(String) addComP.introDateLabelColor();
				
		if (!orgIntroDatecolor.equals(changeIntroDatecolor)) 
		{
			addComP.cancelComputer();
			logger.log(LogStatus.PASS, "Computer not created without introduced date");
		}

		else {
			
			logger.log(LogStatus.FAIL, "New computer created without introduced date");
		}
		
		report.endTest(logger);

	}
	
	
	@Test
	public void creatComputerwithInvalidDiscontinueDate() {
		
		logger = report.startTest("Try to Create Computer with invalid discontinued date");
		
		addComP = homeP.navigateToAddComputerPage();
		String comName = "Computer" + Utility.generateRandomString(4);
		
		String discontinueDate="02-15-1985";
		
		String orgDisColor= (String) addComP.disconDateLabelColor();

		addComP.fillDataComputer(comName, 
				DataProviderFactory.getExcel().getDate("Computer", 1, 0),
				discontinueDate,
				DataProviderFactory.getExcel().getstringdata("Computer", 1, 2));
		
		logger.log(LogStatus.INFO, "Data entered in add computer page with invalid date format in Discontinued date");

		homeP = addComP.createComputer();
		
		String changeDisColor= (String) addComP.disconDateLabelColor();
				
		if (!orgDisColor.equals(changeDisColor)) 
		{
			addComP.cancelComputer();
			logger.log(LogStatus.PASS, "Computer not created without Discontinued date");
		}

		else {
			
			logger.log(LogStatus.FAIL, "New computer created without Discontinued date");
		}
		
		report.endTest(logger);

	}



}
