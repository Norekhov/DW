package ru.skypro.homework.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name="ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    private Integer price;

    private String title;

    private String adText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Ad{" +
                "pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", adText='" + adText + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(pk, ad.pk) && Objects.equals(price, ad.price) && Objects.equals(title, ad.title) && Objects.equals(adText, ad.adText) && Objects.equals(user, ad.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, price, title, adText, user);
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
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

    public String getAdText() {
        return adText;
    }

    public void setAdText(String adText) {
        this.adText = adText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ad() {
    }

    public Ad(Integer pk, Integer price, String title, String adText, User user) {
        this.pk = pk;
        this.price = price;
        this.title = title;
        this.adText = adText;
        this.user = user;
    }
}
