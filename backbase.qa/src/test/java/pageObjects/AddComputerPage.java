package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import library.Utility;
import library.Utility.DropDownType;

public class AddComputerPage extends HomePage

{
	// AddComputer Page Object Repository
	@FindBy(xpath = "//*[@id='main']//h1[1]")
	WebElement addComputerPageHeader;

	@FindBy(xpath = "//input[@id='name' and @name='name']")
	WebElement computerName;

	@FindBy(xpath = "//label[text()='Computer name']")
	WebElement computerNameLabel;

	@FindBy(xpath = "//input[@id='introduced' and @name='introduced']")
	WebElement introDate;

	@FindBy(xpath = "//label[text()='Introduced date']")
	WebElement introDateLabel;

	@FindBy(xpath = "//input[@id='discontinued' and @name='discontinued']")
	WebElement discontinuedDate;

	@FindBy(xpath = "//label[text()='Discontinued date']")
	WebElement discontinuedDateLabel;

	@FindBy(xpath = "//select[@id='company' and @name='company']")
	WebElement company;

	@FindBy(xpath = "//input[@type='submit' and @value='Create this computer']")
	WebElement createComputerBtn;

	@FindBy(xpath = "//a[@href='/computers' and text()='Cancel']")
	WebElement cancelBtn;

	// Constructor
	public AddComputerPage() {

		// Initiate a webdriver instance and all elements will be initialised
		PageFactory.initElements(driver, this);
	}

	// Method to return colour of computer name label
	public Object computerNameLabelColor() {

		// Return the color value of Computer Name label
		return computerNameLabel.getCssValue("color");
	}

	// Method to return colour of introduced date label
	public Object introDateLabelColor() {

		// Return the color value of introduced date label
		return introDateLabel.getCssValue("color");
	}

	// Method to return colour of discontinued date label
	public Object disconDateLabelColor() {

		// Return the color value of discontinued date label
		return discontinuedDateLabel.getCssValue("color");
	}

	// Method to fill data in add to computer page
	public void fillDataComputer(String cName, String iDate, String dDate, String com) {

		// Wait for the computerName text to be visible/clickable and enter text
		Utility.waitforElementVisible(driver, computerName).sendKeys(cName);

		// Wait for the introduced date to be visible/clickable and enter date
		Utility.waitforElementVisible(driver, introDate).sendKeys(iDate);

		// Wait for the discontinued date to be visible/clickable and enter date
		Utility.waitforElementVisible(driver, discontinuedDate).sendKeys(dDate);

		// Wait for the dropdown to be visible and select data by visible text
		Utility.selectBydropdown(driver, company, DropDownType.BY_VISIBLE_TEXT, com);

	}

	// Method to click create after filling data in add to computer page
	public HomePage createComputer() {

		// Wait for Create a computer button to clickable and click
		Utility.waitforElementVisible(driver, createComputerBtn).click();

		// Return to Home Page
		return new HomePage();
	}

	// Method to click cancel after filling data in add to computer page
	public HomePage cancelComputer() {

		// Wait for cancel to clickable and click
		Utility.waitforElementVisible(driver, cancelBtn).click();

		// Return to Home Page
		return new HomePage();
	}

}
