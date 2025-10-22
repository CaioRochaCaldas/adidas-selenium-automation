package modules.pages.Commons;

import modules.pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductResults extends BasePage {

    @FindBy(xpath = "//span//div[@aria-label='Color']")
    public WebElement dropColor;

    @FindBy(xpath = "//button[@aria-label='Filter for Black']")
    public WebElement colorBlack;

    @FindBy(xpath = "//a[contains(@aria-label,'Filter for Women')]")
    public WebElement btnWomen;

    @FindBy(xpath = "//a[@aria-label='Filter for Men']")
    public WebElement btnMen;

    @FindBy(xpath = "//a[normalize-space()='Tops and T-Shirts']")
    public WebElement btnTShirts;

    @FindBy(xpath = "//a[@aria-label='Air Jordan 1 Retro High OG \"Pro Green\"']")
    public WebElement productFromGrid;

    @FindBy(css = "div:nth-child(3) header:nth-child(1) div:nth-child(1) h1:nth-child(1) span:nth-child(2)")
    public WebElement textSearchResults;

    public ProductResults(WebDriver driver) {
        super(driver);
    }


    public void filterSearch() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dropColor));
        dropColor.click();
        wait.until(ExpectedConditions.visibilityOf(colorBlack));
        colorBlack.click();
    }

    public String productSearchResults(String productName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(textSearchResults));
        return textSearchResults.getText();
    }



    public void SelectShoes() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productFromGrid));
        productFromGrid.click();

    }
}
