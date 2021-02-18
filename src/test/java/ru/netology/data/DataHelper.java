package ru.netology.data;

import lombok.Value;

import static ru.netology.data.FakerTest.*;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String city;
        private String name;
        private String phone;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo(validCity(), validName(), validPhone());
    }

    public static AuthInfo getAuthInfoInvalidCity() {
        return new AuthInfo(invalidCity(), validName(), validPhone());
    }

    public static AuthInfo getAuthInfoInvalidName() {
        return new AuthInfo(validCity(), invalidName(), validPhone());
    }

    public static AuthInfo getAuthInfoInvalidPhone() {
        return new AuthInfo(validCity(), validName(), invalidPhone());
    }
}
