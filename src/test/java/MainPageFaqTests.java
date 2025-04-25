import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageFaqTests extends BaseMainPageTestClass {

    @ParameterizedTest(name = "{index}. Test data: Question={0}; Answer={1}")
    @CsvFileSource(resources = "/testDataFAQ.csv")
    public void checkClickOnQuestionOpeningCorrectAnswer(String question, String answer) {
        mainPage.clickFaqQuestion(question);
        mainPage.waitForLoadFaqAnswer(question);
        String actualAnswer = mainPage.getFaqAnswerText(question);
        assertEquals(answer, actualAnswer, "Answer for question: '" + question + "' is different from expected: ");
    }
}
