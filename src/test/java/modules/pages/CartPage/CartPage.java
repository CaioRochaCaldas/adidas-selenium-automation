package modules.pages.CartPage;

import modules.pages.BasePage.BasePage;
import modules.pages.CheckoutPage.CheckoutPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[@class='css-pm6c6 e14faaxk0']")
    public WebElement textCartIsEmpty;

    @FindBy(xpath = "//button[@aria-label='Remove']")
    public WebElement BtnRemoveProduct;

    @FindBy(xpath = "//a[normalize-space()='Checkout']")
    public WebElement BtnCheckout;

    public void CheckIfCartIsEmpty(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(textCartIsEmpty));
    }

    public CheckoutPage GoToCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(BtnCheckout));
        BtnCheckout.click();
        return new CheckoutPage(driver);
    }

    public void RemoveProductFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(BtnRemoveProduct));
        BtnRemoveProduct.click();
        wait.until(ExpectedConditions.visibilityOf(textCartIsEmpty));
    }
}
