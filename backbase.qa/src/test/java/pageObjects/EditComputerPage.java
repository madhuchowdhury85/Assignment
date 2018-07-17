package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.Utility;
import library.Utility.DropDownType;

public class EditComputerPage extends HomePage
{

	//EditComputer Page Object Repository
	
   @FindBy (xpath="//*[@id='main']//h1[1]") WebElement editComputerPageHeader;
	
	@FindBy (xpath="//input[@id='name' and @name='name']") WebElement computerName;
	
	@FindBy (xpath="//input[@id='introduced' and @name='introduced']") WebElement introDate;
	
	@FindBy (xpath="//input[@id='discontinued' and @name='discontinued']") WebElement discontinuedDate;
	
	@FindBy (xpath="//select[@id='company' and @name='company']") WebElement company;
	
	@FindBy (xpath="//input[@type='submit' and @value='Save this computer']") WebElement saveComputerBtn;
	
	@FindBy (xpath="//a[@href='/computers' and text()='Cancel']") WebElement cancelBtn;
	@FindBy (xpath="//input[@type='submit' and @value='Delete this computer']") WebElement deleteComputerBtn;
	
	//Constructor
	public EditComputerPage()
	{
		PageFactory.initElements(driver, this);
	}
		
	//Method to edit computername
	public void editComputerName(String cnewName)
	{
		Utility.waitforElementVisible(driver, computerName).clear();
		computerName.sendKeys(cnewName);
		
	}
	
	//Method to edit introduced date
	public void editComputerintroDate(String iNewDate)
	{
				
	 Utility.waitforElementVisible(driver, introDate).clear();
	 introDate.sendKeys(iNewDate);
		
	}
	
	//Method to edit discontinue date
		public void editComputerdisconDate(String dNewDate)
		{
					
		 Utility.waitforElementVisible(driver, discontinuedDate).clear();
		 discontinuedDate.sendKeys(dNewDate);
			
		}
	
	//Method to edit company
	public void editComputercompany(String newCom)
	{
		
	 Utility.selectBydropdown(driver, company, DropDownType.BY_VISIBLE_TEXT, newCom);
		
	}
	

	//Method to edit computer date and save 
	public HomePage saveComputer()
	{
		
		Utility.waitforElementVisible(driver, saveComputerBtn).click();
		return new HomePage();
	}
	
	//Method to delete the computer
	public HomePage deleteComputer(String cName)
	{
		
	 Utility.waitforElementVisible(driver, deleteComputerBtn).click();
	 return new HomePage();
		
	}

	
	
	
}
