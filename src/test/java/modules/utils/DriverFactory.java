package modules.utils;
import modules.pages.BasePage.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory extends BasePage {
    public DriverFactory(WebDriver driver) {
        super(driver);
    }
    public static WebDriver getDriverDev(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.edge.driver","C:\\Users\\caior\\OneDrive\\Documentos\\Projetos\\Selenium-web\\adidas-selenium-automation\\src\\test\\java\\modules\\drivers\\chrome\\142.0.7432.0\\msedgedriver.exe");
                //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\java\\modules\\drivers\\chrome\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;

            case "firefox":
                /*System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\java\\modules\\drivers\\gecko\\0.36.0\\geckodriver.exe");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                */

                System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\src\\test\\java\\modules\\drivers\\gecko\\0.36.0\\geckodriver.exe");

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                firefoxOptions.addPreference("dom.webdriver.enabled", false);
                firefoxOptions.addPreference("useAutomationExtension", false);
                firefoxOptions.addPreference("general.useragent.override",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                                "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                firefoxOptions.addPreference("media.navigator.enabled", false);

                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                firefoxOptions.addArguments("--disable-infobars");
                firefoxOptions.addArguments("--disable-extensions");

                driver = new FirefoxDriver(firefoxOptions);

                ((JavascriptExecutor) driver).executeScript(
                        "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
                );

                driver.manage().window().maximize();

                break;

            case "edge":

                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\java\\modules\\drivers\\edge\\141\\msedgedriver.exe");

                EdgeOptions edgeOptions = new EdgeOptions();

                // Evita detecção por recursos do Chromium
                edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-dev-shm-usage");
                edgeOptions.addArguments("--disable-infobars");
                edgeOptions.addArguments("--disable-extensions");

                // Remove avisos e extensões de automação
                edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                edgeOptions.setExperimentalOption("useAutomationExtension", false);

                // Define um user-agent de navegador real
                edgeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

                // Cria o driver com as opções configuradas
                driver = new EdgeDriver(edgeOptions);

                // Injeta script para esconder a propriedade navigator.webdriver
                ((JavascriptExecutor) driver).executeScript(
                        "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
                );

                driver.manage().window().maximize();
                break;


            default:
                throw new IllegalArgumentException("Driver not supported: " + browser);
        }

        return driver;
    }

    public static WebDriver getDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                // Definindo as opções para Chrome em modo headless

                System.setProperty("webdriver.chrome.driver", "C:\\Users\\C1344533\\Documents\\Projetos\\trdTestesAutomacao\\src\\test\\java\\modules\\drivers\\chrome\\chromedriver.exe");

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");

                //WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                // Definindo as opções para Firefox em modo headless
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");

               // WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                // Definindo as opções para Edge em modo headless
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                //edgeOptions.addArguments("--disable-gpu");  // Desabilita aceleração de GPU (opcional)

              //  WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Navegador não suportado: " + browser);
        }

        return driver;
    }

}
