package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerTest {
    static Faker faker = new Faker(new Locale("ru"));

    public static String validCity() {
        String city = faker.address().cityName();
        while (city.equalsIgnoreCase("Тольятти") | city.equalsIgnoreCase("Сочи") | city.equalsIgnoreCase("Магнитогорск") | city.equalsIgnoreCase("Новокузнецк")) {
            city = faker.address().cityName();
        }
        return city;
    }

    public static String invalidCity() {
        String city = faker.address().streetName();
        return city;
    }

    public static String validName() {
        String firstName = faker.name().firstName();
        while (firstName.contains("ё")) { firstName = faker.name().firstName(); }
        String lastName = faker.name().lastName();
        while (lastName.contains("ё")) { lastName = faker.name().lastName(); }
        String name =  lastName + " " + firstName;
        return name;
    }

    public static String invalidName() {
        String name = validName() + faker.idNumber().valid();
        return name;
    }

    public static String validPhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }
    public static String invalidPhone() {
        String phone = validPhone().substring(2, 5);
        return phone;
    }
}
