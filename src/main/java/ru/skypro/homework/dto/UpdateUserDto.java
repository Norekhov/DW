package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * DTO (Data Transfer Object) для обновления информации о пользователе.
 * <p>
 * В классе представлены данные для обновления информации о пользователе, а именно: имя, фамилия и телефон.
 * Также в классе реализованы конструкторы, геттеры, сеттеры, equals, hashCode и toString.
 * </p>
 */
public class UpdateUserDto {
    @Schema(type = "string", description = "имя пользователя", minLength = 3, maxLength = 10)
    private String firstName;

    @Schema(type = "string", description = "фамилия пользователя", minLength = 3, maxLength = 10)
    private String lastName;

    @Schema(type = "string", description = "телефон пользователя", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    public UpdateUserDto() {
    }

    public UpdateUserDto(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UpdateUser{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", phone='" + phone + '\'' + '}';
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
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
