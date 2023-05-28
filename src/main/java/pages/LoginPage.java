package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class LoginPage {
	
	WebDriver driver;
	// Page Factory - Object Repository
	@FindBy(id = "user-name")
	WebElement userName;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "login-button")
	WebElement loginButton;
	
	//Initializing the Page objects using the constructors
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	@Step("login with {0} and {1}")
	public ProductsPage login(String un, String pwd) {
		
		userName.sendKeys(un);
		password.sendKeys(pwd);
		loginButton.click();
		
		return new ProductsPage(driver);
	}
}

