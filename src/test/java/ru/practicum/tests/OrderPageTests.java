package ru.practicum.tests;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.MainPage;
import pom.OrderPage;

import static org.hamcrest.CoreMatchers.containsString;
import static pom.MainPage.URL;

@RunWith(Parameterized.class)
public class OrderPageTests {
    private WebDriver driver;
    private final String name;
    private final String lastName;
    private final String address;
    private final String comment;
    private final String phoneNumber;


    public OrderPageTests(String name, String lastName, String address, String phoneNumber, String comment) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber= phoneNumber;
        this.comment = comment;
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(URL);
    }
    @Parameterized.Parameters
    public static Object[][] setOrderForm() {
        return new Object[][] {
                {"Анастасия", "Нескромнова", "Дворцовая 1", "+72233445674", "нужен самокат"},
                {"Иван", "Синичкин", "Шоссеево", "89225577568", "ожидаю"},
        };
    }
    @Test
    public void makeOrderByHeadButtonTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        mainPage.acceptCookie();
        mainPage.clickOrderButtonHead();
        orderPage.makeOrder(name, lastName, address, orderPage.stationName, phoneNumber, orderPage.deliveryDate, comment);
        MatcherAssert.assertThat(orderPage.successOrder(),containsString( "Заказ оформлен"));}


    @Test
    public void makeOrderByBottomButtonTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        mainPage.acceptCookie();
        mainPage.clickOrderButtonBottom();
        orderPage.makeOrder(name, lastName, address, orderPage.stationName, phoneNumber, orderPage.deliveryDate, comment);
        MatcherAssert.assertThat(orderPage.successOrder(),containsString( "Заказ оформлен"));}

    @After
    public void finishTest() {

        driver.quit();
    }

}

