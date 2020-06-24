package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERPFunction;
import Utilities.ExcelFileUtil;

public class DataDriven {
WebDriver driver;
String inputpath="G:\\Selenium_LiveProject\\ERP_StockAccounting\\TestInput\\TestData.xlsx";
String outputpath="G:\\Selenium_LiveProject\\ERP_StockAccounting\\TestOutput\\DataDrivenResults.xlsx";
@BeforeTest
public void setUp()throws Throwable
{
	String applaunch=ERPFunction.verifyUrl("http://projects.qedgetech.com/webapp");
	Reporter.log(applaunch,true);
	String loginres=ERPFunction.verifyLogin("admin", "master");
	Reporter.log(loginres,true);
}
@Test
public void supplierCreation()throws Throwable
{
	//access Excel methods
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	int rc=xl.rowCount("Supplier");
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
	//access all columns from
	String sname=xl.getCellData("Supplier", i, 0);
	String address=xl.getCellData("Supplier", i, 1);
	String city=xl.getCellData("Supplier", i, 2);
	String country=xl.getCellData("Supplier", i, 3);
	String cperson=xl.getCellData("Supplier", i, 4);
	String pnumber=xl.getCellData("Supplier", i, 5);
	String mail=xl.getCellData("Supplier", i, 6);
	String mnumber=xl.getCellData("Supplier", i, 7);
	String notes=xl.getCellData("Supplier", i, 8);
	String result=ERPFunction.verifySupplier(sname, address, city, country, cperson, pnumber, mail, mnumber, notes);
	xl.setCellData("Supplier", i, 9, result, outputpath);
	}
}
@AfterTest
public void tearDown()
{
	ERPFunction.closebrw();
}
}
