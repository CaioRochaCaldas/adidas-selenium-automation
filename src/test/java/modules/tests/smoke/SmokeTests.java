package modules.tests.smoke;

import jdk.jfr.Description;
import modules.others.RetryExtension;
import modules.pages.Commons.HeaderComponent;
import modules.pages.HomePage.HomePage;
import modules.pages.SignInPage.SignInPage;
import modules.utils.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
     */

    @Test
    @Description("Verifies that the Nike logo is displayed correctly when the homepage is loaded, ensuring brand visibility and proper page rendering.")
    @DisplayName("Verify that the Nike logo is displayed when the website loads")
    public void NikeLogoDisplayedOnLoadTest() throws InterruptedException , IOException{

        driver = DriverFactory.getDriverDev("firefox");
        new HomePage(driver).AcessHomepage();
        new HeaderComponent(driver).shouldDisplayNikeLogoWhenWebsiteLoads();
    }

    @Test
    @Description("Ensures that the search field is visible on the page and accepts user input, verifying basic search functionality.")
    @DisplayName("The search field is visible and accepts typing")
    public void VerifySearchFieldIsVisibleAndFunctionalTest() throws InterruptedException , IOException{

        String productName = "air max";
        driver = DriverFactory.getDriverDev("edge");
        new HomePage(driver).AcessHomepage();
        new HeaderComponent(driver).SearchProduct(productName);
    }

    @Test
    @Description("Verifies that clicking the \"Sign In\" button opens the login page, ensuring users can access the authentication flow.")
    @DisplayName("The \"Sign In\" button opens the login page.")
    public void verifyLoginButtonOpensLoginPageTest() throws InterruptedException , IOException{

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
