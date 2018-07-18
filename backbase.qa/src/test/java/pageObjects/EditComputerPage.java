package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import library.Utility;
import library.Utility.DropDownType;

public class EditComputerPage extends HomePage {

	// EditComputer Page Object Repository
	@FindBy(xpath = "//*[@id='main']//h1[1]")
	WebElement editComputerPageHeader;

	@FindBy(xpath = "//input[@id='name' and @name='name']")
	WebElement computerName;

	@FindBy(xpath = "//input[@id='introduced' and @name='introduced']")
	WebElement introDate;

	@FindBy(xpath = "//input[@id='discontinued' and @name='discontinued']")
	WebElement discontinuedDate;

	@FindBy(xpath = "//select[@id='company' and @name='company']")
	WebElement company;

	@FindBy(xpath = "//input[@type='submit' and @value='Save this computer']")
	WebElement saveComputerBtn;

	@FindBy(xpath = "//a[@href='/computers' and text()='Cancel']")
	WebElement cancelBtn;
	@FindBy(xpath = "//input[@type='submit' and @value='Delete this computer']")
	WebElement deleteComputerBtn;

	// Constructor
	public EditComputerPage() {

		// Initiate a webdriver instance and all elements will be initialised
		PageFactory.initElements(driver, this);
	}

	// Method to edit computer name
	public void editComputerName(String cnewName) {

		// Wait until the computerName text to be visible/clickable and clear the text
		Utility.waitforElementVisible(driver, computerName).clear();

		// Enter text in computerName field
		computerName.sendKeys(cnewName);
	}

	// Method to edit introduced date
	public void editComputerintroDate(String iNewDate) {

		// Wait until the introduced date text to be visible/clickable and clear the
		// text
		Utility.waitforElementVisible(driver, introDate).clear();

		// Enter text in introduced date field
		introDate.sendKeys(iNewDate);

	}

	// Method to edit discontinue date
	public void editComputerdisconDate(String dNewDate) {

		// Wait until the discontinued date text to be visible/clickable and clear the
		// text
		Utility.waitforElementVisible(driver, discontinuedDate).clear();

		// Enter text in discontinued date field
		discontinuedDate.sendKeys(dNewDate);

	}

	// Method to edit company
	public void editComputercompany(String newCom) {

		// Wait until the company drodown is clickable and select data by visible text
		Utility.selectBydropdown(driver, company, DropDownType.BY_VISIBLE_TEXT, newCom);

	}

	// Method to edit computer date and save
	public HomePage saveComputer() {

		// Wait until the save button is clickable and click the button
		Utility.waitforElementVisible(driver, saveComputerBtn).click();

		// Return to Home Page
		return new HomePage();
	}

	// Method to delete the computer
	public HomePage deleteComputer(String cName) {

		// Wait until the delete button is clickable and click the button
		Utility.waitforElementVisible(driver, deleteComputerBtn).click();

		// Return to Home Page
		return new HomePage();

	}

}
