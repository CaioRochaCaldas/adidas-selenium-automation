package modules.pages.CartPage;

import modules.pages.BasePage.BasePage;
import modules.pages.CheckoutPage.CheckoutPage;
import org.junit.jupiter.api.Assertions;
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
    public WebElement BtnDeleteProduct;

    @FindBy(xpath = "//a[normalize-space()='Checkout']")
    public WebElement BtnCheckout;

    @FindBy(xpath = "//button[@aria-label='Increment quantity']")
    public WebElement BtnAddProduct;

    @FindBy(xpath = "//button[@aria-label='Decrement quantity']")
    public WebElement BtnDecrementProduct;

    @FindBy(xpath = "//div[@aria-label='Quantity']")
    public WebElement productQuantity;

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
        wait.until(ExpectedConditions.elementToBeClickable(BtnDeleteProduct));
        BtnDeleteProduct.click();
        wait.until(ExpectedConditions.visibilityOf(textCartIsEmpty));
    }

    public void UpdateProductFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(BtnAddProduct));
        wait.until(ExpectedConditions.visibilityOf(productQuantity));
        Assertions.assertEquals(productQuantity,"1");
        BtnAddProduct.click();
        wait.until(ExpectedConditions.visibilityOf(productQuantity));
        Assertions.assertEquals(productQuantity,"2");
        BtnDecrementProduct.click();
        wait.until(ExpectedConditions.visibilityOf(productQuantity));
        Assertions.assertEquals(productQuantity,"1");


    }
}
