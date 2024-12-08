package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class LoginDto {

    @Schema(type = "string",
            description = "пароль",
            minLength = 8,
            maxLength = 16)
    private String password;

    @Schema(type = "string",
            description = "логин",
            minLength = 4,
            maxLength = 32)
    private String username;

    public LoginDto() {
    }

    public LoginDto(String password, String username) {
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Login{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDto loginDto = (LoginDto) o;
        return Objects.equals(password, loginDto.password) && Objects.equals(username, loginDto.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
