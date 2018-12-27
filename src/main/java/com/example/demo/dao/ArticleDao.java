package com.example.demo.dao;

import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleDao {
    List<Article> queryArticleList();
    void  addArticle(Article article);
    void deleteArticle(Integer id);
    void updateArticle(Article article);
    List<Article> queryArticleByUId(Integer uId);
    Article queryArticleById(Integer id);
    List<ArticleCustom> findArticleListIndexByUid(Map map);
    List<ArticleCustom> findArticleListIndex();
    void updateArticleCommentTime(Article article);
    void updateArticleCommentCount(Article article);
}
