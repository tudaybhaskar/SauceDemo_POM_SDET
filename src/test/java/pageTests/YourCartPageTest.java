package pageTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.LoginPage;
import pages.ProductsPage;
import pages.YourCartPage;
import util.BrowserDriverType;
import util.CustomJavaUtils;
import util.ExcelUtil;
import util.TestUtil;
import listeners.TestAllureListener;

@Listeners({ listeners.TestAllureListener.class })
@Epic("Product in Your cart page")
@Feature("Your Cart page")
public class YourCartPageTest extends BaseClass  {
	
	WebDriver driver;
	LoginPage loginPage;
	ProductsPage productsPage;
	YourCartPage yourCartPage;
	TestUtil testUtil;
	Properties prop;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		BaseClass baseClass = new BaseClass();
		driver = baseClass.initializeDriver(BrowserDriverType.Chrome);//launches url as well;
		loginPage = new LoginPage(driver);
		testUtil = new TestUtil(driver);
		prop = baseClass.getProperties();
		
	}
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description : Verify Title of the Page : Your Cart ")
	@Test(description = "To verify title of the Page : Your Cart",dataProvider = "getCredentialsTestData")
	public void verifyTitle_YourCartPage(String Username,String Password) {
		//productsPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		productsPage = loginPage.login(Username, Password);
		SoftAssert softAssert  =  new SoftAssert();
		//String product = "Sauce Labs Bike Light";
		String product = prop.getProperty("product");
		productsPage.selectProduct_AddToCart(product);
		yourCartPage = productsPage.clickOnCart();
		String actualtitle = yourCartPage.getPageTitle();
		softAssert.assertEquals(actualtitle, "Your Car", "Your cart page is not displayed");
		softAssert.assertAll();
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description : Adding a Product to Cart and view in Cart page")
	@Test(description = "Adding product to Cart",dataProvider = "getCredentialsTestData")
	public void verify_Product_In_Cart(String Username,String Password) {
		productsPage = loginPage.login(Username, Password);
		SoftAssert softAssert  =  new SoftAssert();
		//String product = "Sauce Labs Bike Light";
		String product = prop.getProperty("product");
		productsPage.selectProduct_AddToCart(product);
		yourCartPage = productsPage.clickOnCart();
		String productFromCart = yourCartPage.getProduct_From_Cart();
		//ExcelUtil.writeDataToExcel("UserCredentials","verify_Product_In_Cart");
		softAssert.assertEquals(productFromCart, product,"Product : " + product +" is not added to Cart");
		softAssert.assertAll();	
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Description : Removal of Product from From Cart")
	@Test(description = "Removing product from Cart", dataProvider = "getCredentialsTestData")
	public void verify_removeProduct_From_CartPage(String Username, String Password) {
		productsPage = loginPage.login(Username, Password);
		SoftAssert softAssert  =  new SoftAssert();
		//String product = "Sauce Labs Bike Light";
		String product = prop.getProperty("product");
		productsPage.selectProduct_AddToCart(product);
		yourCartPage = productsPage.clickOnCart();
		String productFromCart = yourCartPage.getProduct_From_Cart();
		boolean actual = yourCartPage.removeProduct_From_Cart(productFromCart);
		softAssert.assertEquals(actual, true, "Product from cart is not removed");
		softAssert.assertAll();
	}
	
	@DataProvider
	public Object[][] getCredentialsTestData() {
		Object[][] data = ExcelUtil.getTestData("UserCredentials");
		System.out.println(data);
		return data;
	}
	

	@AfterMethod
	  public void tearDown() {
		driver.quit();	
	  }
}

