package Seleniumpracticeprogs;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class base {
	public static WebDriver driver;
public static void initialization()
{
	System.setProperty("webdriver.chrome.driver", "C:/Users/ABC/Documents/chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("https://www.google.com/");
	
}

public void failed(String testMethodName)
{
	File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(srcFile, new File("E:/selenium_practice/Selenium_maven/Screenshots/"+testMethodName
				+"_"+".jpg"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}
}
