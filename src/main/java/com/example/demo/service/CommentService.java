package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentCustom;

import java.util.List;

public interface CommentService {
    List<CommentCustom> commentListByArticleId(Integer articleId);
    boolean addComment(Comment comment);
    boolean deleteComment(Integer id);
}
