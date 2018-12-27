package com.example.demo.service.impl;


import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.CommentDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.*;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentDao commentDao;
   /**
   * @Description: 增加文章
   * @Param: [article]
   * @return: boolean
   * @Author: Lili Chen
   * @Date: 2018/12/13
   */
    @Override
    public boolean addArticle(Article article) {
        articleDao.addArticle(article);
        return true;
    }
    /**
    * @Description: 删除文章
    * @Param: [id]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Override
    public boolean deleteArticle(Integer id) {
        Article article =articleDao.queryArticleById(id);
        if(article!=null){
            articleDao.deleteArticle(id);
            List<CommentCustom> commentList=commentDao.queryCommentListByArticleId(id);
            if(!commentList.isEmpty()){
                for(CommentCustom commentCustom:commentList){
                    commentDao.deleteComment(commentCustom.getCommentId());
                }
            }
            return true;
        }
        return false;
    }

    /**
    * @Description: 更新文章
    * @Param: [article]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Override
    public boolean updateArticle(Article article) {
        Article article1=articleDao.queryArticleById(article.getArticleId());
        if(article1!=null){
          articleDao.updateArticle(article);
            return true;
        }
        return false;
    }

    @Override
    public List<Article> queryArticleList() {
        return articleDao.queryArticleList();
    }
    /**
    * @Description: 通过用户id查找文章
    * @Param: [id]
    * @return: java.util.List<com.example.demo.entity.Article>
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Override
    public List<Article> queryArticleByUid(Integer id) {
        User user=userDao.queryUserById(id);
        if(user!=null){
            return articleDao.queryArticleByUId(id);
        }
        return null;
    }
    /**
    * @Description: 通过id查找文章
    * @Param: [id]
    * @return: com.example.demo.entity.Article
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Override
    public Article queryArticleById(Integer id) {
        Article article=null;
        article=articleDao.queryArticleById(id);
        return article;
    }


    /**
    * @Description:  某用户文章的篇数
    * @Param: [uId]
    * @return: int
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @Override
    public int getCount(Integer uId) {
        List<Article> articleList=articleDao.queryArticleByUId(uId);
        int count=articleList.size();
        return count;
    }


    /**
    * @Description: 获取某用户从beginIndex开始，size条数的文章
    * @Param: [beginIndex, size, uId]
    * @return: java.util.List<com.example.demo.entity.Article>
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @Override
    public List<ArticleCustom> findArticleListIndexByUid(int beginIndex, int size,Integer uId) {
        List<ArticleCustom> articleList=new ArrayList<ArticleCustom>();
        if(uId==null){
           articleList= articleDao.findArticleListIndex();
        }else {
            Map map=new HashMap<>();
            map.put("uId",uId);
          articleList= articleDao.findArticleListIndexByUid(map);
        }
        List<ArticleCustom> articleList2= new ArrayList<ArticleCustom>();
        Collections.sort(articleList);
        for(int i=beginIndex;i<size+beginIndex&&i<articleList.size();i++){
            articleList2.add(articleList.get(i));
        }
        return articleList2;
    }

    /**
    * @Description:  获取从beginIndex开始，size条数的文章
    * @Param: [beginIndex, size]
    * @return: java.util.List<com.example.demo.entity.Article>
    * @Author: Lili Chen
    * @Date: 2018/12/20
    */
 /*   @Override
    public List<ArticleCustom> findArticleListIndex(int beginIndex, int size) {
        *//*Map map=new HashMap<>();
        map.put("beginIndex",beginIndex);
        map.put("size",size);*//*
        List<ArticleCustom> articleList= articleDao.findArticleListIndex();
        List<ArticleCustom> articleList2= new ArrayList<ArticleCustom>();
        Collections.sort(articleList);
       for(int i=beginIndex;i<size+beginIndex&&i<articleList.size();i++){
           articleList2.add(articleList.get(i));
       }
        return articleList2;
    }*/
}
