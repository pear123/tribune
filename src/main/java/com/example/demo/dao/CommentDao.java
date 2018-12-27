package com.example.demo.dao;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
 @Repository
public interface CommentDao {
    void addComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Integer commentId);
    Comment queryCommentById(Integer commentId);
    List<CommentCustom> queryCommentListByArticleId(Integer articleId);
}
