package pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.qameta.allure.Step;

public class ProductsPage {
	
	WebDriver driver;
	//Page Factory - Object Repository
	@FindBy(xpath = "//span[text()='Products']")
	WebElement pageTitle;
	
	@FindBy(id = "react-burger-menu-btn")
	WebElement reactMenuButton;
	
	@FindBy(id= "logout_sidebar_link")
	WebElement logoutLink;
	
	//div[@class='inventory_item_description']")
	
	@FindBy(xpath="//div[@id='root']//a[@class='shopping_cart_link']")
	WebElement button_cart;
	
	
	
	//Initializing the page object using the constructor
	public ProductsPage(WebDriver driver ) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	@Step("Logout from Application from Products Page")
	public LoginPage logout() {
		reactMenuButton.click();
		logoutLink.click();
		return new LoginPage(driver);
	}
	
	@Step("Get title of Products page")
	public String getProductsPageTitle() {
		return pageTitle.getText().trim();
	}
	
	public Map<String,String> getProducts_Names(){
		Map<String,String> map = new HashMap<String,String>();
		List<WebElement> li = driver.findElements(By.xpath("//div[@class='inventory_item_description']"));
		Iterator<WebElement> it = li.iterator();
		int total_Items = li.size();
		
		for(int i = 1; i<=total_Items;i++) {
			WebElement price = driver.findElement(By.xpath("(//div[@class='inventory_item_description'])["+i+"]//div[@class='inventory_item_price']"));
			WebElement name = driver.findElement(By.xpath("(//div[@class='inventory_item_description'])["+i+"]//div[@class='inventory_item_name']"));
			map.put(name.getText(), price.getText());
		}

		return map; 
	}
	
	@Step("In Product page : Selecting a product with name : {0} and adding to Cart : {0} ")
	public boolean selectProduct_AddToCart(String productName) {
		List<WebElement> li = driver.findElements(By.xpath("//div[@class='inventory_item_description']"));
		WebElement addToCart;
		int total_Items = li.size();
		boolean flag = false;
		int itemsSelected = 0;
		WebDriverWait wait =  new WebDriverWait(driver,Duration.ofSeconds(20));
		for(int i = 1; i<=total_Items;i++) {
			WebElement name = driver.findElement(By.xpath("(//div[@class='inventory_item_description'])["+i+"]//div[@class='inventory_item_name']"));
			if(name.getText().equalsIgnoreCase(productName)) {
				addToCart = driver.findElement(By.xpath("(//div[@class='inventory_item_description'])["+i+"]//button"));
				addToCart.click();
				itemsSelected++;
				
				wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='shopping_cart_badge']"), String.valueOf(itemsSelected)));
			}
		}
		
		WebElement cartValue = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
		if((cartValue.getText()).equalsIgnoreCase(String.valueOf(itemsSelected))) {
			flag = true;
		}
		return flag;
	}
	
	@Step("Click on Cart icon")
	public YourCartPage clickOnCart() {
		button_cart.click();
		return new YourCartPage(driver);
	}
	
}
