package modules.pages.Commons;

import modules.pages.BasePage.BasePage;
import modules.pages.CartPage.CartPage;
import modules.pages.FindStorePage.FindStorePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderComponent extends BasePage {

    @FindBy(id = "gn-search-input")
    public WebElement inputSearch;
    @FindBy(xpath = "//a[@aria-label='Nike Home Page']//*[name()='svg']")
    public WebElement nikeLogo;
    @FindBy(xpath = "//a[normalize-space()='MEN']")
    public WebElement menuMen;
    @FindBy(xpath = "//a[contains(text(),'WOMEN')]")
    public WebElement menuWoman;
    @FindBy(xpath = "//a[normalize-space()='KIDS']")
    public WebElement menuKids;
    @FindBy(css = "div:nth-child(1) > div:nth-child(2) > nav:nth-child(1) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")
    public WebElement LinkFindAStore;
    @FindBy(xpath = "//a[@class='interactive-profile-element profile-link']")
    public WebElement LinksignIn;
    @FindBy(xpath = "//a[@title='Bag Items: 1']")
    public WebElement BtnCart;

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public void shouldDisplayNikeLogoWhenWebsiteLoads(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(nikeLogo));
    }

    public ProductGridComponent SearchProduct(String productName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(inputSearch));
        inputSearch.click();
        inputSearch.sendKeys(productName + Keys.ENTER);

        return new ProductGridComponent(driver);
    }

    public FindStorePage FindAStore(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(LinkFindAStore));
        LinkFindAStore.click();
        return new FindStorePage(driver);
    }

    public void MenuMen(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(menuMen));
        menuMen.click();
    }

    public void MenuWoman(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(menuWoman));
        menuWoman.click();
    }

    public void MenuKids(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(menuKids));
        menuKids.click();
    }

    public void SignIn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(LinksignIn));
        LinksignIn.click();
    }

    public CartPage CartMenu(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(BtnCart));

        BtnCart.click();

        return new CartPage(driver);
    }


}
