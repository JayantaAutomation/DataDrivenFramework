package com.datadriven.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ParameterizeTest {

	public static void main(String[] args) {
		
		//webdriver code
		System.setProperty("webdriver.chrome.driver","C:\\Users\\banjkp\\Documents\\Selenium Oxygen\\Drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); // launch chrome
		driver.get("https://scgi.half.ebay.com/ws/eBayISAPI.dll?RegisterEnterInfo&usage=2943&ru="); // enter url
		driver.manage().window().maximize();
		
		//Data driven approach (Parameterization): driving the test data from excel file
		//get test data from excel
		Xls_Reader reader = new Xls_Reader("C:/Users/banjkp/eclipse-workspace/DDF/src/com/testdata/java/DataDrivenTest.xlsx");
		int rowCount = reader.getRowCount("registration");
		
		reader.addColumn("registration", "status");
		
		//Parameterization:
		for(int rowNum = 2; rowNum <= rowCount; rowNum++) {
		System.out.println("=============");		
		String firstname = reader.getCellData("registration","firstname", rowNum);
		System.out.println(firstname);
		
		String lastname = reader.getCellData("registration","lastname", rowNum);
		System.out.println(lastname);
		
		String address1 = reader.getCellData("registration","address1", rowNum);
		System.out.println(address1);
		
		String address2 = reader.getCellData("registration","address2", rowNum);
		System.out.println(address2);
		
		String city = reader.getCellData("registration","city", rowNum);
		System.out.println(city);
		
		String state = reader.getCellData("registration","state", rowNum);
		System.out.println(state);
		
		String zipcode = reader.getCellData("registration","zipcode", rowNum);
		System.out.println(zipcode);
		
		String emailaddress = reader.getCellData("registration","emailaddress", rowNum);
		System.out.println(emailaddress);
		
		//enter data code
		driver.findElement(By.name("firstname")).clear();
		driver.findElement(By.name("firstname")).sendKeys(firstname);
		driver.findElement(By.name("lastname")).clear();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		driver.findElement(By.id("address1")).clear();
		driver.findElement(By.id("address1")).sendKeys(address1);
		driver.findElement(By.id("address2")).clear();
		driver.findElement(By.id("address2")).sendKeys(address2);
		driver.findElement(By.xpath("//*[@id='city']")).clear();
		driver.findElement(By.xpath("//*[@id='city']")).sendKeys(city);
		Select select = new Select( driver.findElement(By.id("state")));   
		select.selectByVisibleText(state);
		
		driver.findElement(By.name("zip")).clear();
		driver.findElement(By.name("zip")).sendKeys(zipcode);
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(emailaddress);
		driver.findElement(By.name("retype_email")).clear();
		driver.findElement(By.name("retype_email")).sendKeys(emailaddress);
		
		reader.setCellData("registration", "status", rowNum, "Pass"); //write the data into excel
		}

		//close chrome browser
		driver.close();
	}

}
