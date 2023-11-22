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

import static org.junit.jupiter.api.Assertions.*;
public class TestValidFormCard {
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
    public void enterNotValidName() { ///Недопустимое имя

        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Artem");
        driver.findElement(By.cssSelector("[data-test-id = 'phone'] input")).sendKeys("+79025789788");
        driver.findElement(By.cssSelector("[data-test-id= 'agreement'].checkbox")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id= name].input_invalid .input__sub")).getText();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());

    }

    @Test
    public void nameFieldIsEmpty() { ///Пустое поле имени

        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id = 'phone'] input")).sendKeys("+77777782574");
        driver.findElement(By.cssSelector("[data-test-id= 'agreement'].checkbox")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id= name].input_invalid .input__sub")).getText();

        assertEquals("Поле обязательно для заполнения", text.trim());

    }

    @Test
    public void enterNotValidPhone() { ///Недопустимый номер телефона

        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Сергей");
        driver.findElement(By.cssSelector("[data-test-id = phone] input")).sendKeys("-0000");
        driver.findElement(By.cssSelector("[data-test-id= 'agreement'].checkbox")).click();
        driver.findElement(By.className("button")).click();
        List<WebElement> elements = driver.findElements(By.className("input__sub"));
        String text = elements.get(1).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());

    }

    @Test
    public void phoneFieldIsEmpty() { ///Пустое поле телефона

        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Сергей");
        //driver.findElement(By.cssSelector("[data-test-id= phone] input")).sendKeys("+79025789788");
        driver.findElement(By.cssSelector("[data-test-id= 'agreement'].checkbox")).click();
        driver.findElement(By.className("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id= phone].input_invalid .input__sub")).getText();

        assertEquals("Поле обязательно для заполнения", text.trim());

    }

    @Test
    public void checkboxTest() { ///тест нажатия кнопки check-box

        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Сергей");
        driver.findElement(By.cssSelector("[data-test-id = phone] input")).sendKeys("+79025789788");
        //driver.findElement(By.cssSelector("[data-test-id= 'agreement'].checkbox")).click();
        driver.findElement(By.className("button")).click();

        boolean text = driver.findElement(By.cssSelector("[data-test-id = agreement].input_invalid")).isDisplayed();
        assertEquals(true, text);

    }
}