package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
public static String getValueForKey(String key)throws Throwable
{
Properties confingProperties =new Properties();
confingProperties.load(new FileInputStream(new File("G:\\Selenium_LiveProject\\ERP_StockAccounting\\PropertyFile\\Environment.properties")));
return confingProperties.getProperty(key);
}
}
