package modules.pages.HomePage;

import modules.pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//img[contains(@class,'main-logo')]")
    private WebElement bbLogo;
    @FindBy(name = "callback_0")
    private WebElement campoLogin;
    @FindBy(name = "callback_2")
    public WebElement campoSenha;
    @FindBy(id = "loginButton_0")
    public WebElement btnLogin;
    @FindBy(id = "tituloPagina")
    public WebElement tituloPagina;
    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement inputSearch;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void AcessHomepage(){
        driver.get("https://www.nike.com/");

    }
}
