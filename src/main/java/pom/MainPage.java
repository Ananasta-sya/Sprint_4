package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    //Локатор верхней кнопки "Заказать"
    private final By orderButtonHead = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
    //Локатор нижней кнопки "Заказать"
    private final By orderButtonBottom = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Локатор кнопки принятия Куки "Да все привыкли"
    private final By acceptCookie = By.id("rcc-confirm-button");
    //Локатор вопросов из раздела "Вопросы о важном"
    private final By questionFAQ = By.className("accordion__heading");
    //Локатор раскрывающихся ответов из раздела "Вопросы о важном"
    private final By answerFAQ = By.xpath(".//div[@class='accordion__panel']/p");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public void acceptCookie() {
        driver.findElement(acceptCookie).click();
    }

    public void waitToLoadAnswer(int num) {
        new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElements(this.answerFAQ).get(num)));
    }
    public void clickQuestionFAQ(int num) {
        driver.findElements(questionFAQ).get(num).click();
    }
    public String getAnswerFAQ(int num) {
        return driver.findElements(answerFAQ).get(num).getText();
    }
    public void clickOrderButtonHead() {
        driver.findElement(orderButtonHead).click();
    }
    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }
}