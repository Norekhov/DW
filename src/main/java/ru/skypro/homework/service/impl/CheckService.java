package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CheckService {
    public static boolean checkLength(String name, int min, int max) {
        return name.length() >= min && name.length() <= max;
    }

    public static boolean checkPrice(Integer price, int min, int max) {
        return price != null && price >= min && price <= max;
    }

    public static boolean checkUsername(String username) {
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", username);
    }

    public static boolean checkSymbol(String name) {
        return Pattern.matches("[a-zA-Zа-яА-ЯёЁ]+", name);
    }

    public static boolean checkPhone(String phone) {
        return Pattern.matches("^\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$", phone);
    }
}
