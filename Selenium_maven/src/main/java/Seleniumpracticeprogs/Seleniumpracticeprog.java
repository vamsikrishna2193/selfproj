package Seleniumpracticeprogs;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Seleniumpracticeprog {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "C:/Users/ABC/Documents/chromedriver.exe");
																									
		driver.get("http://www.SoftwareTestingMaterial.com");

        System.out.println(driver.manage().window().getSize());

        //Create object of Dimensions class

        Dimension d = new Dimension(480,620);

        //Resize the current window to the given dimension

        driver.manage().window().setSize(d);

        System.out.println(driver.manage().window().getSize()); 
		

     } 


	


	}


