package ru.netology.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.ApplicationPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationCard {

    @BeforeAll
    static void setUpAll() {
        Configuration.headless = true;
    }

    @Test
    void passedApplicationAndReApplication() {
        val applicationPage = open("http://localhost:9999", ApplicationPage.class);
        val authInfo = DataHelper.getAuthInfo();
        applicationPage.validReApplication(authInfo);
        $("[data-test-id='success-notification']").shouldHave(visible, Duration.ofSeconds(15));
    }

    @Test
    void invalidCity() {
        val applicationPage = open("http://localhost:9999", ApplicationPage.class);
        val authInfo = DataHelper.getAuthInfoInvalidCity();
        applicationPage.invalidApplication(authInfo);
        String actual = $("[data-test-id='city'] [class='input__sub']").getText();
        String expected = "Доставка в выбранный город недоступна";
        assertEquals(expected,actual.trim());
    }

    @Test
    void invalidDate() {
        val applicationPage = open("http://localhost:9999", ApplicationPage.class);
        val authInfo = DataHelper.getAuthInfo();
        applicationPage.invalidApplication(authInfo, 0, 2);
        $("[class='button__text']").click();
        $$("[class='input__sub']").find(exactText("Заказ на выбранную дату невозможен")).shouldHave(visible);
    }

    @Test
    void invalidName() {
        val applicationPage = open("http://localhost:9999", ApplicationPage.class);
        val authInfo = DataHelper.getAuthInfoInvalidName();
        applicationPage.invalidApplication(authInfo);
        $$("[class='input__sub']").find(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldHave(visible);
    }

    @Test
    void invalidPhone() {
        val applicationPage = open("http://localhost:9999", ApplicationPage.class);
        val authInfo = DataHelper.getAuthInfoInvalidPhone();
        applicationPage.invalidApplication(authInfo);
        $(".input_invalid [class='input__sub']").shouldHave(visible);
    }

    @Test
    void failedCheckbox() {
        val applicationPage = open("http://localhost:9999", ApplicationPage.class);
        val authInfo = DataHelper.getAuthInfoInvalidPhone();
        $("[data-test-id='agreement']").click();
        applicationPage.invalidApplication(authInfo);
        String actual = $(".input_invalid").getText();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных";
        assertEquals(expected,actual.trim());
    }
}
