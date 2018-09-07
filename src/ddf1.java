import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ddf1 {
	// Define all variables and objects out here
	WebDriver myD;
	String vBrowserName,vURL,vEmailID,vExpMessage,vActMessage,vResult;



	@Before
	 public void beforeTest() {
		//At the beginning
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\banjkp\\Documents\\Selenium Oxygen\\Drivers\\chromedriver_win32\\chromedriver.exe");
		//1	Open the Browser BrowserName
		myD = new ChromeDriver();
		myD.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		
		// Initialize the variables 
		vBrowserName = "Chrome";
		vURL = "http://anyaut.com/app/";
		vEmailID = "fddssfsf@gmail.com";
		vExpMessage = "Email is not found !";
	}
	
	@Test		
	public void myTest() {
		
		//2	Navigate to the AUT	URL
		myD.get(vURL);
		
		//3	Click on forgot password link
		myD.findElement(By.linkText("Forgot Password")).click();
		
		//4	Enter Email ID EmailID
		myD.findElement(By.id("forgot_email")).clear();
		myD.findElement(By.id("forgot_email")).sendKeys(vEmailID);
		
		//5	Click on Recover Button			
		myD.findElement(By.name("submit")).click();
		
		//6	Verify the message Message
		vActMessage = myD.findElement(By.id("hideMe")).getText();
		System.out.println("Actual Message is :" + vActMessage);
		System.out.println("Expected Message is :" + vExpMessage);
		if (vActMessage.equals(vExpMessage)) {
			vResult = "Pass";
		} else {
			vResult = "Fail";
		}
		System.out.println("Test Case is a "+ vResult);
		
	}
	@After
	public void afterTest() {
		//At the end
		
		//7	Close the browser	
		//myD.close();

	}
	
}
