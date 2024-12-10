package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.skypro.homework.service.СheckService;

import java.util.Objects;

public class CreateOrUpdateCommentDto {

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
        CreateOrUpdateCommentDto that = (CreateOrUpdateCommentDto) o;
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
        if (СheckService.checkLength(text, 8, 64)) {
            this.text = text;
        } else {
            throw new IllegalArgumentException("Длина комментария от 8 до 64 символов");
        };
    }

    public CreateOrUpdateCommentDto() {
    }

    public CreateOrUpdateCommentDto(String text) {
        if (СheckService.checkLength(text, 8, 64)) {
            this.text = text;
        } else {
            throw new IllegalArgumentException("Длина комментария от 8 до 64 символов");
        };
    }
}
