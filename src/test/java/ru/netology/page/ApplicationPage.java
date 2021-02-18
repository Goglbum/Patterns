package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DatePicker;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ApplicationPage {
    private SelenideElement cityInput = $("[placeholder='Город']");
    private ElementsCollection cityCollection = $$(".menu-item__control");
    private SelenideElement name = $("[name='name']");
    private SelenideElement phone = $("[name='phone']");
    private SelenideElement checkbox = $("[data-test-id='agreement']");
    private SelenideElement buttonApplication = $("[class='button__text']");
    private SelenideElement buttonReApplication = $("[data-test-id='replan-notification'] [class='button__content']");

    public void validApplication(DataHelper.AuthInfo info) {
        cityInput.setValue(info.getCity().substring(0, 2));
        cityCollection.find(exactText(info.getCity())).click();
        DatePicker.setRandomDateInCalendarByMouseClick();
        name.setValue(info.getName());
        phone.setValue(info.getPhone());
        checkbox.click();
        buttonApplication.click();
    }

    public void validReApplication(DataHelper.AuthInfo info) {
        validApplication(info);
        $("[data-test-id='success-notification']").shouldHave(visible, Duration.ofSeconds(15));
        DatePicker.setRandomDateInCalendarByMouseClick(3, 3);
        buttonApplication.click();
        buttonReApplication.click();
    }

    public void invalidApplication(DataHelper.AuthInfo info, int minRandomDate, int maxRandomDate) {
        cityInput.setValue(info.getCity());
        DatePicker.setRandomDateInCalendarByText(minRandomDate, maxRandomDate);
        name.setValue(info.getName());
        phone.setValue(info.getPhone());
        checkbox.click();
        buttonApplication.click();
    }

    public void invalidApplication(DataHelper.AuthInfo info) {
        invalidApplication(info, 3, 500);
    }

}
