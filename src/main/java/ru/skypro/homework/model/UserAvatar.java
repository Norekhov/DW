package ru.skypro.homework.model;

import javax.persistence.*;

@Entity
@Table(name="userAvatar")
public class UserAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "filepath")
    private String filePath;

    @Column(name = "filesize")
    private long fileSize;

    @Column(name = "mediatype")
    private String mediaType;

    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;
}
