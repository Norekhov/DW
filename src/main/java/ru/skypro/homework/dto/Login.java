package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Objects;

@Data
public class Login {

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
        Login login = (Login) o;
        return Objects.equals(password, login.password) && Objects.equals(username, login.username);
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

    public Login() {
    }

    public Login(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
