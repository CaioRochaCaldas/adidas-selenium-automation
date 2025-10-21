package modules.pages.CheckoutPage;

import modules.pages.BasePage.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutPage  extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    public WebElement inputEmail;

    @FindBy(id = "firstName")
    public WebElement inputFirstName;

    @FindBy(id = "lastName")
    public WebElement inputLastName;

    @FindBy(id = "address1")
    public WebElement inputAddress;

    @FindBy(id = "city")
    public WebElement inputCity;

    @FindBy(id = "postalCode")
    public WebElement inputPostalCode;

    @FindBy(id = "state")
    public WebElement inputState;

    @FindBy(xpath = "//option[@value='NV']")
    public WebElement inputStateSelect;

    @FindBy(id = "addressSuggestionOptOut")
    public WebElement enterAdressManually;

    @FindBy(id = "phoneNumber")
    public WebElement inputPhoneNumber;

    @FindBy(xpath = "//*[@id=\"tunnelPage\"]/div[2]/div/div/div[2]/div[2]/button")
    public WebElement btnGuessCheckout;

    @FindBy(xpath = "//button[normalize-space()='Save & Continue']")
    public WebElement btnSaveContinue;

    @FindBy(xpath = "//button[normalize-space()='Continue to Payment']")
    public WebElement btnContinueToPayment;

    @FindBy(id = "creditDebit")
    public WebElement radioCreditOrDebit;

    @FindBy(id = "creditCardNumber")
    public WebElement inputCartNumber;

    @FindBy(id = "expirationDate")
    public WebElement inputMMYY;

    @FindBy(id = "cvNumber")
    public WebElement inputCVV;

    @FindBy(xpath = "//*[@id=\"payment\"]/div[1]/div[2]/div/div[7]/button")
    public WebElement btnContinuetoOrderReview;

    @FindBy(xpath = "//*[@id=\"orderreview\"]/div[1]/div/div[2]/div/section[2]/div/button")
    public WebElement btnSubmitePayment;

    public void Checkout(String email,String firstName,String lastName,String adress,String city,String postalCode,String phoneNumber,String cardNumber,String cardMMYY,String cardCVV) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(btnGuessCheckout));
        btnGuessCheckout.click();

        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.click();
        inputEmail.sendKeys(email);

        inputFirstName.click();
        inputFirstName.sendKeys(firstName);

        inputLastName.click();
        inputLastName.sendKeys(lastName);

        enterAdressManually.click();

        wait.until(ExpectedConditions.visibilityOf(inputAddress));
        wait.until(ExpectedConditions.elementToBeClickable(inputAddress));
        inputAddress.click();
        inputAddress.sendKeys(adress);

        inputCity.click();
        inputCity.sendKeys(city);

        inputState.click();
        wait.until(ExpectedConditions.visibilityOf(inputStateSelect));
        inputStateSelect.click();

        inputPostalCode.click();
        inputPostalCode.sendKeys(postalCode);

        inputPhoneNumber.click();
        inputPhoneNumber.sendKeys(phoneNumber);

        wait.until(ExpectedConditions.elementToBeClickable(btnSaveContinue));
        btnSaveContinue.click();

        wait.until(ExpectedConditions.visibilityOf(btnContinueToPayment));
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueToPayment));
        btnContinueToPayment.click();

        //wait.until(ExpectedConditions.elementToBeClickable(radioCreditOrDebit));
        //assertTrue(radioCreditOrDebit.isSelected(), "radio is selected");

        inputCartNumber.click();
        inputCartNumber.sendKeys(cardNumber);

        inputMMYY.click();
        inputMMYY.sendKeys(cardMMYY);

        inputCVV.click();
        inputCVV.sendKeys(cardCVV);

        wait.until(ExpectedConditions.elementToBeClickable(btnContinuetoOrderReview));
        btnContinuetoOrderReview.click();

        wait.until(ExpectedConditions.visibilityOf(btnSubmitePayment));
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmitePayment));
        btnSubmitePayment.click();

    }
}
