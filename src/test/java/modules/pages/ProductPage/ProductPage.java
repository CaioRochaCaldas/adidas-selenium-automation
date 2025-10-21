package modules.pages.ProductPage;

import modules.pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//button[normalize-space()='Add to Bag']")
    public WebElement btnAddtoBag;

    @FindBy(xpath = "//label[normalize-space()='W 5 / M 3.5']")
    public WebElement btnSize;

    @FindBy(xpath = "//div[@class='mb8-sm']//a[1]")
    public WebElement firstProductVariant;

    public void AddShoesToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstProductVariant));
        firstProductVariant.click();
        wait.until(ExpectedConditions.visibilityOf(btnSize));
        btnSize.click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(btnAddtoBag));
        btnAddtoBag.click();
    }
}
