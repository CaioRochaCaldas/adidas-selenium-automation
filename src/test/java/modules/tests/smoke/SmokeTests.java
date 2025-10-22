package modules.tests.smoke;

import jdk.jfr.Description;
import modules.others.RetryExtension;
import modules.pages.CartPage.CartPage;
import modules.pages.Commons.HeaderComponent;
import modules.pages.Commons.Modals;
import modules.pages.Commons.ProductResults;
import modules.pages.HomePage.HomePage;
import modules.pages.ProductPage.ProductPage;
import modules.pages.SignInPage.SignInPage;
import modules.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmokeTests {
    private WebDriver driver;

    @RegisterExtension
    static RetryExtension retryExtension = new RetryExtension();
    static {
        RetryExtension.setMaxRetries(0);
    }

    /**
     * Author: Caio Rocha Caldas
     * GitHub: https://github.com/CaioRochaCaldas/nike-selenium-automation
     * LinkedIn: https://www.linkedin.com/in/caio-rocha-caldas-49970b1b2/?locale=en_US
     * Version: 1.0
     * Description: This Smoke Tests file contains automated tests that quickly verify
     * the core functionalities of the Nike website. These tests ensure that the main
     * flows, such as accessing the homepage, acessig the loginpage, add product to cart ,performing a product search and checking key UI elements, are working as expected.
     * The goal is to catch critical issues early before running full regression tests.
     */

    @Test
    @Description("""
    Given the user opens the browser
    When the user navigates to "https://www.nike.com"
    And the homepage finishes loading
    Then the Nike logo should be visible in the header, confirming that the website has successfully loaded and is online.
    """)
    @DisplayName("Verify that the Nike logo is displayed when the website loads")
    public void NikeLogoDisplayedOnLoadTest() throws InterruptedException , IOException{

        driver = DriverFactory.getDriverDev("edge");
        new HomePage(driver).AcessHomepage();
        new HeaderComponent(driver).shouldDisplayNikeLogoWhenWebsiteLoads();
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

        driver = DriverFactory.getDriverDev("firefox");
        new HomePage(driver).AcessHomepage();
        new Modals(driver).AcceptCurrentLocation();
        new HeaderComponent(driver).SearchProduct(productName);
        new ProductResults(driver).SelectShoes();
        new ProductPage(driver).AddShoesToCart();
        new Modals(driver).AddedToCartWithSuccess();
        new HeaderComponent(driver).CartMenu();
        new CartPage(driver).CheckIfCartIsEmpty();
    }

    @Test
    @Description("""
    Given the user opens the browser
    When the user navigates to "https://www.nike.com"
    Then the search field should be visible on the homepage
    When the user types a valid product name into the search field
    And presses Enter
    Then the system should display results related to the searched product
    """)
    @DisplayName("The search field is visible and accepts typing")
    public void VerifySearchFieldIsVisibleAndFunctionalTest() throws InterruptedException , IOException{

        String productName = "air max";
        driver = DriverFactory.getDriverDev("edge");
        new HomePage(driver).AcessHomepage();
        new HeaderComponent(driver).SearchProduct(productName);
        String text = new ProductResults(driver).productSearchResults(productName);
        Assertions.assertEquals(productName,text);
    }

    @Test
    @Description("""
    Given the user opens the browser
    When the user navigates to "https://www.nike.com"
    Then the login button should be visible on the homepage
    When the user clicks the login button
    Then the system should redirect the user to the sign-in page
    And the login form should be displayed
    """)
    @DisplayName("The \"Sign In\" button opens the login page.")
    public void VerifyLoginButtonOpensTheLoginPageTest() throws InterruptedException , IOException{

        String pageTitle="Enter your email to join us or sign in.";

        driver = DriverFactory.getDriverDev("edge");
        new HomePage(driver).AcessHomepage();
        new HeaderComponent(driver).SignIn();
        String text = new SignInPage(driver).validateSignInPageUIElements();
        Assertions.assertEquals(pageTitle,text);
    }

    @AfterEach
    public void AfterEach() throws InterruptedException, IOException {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH-mm-ss").format(new Date());
        String destino = "evidences/smoke/ " + timestamp + ".png";
        Files.copy(screenshot.toPath(), Paths.get(destino));

        if (driver != null) {
            driver.quit();
        }

    }
}
