package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider
{

	public static Properties prop;
	public ConfigDataProvider()
	
	{
		File src = new File("./Config/config.properties");

		try {
			FileInputStream input = new FileInputStream(src);
			prop = new Properties();
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getBrowser()
	{
		return prop.getProperty("browser");
	}
	
	public String getStagingURL()
	{
		return prop.getProperty("url");
	}
	}

