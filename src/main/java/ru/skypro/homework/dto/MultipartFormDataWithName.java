package ru.skypro.homework.dto;

public class MultipartFormDataWithName {
    public MultipartFormDataWithName(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    private byte[] image;
}
