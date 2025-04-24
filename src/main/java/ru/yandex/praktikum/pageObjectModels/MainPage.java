package ru.yandex.praktikum.pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainPage {
    private WebDriver driver;

    private final static String XPATH_TO_FAQ_PANEL = "//div[@class='Home_FAQ__3uVm4']";
    //FAQ question headers
    private By faqHeaders = By.xpath(XPATH_TO_FAQ_PANEL + "//div[@class='accordion__button']");
    //FAQ question answers
    private By faqAnswers = By.xpath(XPATH_TO_FAQ_PANEL + "//div[@class='accordion__panel']");
    //Registration button in the header
    private By headerRegistrationButton = By.xpath("//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    //Registration button on the page
    private By homeRegistrationButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getFaqQuestions(){
        return driver.findElements(faqHeaders);
    }

    public List<WebElement> getFaqAnswers(){
        return  driver.findElements(faqAnswers);
    }

    public void clickFaqQuestion(String question) {
        WebElement questionWebElement = getFaqQuestions().stream().filter(q -> q.getText().equals(question)).findFirst().get();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionWebElement);
        questionWebElement.click();
    }

    public void waitForLoadFaqAnswer(String question) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> getFaqAnswersMap().get(question).isDisplayed());
    }

    public String getFaqAnswerText(String question) {
        return getFaqAnswersMap().get(question).getText();
    }

    public Map<String, WebElement> getFaqAnswersMap() {
        List<WebElement> questions = getFaqQuestions();
        List<WebElement> answers = getFaqAnswers();
        Map<String, WebElement> faqMap = IntStream.range(0, questions.size()).boxed()
                .collect(Collectors.toMap(i -> questions.get(i).getText(), i -> answers.get(i)));
        return faqMap;
    }

    public void clickHeaderRegistrationButton() {
        driver.findElement(headerRegistrationButton).click();
    }

    public void clickHomeRegistrationButton() {
        WebElement homeRegistrationButtonWebElement = driver.findElement(homeRegistrationButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", homeRegistrationButtonWebElement);
        homeRegistrationButtonWebElement.click();
    }
}
