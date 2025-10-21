package modules.pages.FindStorePage;

import modules.pages.BasePage.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FindStorePage extends BasePage {
    public FindStorePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "ta-Location_input")
    private WebElement inputLocation;

    @FindBy(xpath = "//section[@class='locator-list border-top']//section[1]")
    private WebElement selectionOfTheFirstLocation;

    @FindBy(xpath = "//li[@class='no-results']")
    private WebElement noStoresNearby;

    public void insertValidLocation(String location) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(inputLocation));
        Thread.sleep(1000);
        inputLocation.click();
        inputLocation.sendKeys(location);
        Thread.sleep(2000);
        inputLocation.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(selectionOfTheFirstLocation));

    }
    public void insertInvalidLocation(String location) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(inputLocation));
        Thread.sleep(1000);
        inputLocation.click();
        inputLocation.sendKeys(location);
        wait.until(ExpectedConditions.visibilityOf(noStoresNearby));
    }
}
