package CASpro;

//java imports
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
//testng imports
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
//selenium imports
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
 	public class Cas_Project {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Actions actions;

	@BeforeTest
	public void setup()

	{
		//opening chrome
		driver = new ChromeDriver();
		//maximizing screen
		driver.manage().window().maximize();
		//opening Be Cognizant
	    driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
	    //using implicit wait
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    //using explicit wait
	    wait =new WebDriverWait(driver,Duration.ofSeconds(10));
	  
	}

	@Test(priority=1)
	public void userInformation() throws InterruptedException, IOException
	{
		//clicking to get user information
		WebElement clk = driver.findElement(By.xpath("//div[@id='O365_NavHeader']/div[3]"));
	 	actions = new Actions(driver);
		actions.doubleClick(clk).perform();
     	Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='O365_NavHeader']/div[3]")).click();
		Thread.sleep(1000);
		//printing username
		System.out.println("The name is:");
		String name=driver.findElement(By.id("mectrl_currentAccount_primary")).getText();
		System.out.println(name);
	    Thread.sleep(1000);
	    //printing usernmail
		String email=driver.findElement(By.id("mectrl_currentAccount_secondary")).getText();
		System.out.println("email of the user is");
		System.out.println(email);	
		System.out.println("*********************************************************");

	}

	@Test(priority=2 )

	public void getHeader() throws InterruptedException
	{
		//storing heade elements
		List<WebElement> headers = driver.findElements(By.xpath("//div[@id='O365_NavHeader']"));
		System.out.println("Header: ");
		//using for loop to iterate through the elements
		for(WebElement header: headers)
		{
			//geting the text present in header
			System.out.println(header.getText());
		}
		System.out.println("******************************************************************************************");
	}

	@Test(priority=3,dependsOnMethods = {"getHeader"})
	public void getSubHeader() throws InterruptedException
	{
		//storing subheader elements
		List<WebElement> subHeaders = driver.findElements(By.xpath("//div[@id='spTopPlaceholder']"));
		System.out.println("Sub Header: ");
		for(WebElement subHeader: subHeaders)
		{
			System.out.println(subHeader.getText());
	}
		System.out.println("***************************************************************************************************");
	}

	@Test(priority=4,dependsOnMethods = {"getSubHeader"})
	public void corporateFunction() throws InterruptedException, IOException
	{
		driver.findElement(By.xpath("//div/div[6]/button/span/span/span")).click();
		WebElement element1 =driver.findElement(By.xpath("//span[text()='Legal & Corporate Affairs']"));
		//hovering over the element
		actions.moveToElement(element1).build().perform();
		TakesScreenshot shot = (TakesScreenshot)driver;
		File src = shot.getScreenshotAs(OutputType.FILE);
		File trg = new File("C:\\Users\\2318113\\eclipse-workspace\\cognizant_header\\screenshot\\cognizant.png");
		FileUtils.copyFile(src, trg);
		System.out.println(" Screenshot taken");
		WebElement element2= driver.findElement(By.xpath("//span[text()='Ethics and Compliance']"));
		//validating if ethics and compliance is present or not by using assert
	    Assert.assertTrue(element2.isDisplayed(),"Element is not present");
	    Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Ethics and Compliance']")).click();	
	}

	@Test(priority=5,dependsOnMethods = {"corporateFunction"})
	public void resourceLink() throws InterruptedException
	{
		WebElement element1= driver.findElement(By.xpath("//div[@title='Ethics & Compliance']"));
		//validating Ethics and compliance is present or not
		 Assert.assertTrue(element1.isDisplayed(),"Element is not present"); 
		WebElement element2= driver.findElement(By.xpath("//span[text()='Resources / Links']"));
		//validating if resources and links is present or not
	    Assert.assertTrue(element2.isDisplayed(),"Element is not present");
	    List<WebElement> resources = driver.findElements(By.xpath("//span[text()='Resources / Links']/ancestor::div[@data-automation-id='webPartHeader']//following-sibling::div//*[@class='ms-List-cell']"));
		System.out.println("Resources & Links :"); 
		for(WebElement resource: resources)
		{
			System.out.println(resource.getText());
		}
		System.out.println("***************************************************************************************************");
	}
	
	@AfterTest
	public void done()
	{ 
		//closing all windows
       driver.quit(); 
    } 

}

