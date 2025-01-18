package testScripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.TestBase;
import pages.CartPage;
import pages.CheckOutPage;
import pages.LoginPage;
import pages.ProductListPage;

public class PlaceOrderTest {
    
    WebDriver driver; // Declare a WebDriver instance to control the browser
    LoginPage loginPage; // Declare a LoginPage instance to interact with the login page elements
    ProductListPage ListPage;
    CartPage cartpage;
    CheckOutPage checkOutPage;

    // Constructor for PlaceOrderTest class
    public PlaceOrderTest() {
        // Initialize WebDriver using TestBase class
        TestBase.initDriver();
        
        // Get the initialized WebDriver instance from TestBase
        driver = TestBase.getDriver();
        
        // Initialize LoginPage with the WebDriver instance
        loginPage = new LoginPage(driver);
        ListPage = new ProductListPage(driver);
        cartpage = new CartPage(driver);
        checkOutPage = new CheckOutPage(driver);
    }
    
    // Method to set up the test environment before any test method runs
    @BeforeTest
    public void setup() {
        // Open the specified URL using the WebDriver
        TestBase.openUrl("https://www.saucedemo.com/");
    }
    
    // Test method to verify the login functionality
    @Test(priority =1)
    public void loginTest() {
        // Perform a valid login action using the LoginPage object
        loginPage.validLogin("standard_user", "secret_sauce");
    }
    
    @Test(priority =2)
    public void addItemTest() {
    	boolean isOnProductsPage = ListPage.isOnProducts();
    	Assert.assertTrue(isOnProductsPage);
    	ListPage.addToCart();
    	ListPage.viewCart();
    	Assert.assertTrue(cartpage.isItemAdded());
    }
    @Test(priority = 3)
    public void checkoutTest() {
    	cartpage.checkoutItems();
    	checkOutPage.checkoutInfo("Ram", "Santhosh", "522006");
    	checkOutPage.checkoutOrder();
    	Assert.assertEquals(checkOutPage.Succeessmsg(), "Thank you for your order!");
    	System.out.println("Testx on last page"+checkOutPage.Succeessmsg());
    	
    	
    }
    
}
