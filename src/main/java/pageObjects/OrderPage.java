package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    //Поле "Имя"
    private final By nameField = By.xpath( "//input[@placeholder = '* Имя']");
    //Поле "Фамилия"
    private final By lastNameField = By.xpath("//input[@placeholder = '* Фамилия']");
    //Поле "Адрес: куда привезти заказ"
    private final By addressField = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Поле "Станция метро"
    private final By metroStationField = By.xpath("//input[@placeholder = '* Станция метро']");
    //Локатор для выбора станции метро "Сокольники"
    private final By chooseSokolnikiStation = By.xpath("//div[text() = 'Сокольники']");

    //Поле "Телефон: на него позвонит курьер"
    private final By phoneNumberField = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private final By nextButton = By.xpath("//*[text() = 'Далее']");
    //Поле "Когда привезти самокат"
    private final By dateButton = By.xpath("//input[@placeholder = '* Когда привезти самокат']");
    //Локатор для выбора даты 15.05.2025 в поле "Когда привезти самокат"
    private final By chooseDate = By.xpath("//div[@aria-label='Choose четверг, 15-е мая 2025 г.']");

    //Поле "Срок аренды"
    private final By rentalPeriod = By.xpath("//div[@class ='Dropdown-root']");
    //Локатор выбора опции "сутки" в поле "Срок аренды"
    private final By rentalPeriodOneDay = By.xpath("//*[text() = 'сутки']");

    //Локатор выбора самоката черного цвета
    private final By blackSamokat = By.xpath("//input[@id='black']");
    //Поле "Комментарий для курьера"
    private final By commentAdd = By.xpath("//input[@placeholder = 'Комментарий для курьера']");
    //Кнопка "Закать" под формой заказа
    private final By finalOrderButton = By.cssSelector("div[class='Order_Buttons__1xGrp']>button:nth-child(2)");
    //Кнопка "Да" для подтверждения заказа
    private final By yesOrderButton = By.xpath("//button[text() = 'Да']");
    //Локатор всплывающего окна после успешного создания заказа
    private final By successCreatedOrder = By.xpath("//*[contains(@class,'Order_ModalHeader__3FDaJ')]");

    public final String stationName = "Сокольники";
    public final String deliveryDate = "15.05.2025";

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement targetElement (By locator) {
        new WebDriverWait(this.driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }
    public void clickElement (By locator) {
        new WebDriverWait(this.driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }
    public void addTextToElement(By locator, String text) {
        WebElement element = targetElement(locator);
        element.sendKeys(text);
    }
    public String getTextFromElement(By locator) {
        WebElement element = targetElement(locator);
        return element.getText();
    }

    public void makeOrder (String name, String lastName, String address, String text, String phoneNumber, String date,String comment) {
        addTextToElement(nameField, name);
        addTextToElement(lastNameField, lastName);
        addTextToElement(addressField, address);
        addTextToElement(metroStationField, text);
        clickElement(chooseSokolnikiStation);
        addTextToElement(phoneNumberField, phoneNumber);
        clickElement(nextButton);
        addTextToElement(dateButton, date);
        clickElement(chooseDate);
        clickElement(rentalPeriod);
        clickElement(rentalPeriodOneDay);
        clickElement(blackSamokat);
        addTextToElement(commentAdd, comment);
        clickElement(finalOrderButton);
        clickElement(yesOrderButton);

    }
    public String successOrder() {
        return getTextFromElement(successCreatedOrder);}
}
