package oct16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	public static void main(String[] args)throws Throwable {
System.setProperty("webdriver.chrome.driver", "G:\\Selenium_LiveProject\\ERP_StockAccounting\\CommonDrivers\\chromedriver.exe");
WebDriver driver = new ChromeDriver();
driver.get("http://projects.qedgetech.com/webapp/login.php");
driver.manage().window().maximize();
Thread.sleep(5000);
driver.findElement(By.name("btnreset")).click();
driver.findElement(By.name("username")).sendKeys("admin");
driver.findElement(By.name("password")).sendKeys("master");
driver.findElement(By.name("btnsubmit")).click();
Thread.sleep(5000);
driver.close();

	}

}
