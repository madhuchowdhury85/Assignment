package library;

import java.text.DateFormat;

import java.text.ParseException;

import java.text.SimpleDateFormat;




public class Date {

	public static void main(String[] args) {
try {
			
			//String dateStr = "21/20/2011";  //2017-05-10
			String dateStr = "2017-05-10";
			
			DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");
			
			// parse the date string into Date object
			java.util.Date date = srcDf.parse(dateStr);
			
			DateFormat destDf = new SimpleDateFormat("dd MMM yyyy");
			 
			// format the date into another format
			dateStr = destDf.format(date);
			
			System.out.println("Converted date is : " + dateStr);
			
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
