package ru.yandex.praktikum.pageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderRegistrationPage {
    WebDriver driver;

    // --- First page fields ---
    //Name input field
    By nameField = By.xpath("//input[@placeholder='* Имя']");
    //Surname input field
    By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    //Address input field
    By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Station input field
    By stationField = By.xpath("//input[@placeholder='* Станция метро']");
    //Station selection options
    By stationOptions = By.className("select-search__row");
    //Phone input field
    By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Next page button
    By nextPageButton = By.xpath("//div[@class='Order_NextButton__1_rCA']/button");
    // --- Second page field ---
    //Delivery date field
    By deliveryDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Rent length field
    By rentLengthField = By.xpath("//div[text()='* Срок аренды']");
    //Rent length field options
    By rentLengthFieldOptions = By.className("Dropdown-option");
    //Colour selection black checkbox
    By colourSelectionCheckboxBlack = By.id("black");
    //Colour selection grey checkbox
    By colourSelectionCheckboxGrey = By.id("grey");
    //Comment for courier field
    By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //Place order button
    By placeOrderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    // --- Order confirmation pop-up window ---
    //Confirm order button
    By confirmOrderButton = By.xpath("//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");
    //Order is confirmed header
    By confirmationWindowHeader = By.xpath("//div[text()='Заказ оформлен']");

    public OrderRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitFirstPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> driver.findElement(nameField).isDisplayed());
    }

    public void waitSecondPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> driver.findElement(deliveryDateField).isDisplayed());
    }

    public void waitOrderConfirmationPopUpToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> driver.findElement(confirmOrderButton).isDisplayed());
    }

    public void waitOrderConfirmedToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> driver.findElement(confirmationWindowHeader)).isDisplayed();
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setStation(String station) {
        driver.findElement(stationField).sendKeys(station);
        driver.findElement(stationOptions).click();
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void setDate(String date) {
        driver.findElement(deliveryDateField).sendKeys(date + Keys.ENTER);
    }

    public void selectRentLength(String rentLength) {
        driver.findElement(rentLengthField).click();
        driver.findElements(rentLengthFieldOptions).stream().filter(q -> q.getText().equals(rentLength)).findFirst().get().click();
    }

    public void selectBlackColourCheckbox() {
        driver.findElement(colourSelectionCheckboxBlack).click();
    }

    public void selectGreyColourCheckbox() {
        driver.findElement(colourSelectionCheckboxGrey).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickNextPageButton() {
        driver.findElement(nextPageButton).click();
    }

    public void clickPlaceOrderButton() {
        driver.findElement(placeOrderButton).click();
    }

    public void clickConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }
}
