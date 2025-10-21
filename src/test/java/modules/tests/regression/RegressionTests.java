package modules.tests.regression;


import jdk.jfr.Description;
import modules.others.RetryExtension;
import modules.pages.CartPage.CartPage;
import modules.pages.Commons.HeaderComponent;
import modules.pages.Commons.Modals;
import modules.pages.Commons.ProductGridComponent;
import modules.pages.FindStorePage.FindStorePage;
import modules.pages.HomePage.HomePage;
import modules.pages.ProductPage.ProductPage;
import modules.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegressionTests {

    private WebDriver driver;

    @RegisterExtension
    static RetryExtension retryExtension = new RetryExtension();
    static {
        RetryExtension.setMaxRetries(0);
    }

    @Test
    @Description("""
    Given the user opens the browser and accesses the website
    When the user searches for a product
    And selects a product from the grid listing
    And chooses a shoe model and size
    And adds the product to the cart
    And navigates to the cart page
    Then the system should not display the message "Your cart is empty" if no product was successfully added
    """)
    @DisplayName("User can add a product to the shopping cart")
    public void AddProductToCartTest() throws InterruptedException , IOException{

        String productName = "jordan";

        driver = DriverFactory.getDriverDev("edge");
        new HomePage(driver).AcessHomepage();
        new Modals(driver).AcceptCurrentLocation();
        new HeaderComponent(driver).SearchProduct(productName);
        new ProductGridComponent(driver).SelectShoes();
        new ProductPage(driver).AddShoesToCart();
        new Modals(driver).AddedToCartWithSuccess();
        new HeaderComponent(driver).CartMenu();
        new CartPage(driver).CheckIfCartIsEmpty();
    }

    @Test
    @Description("""
    Given the user opens the browser and accesses the website
    When the user searches for a product
    And selects a product from the grid listing
    And chooses a shoe model and size
    And adds the product to the cart
    And navigates to the cart page
    And removes the product from the cart
    Then the system should display the message "Your cart is empty" after the product is removed
    """)
    @DisplayName("Verify that a product can be removed from the cart")
    public void RemoveProductFromCartTest() throws InterruptedException , IOException{
        String productName = "jordan";

        driver = DriverFactory.getDriverDev("edge");
        new HomePage(driver).AcessHomepage();
        new Modals(driver).AcceptCurrentLocation();
        new HeaderComponent(driver).SearchProduct(productName);
        new ProductGridComponent(driver).SelectShoes();
        new ProductPage(driver).AddShoesToCart();
        new Modals(driver).AddedToCartWithSuccess();
        new HeaderComponent(driver).CartMenu();
        new CartPage(driver).RemoveProductFromCart();
    }

    @Test
    @Description("""
    Given the user opens the browser and accesses the website
    When the user searches for a product
    And applies the black color filter
    Then the application should display products matching the selected filters
    """)
    @DisplayName("Search for a product and apply the color filter")
    public void SearchProductAndApplyFilterTest() throws InterruptedException , IOException{

        String productName = "Clothing";
        driver = DriverFactory.getDriverDev("edge");
        new HomePage(driver).AcessHomepage();
        new HeaderComponent(driver).SearchProduct(productName);
        new ProductGridComponent(driver).filterSearch();
        Thread.sleep(50000);
    }

    @Test
    @Description("""
    Given the user opens the browser and accesses the website
    When the user selects the "Find a Store" menu
    And enters a valid address
    Then the application should display store locations for the selected area
    """)
    @DisplayName("Store locations are displayed when a valid address is provided")
    public void FindStoreByValidAddressTest() throws InterruptedException , IOException{

        String location="New York, NY, United States";

        driver = DriverFactory.getDriverDev("edge");
        new HomePage(driver).AcessHomepage();
        new Modals(driver).AcceptCurrentLocation();
        new HeaderComponent(driver).FindAStore();
        new FindStorePage(driver).insertValidLocation(location);
    }

    @Test
    @Description("""
    Given the user opens the browser and accesses the website
    When the user selects the "Find a Store" menu
    And enters an invalid address
    Then the application should display a message indicating no stores were found
    """)
    @DisplayName("Search for a store using an invalid address")
    public void FindStoreByInvalidAddressTest() throws InterruptedException , IOException{

        String location="Zerzura";

        driver = DriverFactory.getDriverDev("edge");
        new HomePage(driver).AcessHomepage();
        new Modals(driver).AcceptCurrentLocation();
        new HeaderComponent(driver).FindAStore();
        new FindStorePage(driver).insertInvalidLocation(location);
    }

    @AfterEach
    public void AfterEach() throws InterruptedException, IOException {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH-mm-ss").format(new Date());
        String destino = "evidences/regression/ " + timestamp + ".png";
        Files.copy(screenshot.toPath(), Paths.get(destino));

        if (driver != null) {
            driver.quit();
        }

    }
}
