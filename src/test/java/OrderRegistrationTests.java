import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.pageObjectModels.MainPage;
import ru.yandex.praktikum.pageObjectModels.OrderRegistrationPage;

@RunWith(Parameterized.class)
public class OrderRegistrationTests {
    private WebDriver driver;
    private OrderRegistrationPage orderRegistrationPage;
    private MainPage mainPage;

    private String name;
    private String surname;
    private String address;
    private String station;
    private String phone;
    private String date;
    private String rentLength;
    private boolean isBlackSelected;
    private boolean isGreySelected;
    private String comment;

    @Parameterized.Parameters
    public static Object[][] getFaqData(){
        return new Object[][]{
                {"Ива", "Иванова", "Климашкина 1", "Сокольники", "81234567890", "05.05.2025", "сутки", true, true, "Комментарий"},
                {"Екатерина", "Ховард", "Покровский бульвар 12", "Орехово", "+71234567890", "05.05.2025", "пятеро суток", false, false, ""}
        };
    }

    public OrderRegistrationTests(String name, String surname, String address, String station, String phone, String date, String rentLength, boolean isBlackSelected, boolean isGreySelected, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.rentLength = rentLength;
        this.isBlackSelected = isBlackSelected;
        this.isGreySelected = isGreySelected;
        this.comment = comment;
    }

    @Before
    public void setupDriverAndPageObject(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderRegistrationPage = new OrderRegistrationPage(driver);
    }

    @Test
    public void checkOrderRegistrationHeaderButtonPositiveFlowTest() {
        mainPage.clickHeaderRegistrationButton();
        registerOrderOnRegistrationPage();
        orderRegistrationPage.waitOrderConfirmedToLoad();
    }

    @Test
    public void checkOrderRegistrationHomeButtonPositiveFlowTest() {
        mainPage.clickHomeRegistrationButton();
        registerOrderOnRegistrationPage();
        orderRegistrationPage.waitOrderConfirmedToLoad();
    }

    private void registerOrderOnRegistrationPage() {
        orderRegistrationPage.waitFirstPageToLoad();
        orderRegistrationPage.setName(name);
        orderRegistrationPage.setSurname(surname);
        orderRegistrationPage.setAddress(address);
        orderRegistrationPage.setStation(station);
        orderRegistrationPage.setPhone(phone);

        orderRegistrationPage.clickNextPageButton();
        orderRegistrationPage.waitSecondPageToLoad();
        orderRegistrationPage.setDate(date);
        orderRegistrationPage.selectRentLength(rentLength);
        if (isBlackSelected)
            orderRegistrationPage.selectBlackColourCheckbox();
        if (isGreySelected)
            orderRegistrationPage.selectGreyColourCheckbox();
        orderRegistrationPage.setComment(comment);

        orderRegistrationPage.clickPlaceOrderButton();
        orderRegistrationPage.waitOrderConfirmationPopUpToLoad();
        orderRegistrationPage.clickConfirmOrderButton();
    }

    private void sendOrder() {
        orderRegistrationPage.waitFirstPageToLoad();
        orderRegistrationPage.setName(name);
        orderRegistrationPage.setSurname(surname);
        orderRegistrationPage.setAddress(address);
        orderRegistrationPage.setStation(station);
        orderRegistrationPage.setPhone(phone);

        orderRegistrationPage.clickNextPageButton();
        orderRegistrationPage.waitSecondPageToLoad();
        orderRegistrationPage.setDate(date);
        orderRegistrationPage.selectRentLength(rentLength);
        if (isBlackSelected)
            orderRegistrationPage.selectBlackColourCheckbox();
        if (isGreySelected)
            orderRegistrationPage.selectGreyColourCheckbox();
        orderRegistrationPage.setComment(comment);
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
