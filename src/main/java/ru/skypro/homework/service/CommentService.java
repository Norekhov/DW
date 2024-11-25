package ru.skypro.homework.service;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@Service
public class CommentService {

    public Comments getCommentsForAd(Integer id) {
        return null;
    }

    public Comment addComment(Integer adId, CreateOrUpdateComment comment) {
        return null;
    }

    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment) {
        return null;
    }

    public String deleteComment(Integer adId, Integer commentId) {
        return null;
    }
}
