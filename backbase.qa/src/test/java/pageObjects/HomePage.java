package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest;
import library.Utility;

public class HomePage extends BaseTest
{

	//Home Page Object Repository
	
	@FindBy (xpath="//h1[@class='fill']//a[1]") WebElement homePageHeader;
	
	@FindBy (xpath="//*[@id='main']//h1[1]") WebElement searchHeader;
	
	@FindBy (xpath="//input[@id='searchbox' and @type='search']") WebElement filterByNameTxt;
	
	@FindBy (xpath="//input[@id='searchsubmit' and @type='submit']") WebElement filterByNameBtn;
	
	@FindBy (xpath="//div[@id='actions']//a[@id='add' and text()='Add a new computer']") WebElement addNewComputer;
	
	@FindBy (xpath="//a[contains(text(),'Next')]") WebElement nextPageBtn;
	
	//HomePage- Computer Save success message
	
	@FindBy(xpath="//*[@id='main']//div[@class='alert-message warning']") WebElement actualSuccessMsg;
	
	//HomePage- search page OR
	
	@FindBy(xpath="//td//a[last()]") WebElement lastCompLink;
	
	// Constructor
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean isPageLoaded() 
	{
		boolean isloaded= false;
	
		if(searchHeader.getText().contains("computers found"))
		{
			isloaded= true;
		}
		return isloaded;
    }
		
	
	// Method to navigate to Add to computer page
	
	public AddComputerPage navigateToAddComputerPage()
	{
		Utility.waitforElementVisible(driver, addNewComputer).click();
				
		return new AddComputerPage();
		
	}
	
	// Method to get the message when computer is created, edited or deleted
	public String successMsgText() 
	{
		return Utility.waitforElementVisible(driver, actualSuccessMsg).getText();
	}
	
	// Method to verify the presence of message when computer is created, edited or deleted 
	public boolean presenceOfMsg()
	{
		boolean isMsgFound=false;
		if(Utility.verifyElement(driver, actualSuccessMsg, 10))
		{
			isMsgFound= true;
		}
		return isMsgFound;
	}
	
	//Method to search a computer by Computer name and verify the search result
	public boolean searchComputerByName(String cName)
	{
		boolean iscomFound=false;
		filterByNameTxt.clear();
		filterByNameTxt.sendKeys(cName);
		filterByNameBtn.click();
		
		Utility.sleep(2);
		
		List <WebElement> compRows=driver.findElements(By.xpath("//table[@class='computers zebra-striped']//tbody//tr"));
		for (WebElement allRows:compRows)
		{
			String branchName=allRows.findElement(By.xpath("//td//a[last()]")).getText();
			
			if(branchName.equals(cName))
			{
				iscomFound=true;
				break;
			}
		}
		return iscomFound;
	}
	
	// Method to search a computer by computer name
	public void searchComputer(String cName)
	{
		filterByNameTxt.clear();
		filterByNameTxt.sendKeys(cName);
		filterByNameBtn.click();
		
		Utility.sleep(2);
		
	}
	
	// Method to navigate to edit computer page
	public EditComputerPage navigateToeditComputerPage(String cName)
	{
		boolean iscomFound=false;
		filterByNameTxt.clear();
		filterByNameTxt.sendKeys(cName);
		filterByNameBtn.click();
		
		Utility.sleep(2);
		
		lastCompLink.click();
		return new EditComputerPage();
	
	}


}
