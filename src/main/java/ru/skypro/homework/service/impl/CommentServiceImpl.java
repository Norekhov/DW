package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.CustomUserDetailsManager;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CustomUserDetailsManager customUserDetailsManager;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, CustomUserDetailsManager customUserDetailsManager) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.customUserDetailsManager = customUserDetailsManager;
    }
    @Override
    public CommentsDto getCommentsForAd(Integer adId) {
        List<CommentDto> comments = commentRepository.findByAdPk(adId).stream().map(CommentMapper::toDto).toList();
        return new CommentsDto(comments.size(), comments);
    }

    @Override
    public CommentDto addComment(Integer adId, CreateOrUpdateCommentDto createCommentDto) {
        Ad ad = adRepository.findById(adId).orElseThrow();
        Comment comment = CommentMapper.toComment(createCommentDto, customUserDetailsManager.getCurrentUser(), System.currentTimeMillis(), ad);
        return CommentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        Ad ad = adRepository.findById(adId).orElseThrow();
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.setText(createOrUpdateCommentDto.getText());
        comment.setCreatedAt(System.currentTimeMillis());
        return CommentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public Boolean deleteComment(Integer commentId) {
        try {
            Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Попытка удалить несуществующий комментарий "+commentId));
            commentRepository.delete(comment);
        } catch (EntityNotFoundException e) {
            return false;
        }
        return true;
    }
}
