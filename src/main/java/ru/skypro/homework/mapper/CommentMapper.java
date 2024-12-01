package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;

public class CommentMapper {
    private final CommentRepository commentRepository;

    public CommentMapper(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(comment.getPk());
        commentDto.setText(comment.getText());
        commentDto.setAuthor(comment.getUser().getId());
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
    }
    public Comment toComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setPk(commentDto.getPk());
        comment.setText(commentDto.getText());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setUser(commentRepository.findById(commentDto.getAuthor()).orElseThrow().getUser());
        return comment;
    }
}
