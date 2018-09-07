package com.datadriven.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DataDrivenTest {
	public static void main(String[]args) {
		
		Xls_Reader reader = new Xls_Reader("C:/Users/banjkp/eclipse-workspace/DDF/src/com/testdata/java/DataDrivenTest.xlsx");
		
		String firstname = reader.getCellData("registration","firstname", 2);
		System.out.println(firstname);
		
		String lastname = reader.getCellData("registration","lastname", 2);
		System.out.println(lastname);
		
		String address1 = reader.getCellData("registration","address1", 2);
		System.out.println(address1);
		
		String address2 = reader.getCellData("registration","address2", 2);
		System.out.println(address2);
		
		String city = reader.getCellData("registration","city", 2);
		System.out.println(city);
		
		String state = reader.getCellData("registration","state", 2);
		System.out.println(state);
		
		String zipcode = reader.getCellData("registration","zipcode", 2);
		System.out.println(zipcode);
		
		String emailaddress = reader.getCellData("registration","emailaddress", 2);
		System.out.println(emailaddress);
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\banjkp\\Documents\\Selenium Oxygen\\Drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); // launch chrome
		driver.get("https://scgi.half.ebay.com/ws/eBayISAPI.dll?RegisterEnterInfo&usage=2943&ru="); // enter url
		driver.manage().window().maximize();
		
		driver.findElement(By.name("firstname")).sendKeys(firstname);
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		driver.findElement(By.id("address1")).sendKeys(address1);
		driver.findElement(By.id("address2")).sendKeys(address2);
		driver.findElement(By.xpath("//*[@id='city']")).sendKeys(city);
		Select select = new Select( driver.findElement(By.id("state")));   
		select.selectByVisibleText(state);
		
		driver.findElement(By.name("zip")).sendKeys(zipcode);
		driver.findElement(By.name("email")).sendKeys(emailaddress);
		driver.findElement(By.name("retype_email")).sendKeys(emailaddress);
	
		
	}

}
