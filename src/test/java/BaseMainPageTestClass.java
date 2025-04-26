import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.page.object.models.MainPage;

public class BaseMainPageTestClass {

    WebDriver driver;
    MainPage mainPage;

    @BeforeEach
    public void setupDriverAndPageObject(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.get(MainPage.MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
