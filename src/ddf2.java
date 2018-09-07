import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ddf2 {
	// Define all variables and objects out here
	WebDriver myD;
	String vBrowserName,vURL,vEmailID,vExpMessage,vActMessage,vResult;



	@Before
	public void beforeTest() {
		//At the beginning
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\banjkp\\Documents\\Selenium Oxygen\\Drivers\\chromedriver_win32\\chromedriver.exe");
		//1	Open the Browser BrowserName
		myD = new ChromeDriver();
		myD.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		// Initialize the variables 
		vBrowserName = "Chrome";
		vURL = "http://anyaut.com/app/";
		vEmailID = "jayanta.panda@gmail.com";
		vExpMessage = "Email is not found !";
	}
	
	@Test		
	public void myTest() {
		
		//1	Navigate to the AUT	URL
		myD.get(vURL);
		
		// 2 Recover Password
		recoverPassword(vEmailID);
		
		//6	Verify the alert Message
		verifyAlertMessage(vExpMessage);

	}
	
	@After
	public void afterTest() {
		//At the end
		
		//7	Close the browser	
		myD.close();

	}
	
	public void verifyAlertMessage(String vExpMsg) {
		//Purpose : Verify that the alert Message comes as expected
		//Input   : Expected Message
		//Output  : None. just print statements in this case.
		vActMessage = myD.findElement(By.id("hideMe")).getText();
		System.out.println("Actual Message is :" + vActMessage);
		System.out.println("Expected Message is :" + vExpMsg);
		if (vActMessage.equals(vExpMsg)) {
			vResult = "Pass";
		} else {
			vResult = "Fail";
		}
		System.out.println("Test Case is a "+ vResult);
	}
	
	public void recoverPassword(String vEID) {
		//Purpose : Perform steps to recover a user password
		//Input   : Email id of the user
		//Output  : None. 
		
		//3	Click on forgot password link
		myD.findElement(By.linkText("Forgot Password")).click();
		
		//4	Enter Email ID EmailID
		myD.findElement(By.id("forgot_email")).clear();
		myD.findElement(By.id("forgot_email")).sendKeys(vEmailID);
		
		//5	Click on Recover Button			
		myD.findElement(By.name("submit")).click();
		
	}

}



