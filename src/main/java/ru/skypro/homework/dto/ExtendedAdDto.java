package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * DTO (Data Transfer Object) для расширенного представления объявления.
 * <p>
 * В классе представлены данные об объявлении, а именно: имя, фамилия, логин и телефон автора,
 * уникальный идентификатор, ссылка на картинку, цена и заголовок.
 * Также в классе реализованы конструкторы, геттеры, сеттеры, equals, hashCode и toString.
 * </p>
 */
public class ExtendedAdDto {

    @Schema(type = "integer",
            format = "int32",
            description = "id объявления")
    private Integer pk;

    @Schema(type = "string",
            description = "имя автора объявления")
    private String authorFirstName;

    @Schema(type = "string",
            description = "фамилия автора объявления")
    private String authorLastName;

    @Schema(type = "string",
            description = "описание объявления")
    private String description;

    @Schema(type = "string",
            description = "логин автора объявления")
    private String email;

    @Schema(type = "string",
            description = "ссылка на картинку объявления")
    private String image;

    @Schema(type = "string",
            description = "телефон автора объявления")
    private String phone;

    @Schema(type = "integer",
            format = "int32",
            description = "цена объявления")
    private Integer price;

    @Schema(type = "string",
            description = "заголовок объявления")
    private String title;

    @Override
    public String toString() {
        return "ExtendedAd{" +
                "pk=" + pk +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", phone='" + phone + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtendedAdDto that = (ExtendedAdDto) o;
        return Objects.equals(pk, that.pk) && Objects.equals(authorFirstName, that.authorFirstName) && Objects.equals(authorLastName, that.authorLastName) && Objects.equals(description, that.description) && Objects.equals(email, that.email) && Objects.equals(image, that.image) && Objects.equals(phone, that.phone) && Objects.equals(price, that.price) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, authorFirstName, authorLastName, description, email, image, phone, price, title);
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ExtendedAdDto() {
    }

    public ExtendedAdDto(Integer pk, String authorFirstName, String authorLastName, String description, String email, String image, String phone, Integer price, String title) {
        this.pk = pk;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.description = description;
        this.email = email;
        this.image = image;
        this.phone = phone;
        this.price = price;
        this.title = title;
    }
}
