package ru.netology.web;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestFormCard {

    WebDriver driver;
    @BeforeEach
    void setupUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("-disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldTestSomething() {
        //Предусловие
        driver.get("http://localhost:9999");

        //Выполняемые действия
        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Владимир Иванов-Петров");
        driver.findElement(By.cssSelector("[data-test-id = 'phone'] input")).sendKeys("+79011114038");
        driver.findElement(By.cssSelector("[data-test-id= 'agreement'].checkbox")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id= 'order-success']")).getText();

        //Проверки
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}