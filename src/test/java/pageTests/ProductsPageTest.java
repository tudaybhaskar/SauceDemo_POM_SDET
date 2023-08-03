package pageTests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
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
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.LoginPage;
import pages.ProductsPage;
import util.BrowserDriverType;
import util.CustomJavaUtils;
import util.TestUtil;


@Listeners({ listeners.TestAllureListener.class })
public class ProductsPageTest extends BaseClass {
	
	WebDriver driver;
	LoginPage loginPage;
	ProductsPage productsPage;
	TestUtil testUtil;
	Properties prop;
	
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		BaseClass baseClass = new BaseClass();
		driver = baseClass.initializeDriver(BrowserDriverType.Chrome);//launches url as well;
		loginPage = new LoginPage(driver);
		testUtil = new TestUtil(driver);
		//productsPage = loginPage.login("standard_user","secret_sauce");
		prop = baseClass.getProperties();
		productsPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
  @Test
  @Description("Test case description: Verify Products page title")// coming from allure
  @Severity(SeverityLevel.NORMAL)
  public void verifyProductsPageTitle() {
	  String title = productsPage.getProductsPageTitle();
	  Assert.assertEquals(title, "Products", "ProductsPage is not displayed");
  }
  
  @Test
  @Description("Test case description: Verify Product Bolt Tt-shirt is present")// coming from allure
  @Severity(SeverityLevel.BLOCKER)
  public void verifyProductIsPresent() {
	  Map<String,String> map = productsPage.getProducts_Names();
	  CustomJavaUtils.printMap(map);
	  boolean actual = CustomJavaUtils.verifyItemFromMapKey(map, "Sauce Labs Bolt T-Shirt");
	  Assert.assertEquals(actual, true,"Sauce Labs Bolt T-Shirt is not present");
  }
  
  @Test
  @Description("Test case description: Verify Product addition to cart")// coming from allure
  @Severity(SeverityLevel.MINOR)
  public void addProductToCart() {
	  boolean actual = productsPage.selectProduct_AddToCart("Sauce Labs Bike Light");
	  Assert.assertEquals(actual, true, "Product : Sauce Labs Bike Light is not added");
  }
  
  @AfterMethod
  public void tearDown() {
	  driver.quit();
  }
}

