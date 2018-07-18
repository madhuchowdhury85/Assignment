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

public class Utility {

	static WebDriverWait wait;

	// Method to wait for an element to be visible or clickable and return the
	// webelement
	public static WebElement waitforElementVisible(WebDriver driver, WebElement element) {

		// create an object of webdriver wait
		wait = new WebDriverWait(driver, 30);

		// wait until webelement is visible
		wait.until(ExpectedConditions.visibilityOf(element));

		// wait until webelement is clickable
		wait.until(ExpectedConditions.elementToBeClickable(element));

		// return webelement
		return element;
	}

	// Method to halt the program for given time
	public static void sleep(int time) {

		// halt the program for given time
		try {
			Thread.sleep(time * 1000);
		}

		// print the exception
		catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Enum consisting set of values
	public enum DropDownType {
		BY_INDEX, BY_VALUE, BY_VISIBLE_TEXT;
	}

	// Method to select value from dropdown by index/value/visible text
	public static void selectBydropdown(WebDriver driver, WebElement element, DropDownType type, Object value) {

		// wait for 30 seconds until the webelement is visible
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));

		// Create a select class object on webelement
		Select select = new Select(element);

		switch (type) {
		case BY_INDEX:
			int index = (Integer) value;

			// Select dropdown value by index
			select.selectByIndex(index);

			break;
		case BY_VALUE:
			String val = (String) value;

			// Select dropdown data by value
			select.selectByValue(val);

			break;
		case BY_VISIBLE_TEXT:
			String visible_text = (String) value;

			// Select dropdown value by visible text
			select.selectByVisibleText(visible_text);
			break;
		}
	}

	// Method to generate random string of given length
	public static String generateRandomString(int length) {

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// length of alphabet
		int N = alphabet.length();

		// Create object of Random class
		Random r = new Random();

		String randomstr = "";

		// Initiate a for loop
		for (int i = 0; i < length; i++) {

			// Create a random alphabet string of given length
			randomstr = randomstr + alphabet.charAt(r.nextInt(N));
		}

		// Return the random string
		return randomstr;
	}

	// Method to wait for an element to be visible and return true or false
	public static boolean verifyElement(WebDriver driver, WebElement element, int second) {

		boolean isFound = true;
		try {
			// wait for 30 seconds until the webelement is visible
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {

			isFound = false;
		}

		// return true if webelement is found or else return false
		return isFound;
	}

	// Method to convert a date format to another format and return the date
	public static String dateFormat(String dateStr) {

		// Date format being filled while update of Computer
		try {
			DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");

			// Parse the date string into Date object
			java.util.Date date = srcDf.parse(dateStr);

			DateFormat destDf = new SimpleDateFormat("dd MMM yyyy");

			// Format the date into another format
			dateStr = destDf.format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;

	}

	// Method to generate current date and time in the desired format
	// return the dateandtime
	public static String getCurrentDate_Time() {

		// Create a Date object
		Date data = new Date();

		// Create an object of SimpleDateFormat with the given format
		SimpleDateFormat dateFormater = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");

		// Return the date in the given format
		return dateFormater.format(data);
	}

	// Method to capture screenshot and return the file destination
	public static String captureScreenshot(WebDriver driver) {

		// Convert web driver object to TakeScreenshot- typecasting
		TakesScreenshot ts = (TakesScreenshot) driver;

		// Take screenshot and store as a file format
		File src = ts.getScreenshotAs(OutputType.FILE);

		// File destination
		String destination = System.getProperty("user.dir") + "\\Screenshots\\Backbase_" + getCurrentDate_Time()
				+ ".png";
		try {

			// now copy the screenshot to desired location using copyFile
			FileUtils.copyFile(src, new File(destination));

		} catch (Exception e) {
			System.out.println("Unable to capture screenshots " + e.getMessage());
		}
		// return file destination
		return destination;
	}

}
