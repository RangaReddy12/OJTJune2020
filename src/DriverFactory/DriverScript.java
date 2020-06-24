package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
String inputpath="G:\\Selenium_LiveProject\\ERP_StockAccounting\\TestInput\\InputSheet.xlsx";
String outputpath="G:\\Selenium_LiveProject\\ERP_StockAccounting\\TestOutput\\Hybrid.xlsx";
public void startTest()throws Throwable
{
	//creating reference object for excel util methods
	ExcelFileUtil excel=new ExcelFileUtil(inputpath);
	//iterating all row in MasterTestCases sheet
	for(int i=1;i<=excel.rowCount("MasterTestCases");i++)
	{
	if(excel.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
	{
		//store module name into TCModule 
		String TCModule=excel.getCellData("MasterTestCases", i, 1);
		//generate report under project
	report=new ExtentReports("./ExtentReports/"+TCModule+FunctionLibrary.generateDate()+".html");
		//iterate all rows in TCModule sheet
		for(int j=1;j<=excel.rowCount(TCModule);j++)
		{
		test=report.startTest(TCModule);	
		//read all columns in 	TCModule
			String Description=excel.getCellData(TCModule, j, 0);
			String Function_Name=excel.getCellData(TCModule, j, 1);
			String Locator_Type=excel.getCellData(TCModule, j, 2);
			String Locator_Value=excel.getCellData(TCModule, j, 3);
			String Test_Data=excel.getCellData(TCModule, j, 4);
			try {
				//call functions
			if(Function_Name.equalsIgnoreCase("startBrowser"))	
			{
				driver=FunctionLibrary.startBrowser();
				test.log(LogStatus.INFO, Description);
			}
			else if(Function_Name.equalsIgnoreCase("openApplication"))
			{
				FunctionLibrary.openApplication(driver);
				test.log(LogStatus.INFO, Description);
			}
			else if(Function_Name.equalsIgnoreCase("waitForElement"))
			{
		FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
		test.log(LogStatus.INFO, Description);
			}
			else if(Function_Name.equalsIgnoreCase("typeAction"))
			{
			FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);	
			test.log(LogStatus.INFO, Description);
			}
			else if(Function_Name.equalsIgnoreCase("clickAction"))	
			{
				FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
				test.log(LogStatus.INFO, Description);
			}
			else if(Function_Name.equalsIgnoreCase("closeBrowser"))
			{
				FunctionLibrary.closeBrowser(driver);
				test.log(LogStatus.INFO, Description);
			}
			else if(Function_Name.equalsIgnoreCase("captureData"))
			{
				FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
				test.log(LogStatus.INFO, Description);
			}
			else if(Function_Name.equalsIgnoreCase("tableValidation"))
			{
				FunctionLibrary.tableValidation(driver, Test_Data);
				test.log(LogStatus.INFO, Description);
			}
			else if(Function_Name.equalsIgnoreCase("stockCategories"))
			{
				FunctionLibrary.stockCategories(driver);
				test.log(LogStatus.INFO, Description);
			}
			else if(Function_Name.equalsIgnoreCase("stockValidation"))
			{
				FunctionLibrary.stockValidation(driver, Test_Data);
				test.log(LogStatus.INFO, Description);
			}
			//write as pass into status column TCModule
			excel.setCellData(TCModule, j, 5, "Pass", outputpath);
			test.log(LogStatus.PASS, Description);
			//write as pass into MaterTestCases sheet
			excel.setCellData("MasterTestCases", i, 3, "Pass", outputpath);
			}catch(Exception e)
			{
				//take screen shot and store
	File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//copy screen shot into your project
	FileUtils.copyFile(screen, new File("./Screens/"+TCModule+FunctionLibrary.generateDate()+".png"));
			//write as Fail into status column TCModule	
			excel.setCellData(TCModule, j, 5, "Fail", outputpath);
			test.log(LogStatus.FAIL, Description);
			//write as Fail into MaterTestCases sheet
			excel.setCellData("MasterTestCases", i, 3, "Fail", outputpath);
			System.out.println(e.getMessage());
			}
			report.endTest(test);
			report.flush();
		}
		}
	else
	{
		//write as not executed into status column in MasterTestCases
		excel.setCellData("MasterTestCases", i, 3, "Not Executed", outputpath);
	}
	}
}

}
