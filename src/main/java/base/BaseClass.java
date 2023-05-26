package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
		WebDriver driver;
		Properties prop;
		String hubURL = "http://192.168.1.5:4444";
		
		private static ThreadLocal<WebDriver> tdriver =  new ThreadLocal<WebDriver>();
		
		public WebDriver initializeDriver() throws MalformedURLException{
			try {
				prop = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/config/config.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String browser = prop.getProperty("browser");
		
			if (browser.equals("chrome")) {
				DesiredCapabilities cap =  new DesiredCapabilities();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.MAC);
				//driver = new ChromeDriver();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				options.merge(cap);
				
				//WebDriverManager.chromedriver().setup();
				driver = new RemoteWebDriver(new URL(hubURL),options);
				
			} else if (browser.equals("safari")) {
				WebDriverManager.getInstance(SafariDriver.class).setup();
				driver = new SafariDriver();
				
		
			} else {
				System.out.println("Please pass the correct browser name....");
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			
			driver.get(prop.getProperty("url"));
			tdriver.set(driver);
			return getDriver();
			
		}
		
		public static synchronized WebDriver getDriver() {
			return tdriver.get();
		}
		
		public void setProperty(String Key, String value) {
			prop.setProperty(Key, value);
		}
		
		public String getProperty(String key) {
			return prop.getProperty(key);
		}
		
		public Properties getProperties() {
			return prop;
		}

}
