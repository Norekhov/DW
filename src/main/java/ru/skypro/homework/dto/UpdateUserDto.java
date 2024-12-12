package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.skypro.homework.service.impl.СheckServiceImpl;

import java.util.Objects;

public class UpdateUserDto {
    @Schema(type = "string",
            description = "имя пользователя",
            minLength = 3,
            maxLength = 10)
    private String firstName;

    @Schema(type = "string",
            description = "фамилия пользователя",
            minLength = 3,
            maxLength = 10)
    private String lastName;

    @Schema(type = "string",
            description = "телефон пользователя",
            pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Override
    public String toString() {
        return "UpdateUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateUserDto that = (UpdateUserDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (СheckServiceImpl.checkLength(firstName, 3, 10) && СheckServiceImpl.checkSymbol(firstName)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Длина имени от 3 до 10 символов");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (СheckServiceImpl.checkLength(lastName, 3, 10) && СheckServiceImpl.checkSymbol(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Длина фамилии от 3 до 10 символов");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (СheckServiceImpl.checkPhone(phone)) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Формат номера телефона +7(000)000-00-00");
        }
    }

    public UpdateUserDto() {
    }

    public UpdateUserDto(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
