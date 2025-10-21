package modules.tests.e2e;
import jdk.jfr.Description;
import modules.others.RetryExtension;
import modules.pages.CartPage.CartPage;
import modules.pages.CheckoutPage.CheckoutPage;
import modules.pages.Commons.HeaderComponent;
import modules.pages.Commons.Modals;
import modules.pages.Commons.ProductGridComponent;
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

public class e2eTests {

    private WebDriver driver;

    @RegisterExtension
    static RetryExtension retryExtension = new RetryExtension();
    static {
        RetryExtension.setMaxRetries(0);
    }
    //chrome
    @Test
    @Description("""
    E2E Scenario:
    Given the user is browsing on Chrome
    When the user searches for a product
    And selects the product from the search results
    And adds the product to the cart
    And proceeds to checkout
    And confirms the payment
    Then the system should display an order confirmation page indicating a successful purchase
    """)
    @DisplayName("E2E: User searches, selects, and purchases a product successfully on Chrome browser")
    public void AddProductToCartOnChromeTest() throws InterruptedException , IOException{

        String productName = "jordan";
        String email = "test@gmail.com";
        String firstName = "Nathan";
        String lastName = "Drake";
        String adress = "Howard Hughes Parkway";
        String city = "Las Vegas";
        String postalCode = "89169";
        String phoneNumber = "1856266170";
        String cardNumber= "4274904482437567";
        String cardMMYY= "1129";
        String cardCVV= "383";

        driver = DriverFactory.getDriverDev("chrome");
        new HomePage(driver).AcessHomepage();
        new Modals(driver).AcceptCurrentLocation();
        new HeaderComponent(driver).SearchProduct(productName);
        new ProductGridComponent(driver).SelectShoes();
        new ProductPage(driver).AddShoesToCart();
        new Modals(driver).AddedToCartWithSuccess();
        new HeaderComponent(driver).CartMenu();
        new CartPage(driver).CheckIfCartIsEmpty();
        new CartPage(driver).GoToCheckout();
        new CheckoutPage(driver).Checkout(email,firstName,lastName,adress,city,postalCode,phoneNumber,cardNumber,cardMMYY,cardCVV);
        new Modals(driver).PaymentErrorMessage();
    }

    //firefox
    @Test
    @Description("""
    E2E Scenario:
    Given the user is browsing on Firefox
    When the user searches for a product
    And selects the product from the search results
    And adds the product to the cart
    And proceeds to checkout
    And confirms the payment
    Then the system should display an order confirmation page indicating a successful purchase
    """)
    @DisplayName("E2E: User searches, selects, and purchases a product successfully on Chrome browser")
    public void AddProductToCartOnFirefoxTest() throws InterruptedException , IOException{

        String productName = "jordan";
        String email = "test@gmail.com";
        String firstName = "Nathan";
        String lastName = "Drake";
        String adress = "Howard Hughes Parkway";
        String city = "Las Vegas";
        String postalCode = "89169";
        String phoneNumber = "1856266170";
        String cardNumber= "4274904482437567";
        String cardMMYY= "1129";
        String cardCVV= "383";

        driver = DriverFactory.getDriverDev("firefox");
        new HomePage(driver).AcessHomepage();
        new Modals(driver).AcceptCurrentLocation();
        new HeaderComponent(driver).SearchProduct(productName);
        new ProductGridComponent(driver).SelectShoes();
        new ProductPage(driver).AddShoesToCart();
        new Modals(driver).AddedToCartWithSuccess();
        new HeaderComponent(driver).CartMenu();
        new CartPage(driver).CheckIfCartIsEmpty();
        new CartPage(driver).GoToCheckout();
        new CheckoutPage(driver).Checkout(email,firstName,lastName,adress,city,postalCode,phoneNumber,cardNumber,cardMMYY,cardCVV);
        new Modals(driver).PaymentErrorMessage();
    }

    //edge
    @Test
    @Description("""
    E2E Scenario:
    Given the user is browsing on Edge
    When the user searches for a product
    And selects the product from the search results
    And adds the product to the cart
    And proceeds to checkout
    And confirms the payment
    Then the system should display an order confirmation page indicating a successful purchase
    """)
    @DisplayName("E2E: User searches, selects, and purchases a product successfully on Chrome browser")
    public void AddProductToCartOnEdgeTest() throws InterruptedException , IOException{

        String productName = "jordan";
        String email = "test@gmail.com";
        String firstName = "Nathan";
        String lastName = "Drake";
        String adress = "Howard Hughes Parkway";
        String city = "Las Vegas";
        String postalCode = "89169";
        String phoneNumber = "1856266170";
        String cardNumber= "4274904482437567";
        String cardMMYY= "1129";
        String cardCVV= "383";

        driver = DriverFactory.getDriverDev("edge");
        new HomePage(driver).AcessHomepage();
        new Modals(driver).AcceptCurrentLocation();
        new HeaderComponent(driver).SearchProduct(productName);
        new ProductGridComponent(driver).SelectShoes();
        new ProductPage(driver).AddShoesToCart();
        new Modals(driver).AddedToCartWithSuccess();
        new HeaderComponent(driver).CartMenu();
        new CartPage(driver).CheckIfCartIsEmpty();
        new CartPage(driver).GoToCheckout();
        new CheckoutPage(driver).Checkout(email,firstName,lastName,adress,city,postalCode,phoneNumber,cardNumber,cardMMYY,cardCVV);
        new Modals(driver).PaymentErrorMessage();
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
