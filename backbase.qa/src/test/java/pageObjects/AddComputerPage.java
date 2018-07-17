package pageObjects;

import javax.swing.DropMode;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import library.Utility;

import library.Utility.DropDownType;

public class AddComputerPage extends HomePage

{

	//AddComputer Page Object Repository
	
	@FindBy (xpath="//*[@id='main']//h1[1]") WebElement addComputerPageHeader;
	
	@FindBy (xpath="//input[@id='name' and @name='name']") WebElement computerName;
	
	@FindBy (xpath="//label[text()='Computer name']") WebElement computerNameLabel;
	
	@FindBy (xpath="//input[@id='introduced' and @name='introduced']") WebElement introDate;
	
	@FindBy (xpath="//label[text()='Introduced date']") WebElement introDateLabel;
	
	@FindBy (xpath="//input[@id='discontinued' and @name='discontinued']") WebElement discontinuedDate;
	
	@FindBy (xpath="//label[text()='Discontinued date']") WebElement discontinuedDateLabel;
	
	@FindBy (xpath="//select[@id='company' and @name='company']") WebElement company;
	
	@FindBy (xpath="//input[@type='submit' and @value='Create this computer']") WebElement createComputerBtn;
	
	@FindBy (xpath="//a[@href='/computers' and text()='Cancel']") WebElement cancelBtn;
	
	//Constructor
	public AddComputerPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public Object computerNameLabelColor() 
	{
		return computerNameLabel.getCssValue("color");
	}
	
	public Object introDateLabelColor() 
	{
		return introDateLabel.getCssValue("color");
	}
	
	public Object disconDateLabelColor() 
	{
		return discontinuedDateLabel.getCssValue("color");
	}
	
		
	//Method to fill data in add to computer page
	public void fillDataComputer(String cName, String iDate, String dDate, String com)
	{
		
		//String ComputerName="madhurima";
		Utility.waitforElementVisible(driver, computerName).sendKeys(cName);
		Utility.waitforElementVisible(driver, introDate).sendKeys(iDate);
		Utility.waitforElementVisible(driver, discontinuedDate).sendKeys(dDate);
		
		Utility.selectBydropdown(driver, company, DropDownType.BY_VISIBLE_TEXT, com);
		
					
	}
	
	//Method to click create after filling data in add to computer page
	public HomePage createComputer()
	{
		
		Utility.waitforElementVisible(driver, createComputerBtn).click();
		return new HomePage();
	}
	
	//Method to click cancel after filling data in add to computer page
	public HomePage cancelComputer()
	{
		
		Utility.waitforElementVisible(driver, cancelBtn).click();
		return new HomePage();
	}

}
