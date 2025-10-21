package modules.pages.Commons;

import modules.pages.BasePage.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Modals extends BasePage {
    public Modals(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id=\"modal-root\"]/div/div/div/div/section/div[2]/button")
    public WebElement btnUSA;


    @FindBy(xpath = "/div/button[2]")
    public WebElement btnCheckoutAfterAdd;

    @FindBy(xpath = "//button[@class='nds-btn nds-button--icon-only modal-ui-close css-1pto8ls ex41m6f0 btn-secondary-dark ']")
    public WebElement btnCloseAddToCartModal;

    @FindBy(xpath = "//*[@id=\"modal-root\"]/div/div/div/div/div/section/div[1]/div[3]/p")
    public WebElement textPaymentError;

    public void AcceptCurrentLocation(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(btnUSA));
        btnUSA.click();
        wait.until(ExpectedConditions.invisibilityOf(btnUSA));
    }
    public void AddedToCartWithSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(btnCloseAddToCartModal));
        wait.until(ExpectedConditions.invisibilityOf(btnCloseAddToCartModal));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    public void PaymentErrorMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(textPaymentError));
    }

}
