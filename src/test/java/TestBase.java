import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {

    private static Logger logger = LoggerFactory.getLogger("TestBase.java");

    private static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeAll
    static void setUp() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        logger.info(">>>>> Starting test <<<<<");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
        logger.info("Driver has been closed properly");
        logger.info(">>>>> Finished test <<<<<");
    }

}
