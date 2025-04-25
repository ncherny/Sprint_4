import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.yandex.praktikum.page.object.models.OrderRegistrationPage;


public class OrderRegistrationTests extends BaseMainPageTestClass{
    private OrderRegistrationPage orderRegistrationPage;

    @BeforeEach
    @Override
    public void setupDriverAndPageObject() {
        super.setupDriverAndPageObject();
        orderRegistrationPage = new OrderRegistrationPage(driver);
    }

    @ParameterizedTest(name="{index}. Test data: Name={0}, Surname={1}, Address={2}, Station={3}, Phone={4}, Date={5}, Rent length={6}, Is black={7}, Is grey={8}, Comment={9}")
    @CsvFileSource(resources = "/testDataPositiveRegistration.csv")
    public void checkOrderRegistrationHeaderButtonPositiveFlowTest(String name, String surname, String address, String station, String phone, String date, String rentLength, boolean isBlackSelected, boolean isGreySelected, String comment) {
        mainPage.clickHeaderRegistrationButton();
        orderRegistrationPage.registerOrder(name, surname, address, station, phone, date, rentLength, isBlackSelected, isGreySelected, comment);
        assert(orderRegistrationPage.wasOrderConfirmed());
    }

    @ParameterizedTest(name="{index}. Test data: Name={0}, Surname={1}, Address={2}, Station={3}, Phone={4}, Date={5}, Rent length={6}, Is black={7}, Is grey={8}, Comment={9}")
    @CsvFileSource(resources = "/testDataPositiveRegistration.csv")
    public void checkOrderRegistrationHomeButtonPositiveFlowTest(String name, String surname, String address, String station, String phone, String date, String rentLength, boolean isBlackSelected, boolean isGreySelected, String comment) {
        mainPage.clickHomeRegistrationButton();
        orderRegistrationPage.registerOrder(name, surname, address, station, phone, date, rentLength, isBlackSelected, isGreySelected, comment);
        assert(orderRegistrationPage.wasOrderConfirmed());
    }


}
