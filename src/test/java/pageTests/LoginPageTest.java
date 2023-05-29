package pageTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Listeners;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.ProductsPage;
import util.TestUtil;

@Listeners({ listeners.TestAllureListener.class })
public class LoginPageTest extends BaseClass {
	
	WebDriver driver;
	LoginPage loginPage;
	ProductsPage productsPage;
	TestUtil testUtil;
	Properties prop;

@BeforeMethod
public void setUp() throws MalformedURLException {
		BaseClass baseClass = new BaseClass();
		driver = baseClass.initializeDriver();//launches url as well;
		loginPage = new LoginPage(driver);
		testUtil = new TestUtil(driver);
		prop = baseClass.getProperties();
	
	}
	
  @Test
  public void verifyLoginPageTest() {
	  //productsPage = loginPage.login("standard_user","secret_sauce");
	  productsPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	  String title = productsPage.getProductsPageTitle();
	  Assert.assertEquals(title, "Products", "Login is not successful");
  }
  

  @AfterMethod
  public void tearDown() {
	  driver.quit();
  }


}

