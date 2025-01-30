package e_Com.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import e_Com.utils.Utility;

public class BaseClass {
	public WebDriver driver;
	public Properties prop;
	public Properties dataprop;

	public BaseClass() {
		
		prop = new Properties();
		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\e_Com\\configue\\configue.properties");

		dataprop = new Properties();
		File datafile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\e_Com\\testData\\testData.properties");

		try {
			FileInputStream datafiles = new FileInputStream(datafile);
			dataprop.load(datafiles);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			FileInputStream files = new FileInputStream(file);
			prop.load(files);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public WebDriver initilizeBrowser(String browsername) {
		// String browsername= "chrome"; here we dont need to give this coz already
		// given as an argument

		if (browsername.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility.IMPLICIT_WAIT_TIME));
		return driver;

	}

}
