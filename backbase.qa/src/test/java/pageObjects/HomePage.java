package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.BaseTest;
import library.Utility;

public class HomePage extends BaseTest {
	// Home Page Object Repository
	@FindBy(xpath = "//h1[@class='fill']//a[1]")
	WebElement homePageHeader;

	@FindBy(xpath = "//*[@id='main']//h1[1]")
	WebElement searchHeader;

	@FindBy(xpath = "//input[@id='searchbox' and @type='search']")
	WebElement filterByNameTxt;

	@FindBy(xpath = "//input[@id='searchsubmit' and @type='submit']")
	WebElement filterByNameBtn;

	@FindBy(xpath = "//div[@id='actions']//a[@id='add' and text()='Add a new computer']")
	WebElement addNewComputer;

	@FindBy(xpath = "//a[contains(text(),'Next')]")
	WebElement nextPageBtn;

	// HomePage- Computer Save success message
	@FindBy(xpath = "//*[@id='main']//div[@class='alert-message warning']")
	WebElement actualSuccessMsg;

	// HomePage- search page OR
	@FindBy(xpath = "//td//a[last()]")
	WebElement lastCompLink;

	// Constructor
	public HomePage() {
		// Initiate a webdriver instance and all elements will be initialised
		PageFactory.initElements(driver, this);

	}

	// Method to verify the page header is displayed
	public boolean isPageLoaded() {
		
		boolean isloaded = false;

		// Verify if the page header contains the given message
		if (searchHeader.getText().contains("computers found")) {
			isloaded = true;
		}

		// Return true if "if" statement satisfies else will return false
		return isloaded;
	}

	// Method to navigate to Add to computer page
	public AddComputerPage navigateToAddComputerPage() {
		
		// Wait until add a new computer is visible/clickable and click on it
		Utility.waitforElementVisible(driver, addNewComputer).click();

		// Return to Add computer page
		return new AddComputerPage();

	}

	// Method to get the message of an webelement
	public String successMsgText() {
		
		// Wait until success message is visible and return the text of the webelement
		return Utility.waitforElementVisible(driver, actualSuccessMsg).getText();
	}

	// Method to verify the presence of message when computer is created, edited or
	// deleted
	public boolean presenceOfMsg() {
		
		boolean isMsgFound = false;

		// Wait until success message is visible
		if (Utility.verifyElement(driver, actualSuccessMsg, 10)) {
			isMsgFound = true;
		}

		// Return true if "if" statement satisfies else will return false
		return isMsgFound;
	}

	// Method to search a computer by Computer name and verify the search result
	public boolean searchComputerByName(String cName) {
		
		boolean iscomFound = false;

		// Clear the filter text box
		Utility.waitforElementVisible(driver, filterByNameTxt).clear();

		// Enter value in the filter text box
		filterByNameTxt.sendKeys(cName);

		// Click filter button
		Utility.waitforElementVisible(driver, filterByNameBtn).click();

		// Halt the program for 2 seconds
		Utility.sleep(2);

		// Find a list of webelement
		List<WebElement> compRows = driver
				.findElements(By.xpath("//table[@class='computers zebra-striped']//tbody//tr"));
		for (WebElement allRows : compRows) {
			// Find the text of the webelemet located by xpath
			String branchName = allRows.findElement(By.xpath("//td//a[last()]")).getText();

			if (branchName.equals(cName)) {
				iscomFound = true;
				break;
			}
		}

		// Return true if "if" condition satisfies or else false
		return iscomFound;
	}

	// Method to search a computer by computer name
	public void searchComputer(String cName) {
		
		// Clear the filter text box
		Utility.waitforElementVisible(driver, filterByNameTxt).clear();

		// Enter value in the filter text box
		filterByNameTxt.sendKeys(cName);

		// Click filter button
		Utility.waitforElementVisible(driver, filterByNameBtn).click();

		// Halt the program for 2 seconds
		Utility.sleep(2);

	}

	// Method to navigate to edit computer page
	public EditComputerPage navigateToeditComputerPage(String cName) {
		
		// Clear the filter text box
		Utility.waitforElementVisible(driver, filterByNameTxt).clear();

		// Enter value in the filter text box
		filterByNameTxt.sendKeys(cName);

		// Click filter button
		Utility.waitforElementVisible(driver, filterByNameBtn).click();

		// Halt the program for 2 seconds
		Utility.sleep(2);

		// Click on last row in search result page
		Utility.waitforElementVisible(driver, lastCompLink).click();

		// Return to Edit computer page
		return new EditComputerPage();

	}

}
