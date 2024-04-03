package CASpro;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
public class practice {
	public static void mian(String args[]) {
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.amazon.in/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0,3000)", "");
		
	}

}
