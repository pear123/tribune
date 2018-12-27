package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleCustom;

import java.util.List;

public interface ArticleService {
    boolean addArticle(Article article);
    boolean deleteArticle(Integer id);
    boolean updateArticle(Article article);
    List<Article> queryArticleList();
    List<Article> queryArticleByUid(Integer id);
    Article queryArticleById(Integer id);
    int getCount(Integer u_id);
    List<ArticleCustom> findArticleListIndexByUid(int beginIndex, int size, Integer uId);
   /* List<ArticleCustom> findArticleListIndex(int beginIndex, int size);*/
}
