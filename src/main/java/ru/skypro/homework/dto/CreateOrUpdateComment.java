package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class CreateOrUpdateComment {

    @Schema(required = true,
            type = "string",
            description = "текст комментария",
            minLength = 8,
            maxLength = 64)
    private String text;

    @Override
    public String toString() {
        return "CreateOrUpdateComment{" +
                "text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateComment that = (CreateOrUpdateComment) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CreateOrUpdateComment() {
    }

    public CreateOrUpdateComment(String text) {
        this.text = text;
    }
}
