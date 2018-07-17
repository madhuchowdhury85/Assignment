package library;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility 
{
	
	static WebDriverWait wait;
	
	//Method to wait for an element to be visible or clickable and return the webelement
	public static WebElement waitforElementVisible(WebDriver driver,WebElement element)
	{
		//create an object of webdriver wait
		wait= new WebDriverWait(driver, 30);
		
		//wait until webelement is visible
		wait.until(ExpectedConditions.visibilityOf(element));
		
		//wait until webelement is clickable
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		//return webelement
		return element;
	}

	//Method to halt the program for given time
	public static void  sleep(int time)
	{
		//halt the program
		try {
			Thread.sleep(time*1000);
		} 
		
		//print the exception
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public enum DropDownType
	{
		BY_INDEX, BY_VALUE, BY_VISIBLE_TEXT;
	}
	
	//Method to select value from dropdown by index/value/visible text
	
	public static void selectBydropdown(WebDriver driver, WebElement element, DropDownType type, Object value)
	{
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
		Select select= new Select(element);
		
		switch(type)
		{
		case BY_INDEX:
			int index = (Integer)value;
			select.selectByIndex(index);
			break;
		case BY_VALUE:
			String val = (String)value;
			select.selectByValue(val);
			break;
		case BY_VISIBLE_TEXT:
			String visible_text=(String)value;
			select.selectByVisibleText(visible_text);
			break;
		}
	}
	
	//Method to generate random string of given length
	public static String generateRandomString(int length) 
	{
		String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int N= alphabet.length();
		Random r= new Random();
		
		String randomstr="";
		for (int i=0;i<length; i++)
		{
			randomstr= randomstr+alphabet.charAt(r.nextInt(N));
		}
		return randomstr;
	}

	//Method to wait for an element to be visible and return true or false
	public static boolean verifyElement(WebDriver driver,WebElement element, int second)
	{
		boolean isFound = true;
		try{
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
			
		}catch(Exception e){
			isFound = false;
		}
		return isFound;
	}
	
	public static String dateFormat(String dateStr) 
	{
			
			
			//String dateStr = "21/20/2011";  //2017-05-10
			//String dateStr = "2017-05-10";
			//Date format being filled while update of Computer
		   			try {
				DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");
				
				// parse the date string into Date object
				java.util.Date date = srcDf.parse(dateStr);
				
				DateFormat destDf = new SimpleDateFormat("dd MMM yyyy");
				 
				// format the date into another format
				dateStr = destDf.format(date);
				
		   			} 
			catch (ParseException e) {
				e.printStackTrace();
			}
			return dateStr;
		
			}
	
	public static String getCurrentDate_Time() 
	{
		Date data = new Date();

		SimpleDateFormat dateFormater = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");

		return dateFormater.format(data);
	}

	public static String captureScreenshot(WebDriver driver) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File src = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir")+"\\Screenshots\\Backbase_" + getCurrentDate_Time() + ".png";
		try {
			
			FileUtils.copyFile(src, new File(destination));
			
			} catch (Exception e) {
			System.out.println("Unable to capture screenshots " + e.getMessage());
		}

		return destination;
	}
	


		
	
}
