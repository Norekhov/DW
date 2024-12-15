package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.CommentService;
/**
 * Контроллер для работы с комментариями.
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Комментарии")
public class CommentController {
    private final CommentService commentService;
    /**
     * Конструктор контроллера CommentController.
     */
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    /**
     * Получение комментариев объявления.
     */
    @GetMapping("/{adId}/comments")
    @Operation(summary = "Получение комментариев объявления")
    public ResponseEntity<CommentsDto> getComments(@PathVariable Integer adId) {
        CommentsDto commentsDto = commentService.getCommentsForAd(adId);
        if (commentsDto.getCount() < 1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commentsDto);
    }
    /**
     * Добавление комментария к объявлению.
     */
    @PostMapping("/{adId}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer adId, @RequestBody CreateOrUpdateCommentDto comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(adId, comment));
    }
    /**
     * Обновление комментария.
     */
    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    public CommentDto updateComment(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody CreateOrUpdateCommentDto comment) {
        return commentService.updateComment(adId, commentId, comment);
    }
    /**
     * Удаление комментария.
     */
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        if (!commentService.deleteComment(commentId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
