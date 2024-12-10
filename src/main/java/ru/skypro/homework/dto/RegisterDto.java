package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.service.СheckService;

import java.util.Objects;

@Data
public class RegisterDto {

    @Schema(type = "string",
            description = "логин",
            minLength = 4,
            maxLength = 32)
    private String username;

    @Schema(type = "string",
            description = "пароль",
            minLength = 8,
            maxLength = 16)
    private String password;

    @Schema(type = "string",
            description = "имя пользователя",
            minLength = 2,
            maxLength = 16)
    private String firstName;

    @Schema(type = "string",
            description = "фамилия пользователя",
            minLength = 2,
            maxLength = 16)
    private String lastName;

    @Schema(type = "string",
            description = "телефон пользователя",
            pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(type = "string",
            description = "роль пользователя",
            allowableValues = {"USER", "ADMIN"})
    private RoleDto roleDto;

    @Override
    public String toString() {
        return "Register{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + roleDto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterDto registerDto = (RegisterDto) o;
        return Objects.equals(username, registerDto.username) && Objects.equals(password, registerDto.password) && Objects.equals(firstName, registerDto.firstName) && Objects.equals(lastName, registerDto.lastName) && Objects.equals(phone, registerDto.phone) && roleDto == registerDto.roleDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName, phone, roleDto);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (СheckService.checkLength(username, 4, 32) && СheckService.checkUsername(username)) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Длина логина от 4 до 32 символов в формате \"yourmail@mail.ru\"");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (СheckService.checkLength(password, 8, 16)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Длина пароля от 8 до 16 символов");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (СheckService.checkLength(firstName, 2, 16) && СheckService.checkSymbol(firstName)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Длина имени от 2 до 16 символов");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (СheckService.checkLength(lastName, 2, 16) && СheckService.checkSymbol(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Длина фамилии от 2 до 16 символов");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (СheckService.checkPhone(phone)) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Формат номера телефона +7(000)000-00-00");
        }
    }

    public RoleDto getRole() {
        return roleDto;
    }

    public void setRole(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public RegisterDto() {
    }

    public RegisterDto(String username, String password, String firstName, String lastName, String phone, RoleDto roleDto) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.roleDto = roleDto;
    }
}
