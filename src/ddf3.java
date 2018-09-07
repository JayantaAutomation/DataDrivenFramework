import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ddf3 {
	// Define all variables and objects out here
	WebDriver myD;
	String vBrowserName,vURL,vEmailID,vExpMessage,vActMessage,vResult;
	String xlPath, xlSheet;
	String[][] xlData;
	static int xRows, xCols;


	@Before
	 public void beforeTest() {
		//At the beginning
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\banjkp\\Documents\\Selenium Oxygen\\Drivers\\chromedriver_win32\\chromedriver.exe");
		//1	Open the Browser BrowserName
		myD = new ChromeDriver();
		myD.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	
	//Read the test Data excel
	xlPath = "C:\\Users\\banjkp\\Documents\\Selenium Oxygen\\Drivers\\TestData.xls";
	xlSheet = "TestData";
	xlData = readXL(xlPath, xlSheet);
	
	Reader
	}
	
	@Test		
	public void myTest() {
		
		// read the Excel.
		// We get each set of data into the variables
		// Run the Test Case
		// Repeat till end of the excel file
		
		for (int rowCount = 1; rowCount < xRows; rowCount++) {
			
			// Initialize the variables 
			vBrowserName = xlData[rowCount][1];
			vURL = xlData[rowCount][2];
			vEmailID = xlData[rowCount][3];
			vExpMessage = xlData[rowCount][4];		
			
			//1	Navigate to the AUT	URL
			myD.get(vURL);
			
			// 2 Recover Password
			recoverPassword(vEmailID);
			
			//6	Verify the message Message
			verifyAlertMessage(vExpMessage);
			
		}


	}
	
	@After
	public void afterTest() {
		//At the end
		
		//7	Close the browser	
		myD.close();

	}
	
	public void verifyAlertMessage(String vExpMsg) {
		//Purpose : Verify that the alert Message comes as expected
		//Input : Expected Message
		//Output : None. just print statements in this case.
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
		//Input : Email id of the user
		//Output : None. 
		
		//3	Click on forgot password link
		myD.findElement(By.linkText("Forgot Password")).click();
		
		//4	Enter Email ID EmailID
		myD.findElement(By.id("forgot_email")).clear();
		myD.findElement(By.id("forgot_email")).sendKeys(vEmailID);
		
		//5	Click on Recover Button			
		myD.findElement(By.name("submit")).click();
		
	}

	public static String[][] readXL(String fPath, String fSheet) throws Exception{
		// Purpose : Read an Excel file into a 2D array
		// Inputs  : XL Path and XL Sheet name
		// Output  : 
		String [][] xData;
		
		File myxl = new File(fPath);
		FileInputStream myStream = new FileInputStream(myxl);
		HSSFWorkbook myWB = new HSSFWorkbook(myStream);
		HSSFSheet mySheet = myWB.getSheet(fSheet);
		xRows = mySheet.getLastRowNum()+1;
		xCols = mySheet.getRow(0).getLastCellNum();
		System.out.println("Total Rows in Excel are " + xRows);
		System.out.println("Total Cols in Excel are " + xCols);
		xData = new String [xRows][xCols];
		for (int i = 0; i< xRows; i++) {
			HSSFRow row = mySheet.getRow(i);
			for (int j = 0; j < xCols; j++) {
				HSSFCell cell = row.getCell(j);
				String value = "-";
				if (cell!=null) {
					value = cellToString(cell);
				}
				xData[i][j] = value;
				System.out.print(value);
				System.out.println("------------");
			}
			System.out.println("");
		}
		myxl = null;// Memory gets released
		return xData;
	}
	
	@SuppressWarnings("deprecation")
	public static String cellToString(HSSFCell cell) {
		// This function will convert an object of type excel cell to a string value
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case HSSFCell.CELL_TYPE_NUMERIC: //0
			result = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING: //1
			result = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA: //2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case HSSFCell.CELL_TYPE_BLANK: //3
			result = "%";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: //4
			result = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR: //5
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type: " +type);
		}
		return result.toString();
	}
	
	
	public static String[][] writeXL(String fPathe, String fSheet) throws Exception{
		//Inputs : XL Path and XL Sheet name
		//Output :
	
		File outFile = new File(fPath);
		HSSFWorkbook WB = new HSSFWorkbook();
		HSSFSheet osSheet = WB.createSheet(fSheet);
		int xR_TS = xData.length;
		int xC_TS = xData[0].length;
		for (int myrow =0; myrow < xR_TS; myrow++) {
			HSSFRow row = osheet.createRow(myrow);
		}
		//System.out.println("Total Rows in Excel are " + xRows);)
}




	