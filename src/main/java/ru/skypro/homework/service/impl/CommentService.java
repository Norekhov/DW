package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

@Service
public class CommentService {

    public CommentsDto getCommentsForAd(Integer id) {
        return null;
    }

    public CommentDto addComment(Integer adId, CreateOrUpdateCommentDto comment) {
        return null;
    }

    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto comment) {
        return null;
    }

    public String deleteComment(Integer adId, Integer commentId) {
        return null;
    }
}
