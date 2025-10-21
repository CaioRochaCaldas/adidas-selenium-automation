package modules.pages.Commons;

import modules.pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductGridComponent extends BasePage {

    @FindBy(xpath = "//h1[@class='heading_title__iF1Ox']")
    public WebElement titleMen;

    @FindBy(xpath = "//h1[@class='heading_title__iF1Ox']")
    public WebElement titleWoman;

    @FindBy(xpath = "//h1[@class='heading_title__iF1Ox']")
    public WebElement titleKids;

    @FindBy(xpath = "//span//div[@aria-label='Gender']")
    public WebElement dropGender;

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

    public ProductGridComponent(WebDriver driver) {
        super(driver);
    }

    public void MenGrid(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(titleMen));
        assertEquals(titleMen.getText(),"Men's Sneakers and Workout Clothes");
    }
    public void WomenGrid(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(titleWoman));
        assertEquals(titleMen.getText(),"Women's Sneakers and Workout Clothes");
    }
    public void KidsGrid(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(titleKids));
        assertEquals(titleMen.getText(),"Kids' Shoes and Activewear");
    }
    public void filterSearch() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dropColor));
        dropColor.click();
        wait.until(ExpectedConditions.visibilityOf(colorBlack));
        colorBlack.click();

    }
    public void SelectShoes() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productFromGrid));
        productFromGrid.click();

    }
}
