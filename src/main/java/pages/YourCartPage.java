package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class YourCartPage {
	
	WebDriver driver;
	//Page Factory - Object Repository
	@FindBy(xpath = "//a[@class='shopping_cart_link']" )
	WebElement button_cart;
	
	@FindBy(xpath = "//span[@class='title']" )
	WebElement title_YourCart;
	
	@FindBy(xpath = "//div[@class='cart_list']" )
	WebElement cartList;
	

	@FindBy(id = "react-burger-menu-btn")
	WebElement reactMenuButton;
	
	@FindBy(id= "logout_sidebar_link")
	WebElement logoutLink;
	
	//Initializing the page objects using the constructor
	public YourCartPage(WebDriver driver ) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver; 
	
	//Actions
	public String getPageTitle() {
		String title = title_YourCart.getText();
		return title;
	}
	
	@Step("In your cart page, getting the product that is added in test execution")
	public String getProduct_From_Cart() {
		String productName = cartList.findElement(By.xpath( "//div[@class='inventory_item_name']" )).getText();
		return productName;
	}
	
	@Step("In YourCart page, clicking on remove button , removes the product from cart is : {0}")
	public boolean removeProduct_From_Cart(String productName) {
		boolean flag_removed =  false;
		WebElement cartValue;
		WebDriverWait wait =  new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement remove_button;
		String xpath_RemoveButton = "//div[@class='inventory_item_name' and text()='"+productName+"']/parent::a/following-sibling::div[@class='item_pricebar']/button";
		remove_button = driver.findElement(By.xpath(xpath_RemoveButton));
		//remove_button.click();
		
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath_RemoveButton)));
			flag_removed = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag_removed;
		
	}

	public void logoutFromApp() {
		
		js.executeScript("arguments[0].click();", reactMenuButton);
		js.executeScript("arguments[0].click();", logoutLink);
	}

}

