package modules.pages.SignInPage;

import modules.pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space()='Enter your email to join us or sign in.']")
    private WebElement mainTitle;

    @FindBy(id = "username")
    private WebElement inputEmail;


    public String validateSignInPageUIElements(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(inputEmail));
        wait.until(ExpectedConditions.visibilityOf(mainTitle));
        return  mainTitle.getText();

    }

}
