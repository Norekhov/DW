package ru.skypro.homework.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="user_avatar")
public class UserAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "filepath")
    private String filePath;

    @Column(name = "pathforendpoint")
    private String pathForEndpoint;

    @Column(name = "mediatype")
    private String mediaType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @Override
    public String toString() {
        return "UserAvatar{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", pathForEndpoint='" + pathForEndpoint + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAvatar that = (UserAvatar) o;
        return Objects.equals(id, that.id) && Objects.equals(filePath, that.filePath) && Objects.equals(pathForEndpoint, that.pathForEndpoint) && Objects.equals(mediaType, that.mediaType) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, pathForEndpoint, mediaType, user);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPathForEndpoint() {
        return pathForEndpoint;
    }

    public void setPathForEndpoint(String pathForEndpoint) {
        this.pathForEndpoint = pathForEndpoint;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAvatar() {
    }

    public UserAvatar(Integer id, String filePath, String pathForEndpoint, String mediaType, User user) {
        this.id = id;
        this.filePath = filePath;
        this.pathForEndpoint = pathForEndpoint;
        this.mediaType = mediaType;
        this.user = user;
    }
}