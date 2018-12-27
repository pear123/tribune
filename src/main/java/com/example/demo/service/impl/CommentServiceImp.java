package com.example.demo.service.impl;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.CommentDao;
import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentCustom;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<CommentCustom> commentListByArticleId(Integer articleId) {
        Article article=articleDao.queryArticleById(articleId);
        if(article!=null){
            return commentDao.queryCommentListByArticleId(articleId);
        }
        return null;
    }

    @Override
    public boolean addComment(Comment comment) {
        Article article=articleDao.queryArticleById(comment.getArticleId());
        if(article!=null){
            article.setLastCommentCreatetime(comment.getCommentCreatetime());
            article.setCommentCount(article.getCommentCount()+1);
            commentDao.addComment(comment);
            articleDao.updateArticleCommentTime(article);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteComment(Integer id) {
        Comment comment=commentDao.queryCommentById(id);
        if(comment!=null){
            Article article=articleDao.queryArticleById(comment.getArticleId());
            article.setCommentCount(article.getCommentCount()-1);
            commentDao.deleteComment(id);
            articleDao.updateArticleCommentCount(article);
            return true;
        }
        return false;
    }
}
