package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERPFunction {
public static WebDriver driver;
//method for lauching url in a browser
public static String verifyUrl(String url)throws Throwable
{
	String res="";
System.setProperty("webdriver.chrome.driver", "G:\\Selenium_LiveProject\\ERP_StockAccounting\\CommonDrivers\\chromedriver.exe");
driver=new ChromeDriver();
driver.get(url);
driver.manage().window().maximize();
Thread.sleep(5000);
if(driver.findElement(By.name("btnsubmit")).isDisplayed())
{
	res="App Launch Success";
}
else
{
	res="App Launch Fail";
}
return res;
}
//method for Login
public static String verifyLogin(String username,String password)throws Throwable
{
	String res="";
	WebElement user=driver.findElement(By.name("username"));
	user.clear();
	Thread.sleep(4000);
	user.sendKeys(username);
	Thread.sleep(4000);
	WebElement pass=driver.findElement(By.name("password"));
	pass.clear();
	Thread.sleep(4000);
	pass.sendKeys(password);
	Thread.sleep(4000);
	driver.findElement(By.name("btnsubmit")).click();
if(driver.findElement(By.xpath("(//a[contains(text(),'Log')])[3]")).isDisplayed())
{
	res="Login Success";
}
else
{
	res="Login Fail";
}
return res;
}
//method for suplier creation
public static String verifySupplier(String sname,String address,String city,String country,
	String cperson,String pnumber,String email,String mnumber,String notes)throws Throwable
{
	String res="";
	//Click on supplier link
driver.findElement(By.xpath("//li[@id='mi_a_suppliers']//a")).click();
Thread.sleep(5000);
//click on add
driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
Thread.sleep(5000);
//get snumber id 
String snumber=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
driver.findElement(By.name("x_Supplier_Name")).sendKeys(sname);
driver.findElement(By.name("x_Address")).sendKeys(address);
driver.findElement(By.name("x_City")).sendKeys(city);
driver.findElement(By.name("x_Country")).sendKeys(country);
driver.findElement(By.name("x_Contact_Person")).sendKeys(cperson);
driver.findElement(By.name("x_Phone_Number")).sendKeys(pnumber);
driver.findElement(By.name("x__Email")).sendKeys(email);
driver.findElement(By.name("x_Mobile_Number")).sendKeys(mnumber);
driver.findElement(By.name("x_Notes")).sendKeys(notes);
driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
Thread.sleep(5000);
driver.findElement(By.xpath("//button[text()='OK!']")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("(//button[text()='OK'])[6]")).click();
Thread.sleep(5000);
//verify search textbox
if(!driver.findElement(By.name("psearch")).isDisplayed())
//click on search panel button
driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
driver.findElement(By.name("psearch")).clear();
driver.findElement(By.name("psearch")).sendKeys(snumber);
driver.findElement(By.name("btnsubmit")).click();
Thread.sleep(5000);
//capture snumber from suppler table
String act_number=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
Thread.sleep(5000);
System.out.println(snumber+"     "+act_number);
if(snumber.equals(act_number))
{
	res="Pass";
}
else
{
	res="Fail";
}
return res;
}
public static void closebrw()
{
	driver.close();
}
}
















