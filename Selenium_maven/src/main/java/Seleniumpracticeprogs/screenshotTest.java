package Seleniumpracticeprogs;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


//This is used in order to respond to Listnerclass we use @Listeners,cutomListener.class is to indicate
// that which class it has to listen
@Listeners(CustomListener.class)
public class screenshotTest extends base{
	@BeforeMethod
	public void setup()
	{
		initialization(); 	

}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
}
	@Test
	public void loginTest()
	{
	Assert.assertEquals(false, true);
}
	@Test
	public void searchTest()
	{
	Assert.assertEquals(false, true);
	}
	@Test
	public void createDealsTest()
	{
	Assert.assertEquals(false, true);
}
	@Test
	public void createContactsTest()
	{
	Assert.assertEquals(false, true);
}
}