package pageTests;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;

import pages.LoginPage;
import pages.ProductsPage;
import util.CustomJavaUtils;
import util.TestUtil;

public class ProductsPageTest extends BaseClass {
	
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
		//productsPage = loginPage.login("standard_user","secret_sauce");
		prop = baseClass.getProperties();
		productsPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
  @Test
  public void verifyProductsPageTitle() {
	  String title = productsPage.getProductsPageTitle();
	  Assert.assertEquals(title, "Products", "ProductsPage is not displayed");
  }
  
  @Test
  public void verifyProductIsPresent() {
	  Map<String,String> map = productsPage.getProducts_Names();
	  CustomJavaUtils.printMap(map);
	  boolean actual = CustomJavaUtils.verifyItemFromMapKey(map, "Sauce Labs Bolt T-Shirt");
	  Assert.assertEquals(actual, true,"Sauce Labs Bolt T-Shirt is not present");
  }
  
  @Test
  public void addProductToCart() {
	  boolean actual = productsPage.selectProduct_AddToCart("Sauce Labs Bike Light");
	  Assert.assertEquals(actual, true, "Product : Sauce Labs Bike Light is not added");
  }
  
  @AfterMethod
  public void tearDown() {
	  driver.quit();
  }
}
