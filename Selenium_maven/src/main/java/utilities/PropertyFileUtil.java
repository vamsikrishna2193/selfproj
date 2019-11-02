package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil
{
	public static String getKeyValue(String key) throws Throwable, Throwable
	{
		Properties configProps = new Properties();
		configProps.load(new FileInputStream(new File("E:/selenium_practice/Selenium_maven/PropertiesFile/Environment.properties")));
		return configProps.getProperty(key);
	}
}
