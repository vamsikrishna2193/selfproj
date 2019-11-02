package commonFunLib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.PropertyFileUtil;

public class FunctionLibrary
{
	public static WebDriver startBrowser(WebDriver driver) throws Throwable
	{
		if(PropertyFileUtil.getKeyValue("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:/Users/ABC/Desktop/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}		
		else if (PropertyFileUtil.getKeyValue("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}		
		else if (PropertyFileUtil.getKeyValue("Browser").equalsIgnoreCase("ie"))
		{
			System.setProperty("", "");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}		
		return driver;
	}
	
	public static void launchApp(WebDriver driver) throws Throwable
	{
		driver.get(PropertyFileUtil.getKeyValue("URL"));
	}
	
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
	public static void clickAction(WebDriver driver, String locaterType, String locaterValue)
	{
		if (locaterType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locaterValue)).sendKeys(Keys.ENTER);
		}		
		else if (locaterType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locaterValue)).sendKeys(Keys.ENTER);
		}		
		else if (locaterType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locaterValue)).sendKeys(Keys.ENTER);
		}
	}
	
	public static void typeAction(WebDriver driver, String locaterType, String locaterValue, String text)
	{
		if (locaterType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locaterValue)).clear();
			driver.findElement(By.id(locaterValue)).sendKeys(text);
		}		
		else if (locaterType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locaterValue)).clear();
			driver.findElement(By.name(locaterValue)).sendKeys(text);
		}		
		else if (locaterType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locaterValue)).clear();
			driver.findElement(By.xpath(locaterValue)).sendKeys(text);
		}
	}
	
	public static void titleValidation(WebDriver driver, String expTitle)
	{
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, expTitle);
	}
	
	public static void waitForElement(WebDriver driver, String locaterType, String locaterValue, String timeToWait)
	{
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(timeToWait));

		if (locaterType.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locaterValue)));
		}
		else if (locaterType.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locaterValue)));
		}
		else if (locaterType.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locaterValue)));
		}
	}

	public static String generateDate()
	{
		DateFormat sdf = new SimpleDateFormat("YYYY_MM_DD");
		Date date = new Date();
		return sdf.format(date);
	}
	
	public static void captureData(WebDriver driver, String locaterType, String locaterValue) throws Throwable
	{
		String data = "";
		if (locaterType.equalsIgnoreCase("id"))
		{
			data = driver.findElement(By.id(locaterValue)).getAttribute("value");
		}
		else if (locaterType.equalsIgnoreCase("name"))
		{
			data = driver.findElement(By.name(locaterValue)).getAttribute("value");
		}
		else if (locaterType.equalsIgnoreCase("xpath"))
		{
			data = driver.findElement(By.xpath(locaterValue)).getAttribute("value");
		}
		
		FileWriter fw = new FileWriter("D:/Chaitanya/Maven_ERP/CaptureData/SupNum.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data);
		bw.flush();
		bw.close();
	}
	
	public static void supplierValidation(WebDriver driver, String column) throws Throwable
	{
		FileReader fr = new FileReader("D:/Chaitanya/Maven_ERP/CaptureData/SupNum.txt");
		BufferedReader br = new BufferedReader(fr);
		String expData = br.readLine();
		int col = Integer.parseInt(column);
		
		if (driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchBox"))).isDisplayed())
		{
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchBox"))).clear();
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchBox"))).sendKeys(expData);
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchButton"))).click();
		}
		else
		{
			driver.findElement(By.xpath(PropertyFileUtil.getKeyValue("searchIcon"))).click();
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchBox"))).clear();
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchBox"))).sendKeys(expData);
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchButton"))).click();
		}
		
		WebElement table = driver.findElement(By.xpath(PropertyFileUtil.getKeyValue("supplierTable")));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		
		for (int i = 1; i < rows.size(); i++)
		{
			String actData = driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]")).getText();
			Assert.assertEquals(actData, expData);
			break;
		}
	}
	
	public static void stockCategories(WebDriver driver)
	{
		WebElement stockItem = driver.findElement(By.xpath("//li[@id='mi_a_stock_items']//a[contains(text(),'Stock Items')]"));
		WebElement stockCategory = driver.findElement(By.xpath("//li[@id='mi_a_stock_categories']//a[contains(text(),'Stock Categories')]"));
		Actions mouse = new Actions(driver);
		mouse.moveToElement(stockItem).build().perform();
		mouse.moveToElement(stockCategory).click().build().perform();
	}
	
	public static void categoryValidation(WebDriver driver, String expData) throws Throwable
	{
		if (driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchBox"))).isDisplayed())
		{
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchBox"))).clear();
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchBox"))).sendKeys(expData);
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchButton"))).click();
		}
		else
		{
			driver.findElement(By.xpath(PropertyFileUtil.getKeyValue("searchIcon"))).click();
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchBox"))).clear();
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchBox"))).sendKeys(expData);
			driver.findElement(By.id(PropertyFileUtil.getKeyValue("searchButton"))).click();
		}
		
		WebElement table = driver.findElement(By.xpath(PropertyFileUtil.getKeyValue("categoryTable")));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		
		for (int i = 1; i < rows.size(); i++)
		{
			String actData = driver.findElement(By.xpath("//table[@id='tbl_a_stock_categorieslist']/tbody/tr[1]/td[4]")).getText();
			Assert.assertEquals(actData, expData);
			break;
		}
	}
}
