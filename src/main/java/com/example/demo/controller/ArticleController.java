package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;
import com.example.demo.service.ScoreService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/myArticle")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private ScoreService scoreService;
    @Resource
    private UserService userService;
    @Resource
    private CommentService commentService;

    /**
    * @Description:  用户主界面
    * @Param: [model, score, uId]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @RequestMapping("userMain")
    public String userMain(Model model, Integer score, Integer uId) throws Exception {
        List<ArticleCustom> articleList=articleService.findArticleListIndexByUid(0,4,null);
        model.addAttribute("uId", uId);
        model.addAttribute("articleList", articleList);
        model.addAttribute("score",score);
        model.addAttribute("page",1);
        return "userMain";
    }


    /**
    * @Description: 跳转到增加文章的页面
    * @Param: [model, uId]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @RequestMapping("preAddArticle")
    public String preAddArticle(Model model, Integer uId) throws Exception {
        Score score=scoreService.queryScore(uId);
        model.addAttribute("uId", uId);
        model.addAttribute("score",score.getSc());
        return "addInformation";
    }


    /**
    * @Description: 增加文章
    * @Param: [model, article]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @RequestMapping("addArticle")
    public String addInformation(Model model, Article article) throws Exception {
        Date date = new Date();
        article.setCreatetime(date);
        article.setTitle(article.getTitle());
        article.setContent(article.getContent());
        boolean b = articleService.addArticle(article);
        if (b) {
            Score score=scoreService.queryScore(article.getuId());
            return "redirect:getMyArticle?&uId="+article.getuId();
        }
        model.addAttribute("uId", article.getuId());
        model.addAttribute("missMessage", "添加失败");
        return "addInformation";

    }


    /**
    * @Description:  删除文章
    * @Param: [model, articleId]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @RequestMapping("deleteArticle")
    public @ResponseBody
    String deleteArticle(Model model, Integer articleId) throws Exception {
        boolean b = articleService.deleteArticle(articleId);
        if (b) {
            return "success";
        }
        return "fail";
    }

    /**
    * @Description: 跳转到编辑页面
    * @Param: [model, articleId]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @RequestMapping("preEditArticle")
    public String preEditArticle(Model model, Integer articleId) throws Exception {
        Article article=articleService.queryArticleById(articleId);
        Score score=scoreService.queryScore(article.getuId());
        model.addAttribute("article",article);
        model.addAttribute("score",score.getSc());
        return "edit";
    }

    /**
    * @Description:  修改消息
    * @Param: [model, article]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */
    @RequestMapping("editArticle")
    public @ResponseBody
    String editInformation(Model model, Article article) throws Exception {
        boolean b = articleService.updateArticle(article);
        if (b) {
            return "success";
        }
        return "fail";
    }

    /**
    * @Description: 有关分页的操作分页的操作
    * @Param: [model, page, u_id]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/17
    */

    @RequestMapping("queryArticleList")
    public String queryArticleList(Model model,Integer page,Integer uId) throws Exception {
        int beginIndex=0;
        int size=4;
        int count=articleService.queryArticleList().size();
       // int count=articleService.getCount(uId);//总共的记录
        int pageCount=count/4;//总共的页数
        if(count%4!=0){
            pageCount++;
        }
        if(page<1){
            page=1;
        }
        if(page>pageCount){
            page=pageCount;
        }
        beginIndex=(page-1)*size;
        List<ArticleCustom> articleList = articleService.findArticleListIndexByUid(beginIndex,size,null);
        model.addAttribute("articleList", articleList); //当前页的消息列表
        model.addAttribute("page",page);//当前的页数
        model.addAttribute("pageCount", pageCount);//总共的页数

        Score score=scoreService.queryScore(uId);
        User user=userService.queryUserById(uId);
        model.addAttribute("phone",user.getPhone());
        model.addAttribute("message"," ");
        model.addAttribute("uId",user.getId());
        model.addAttribute("score",score.getSc());
        return "firstPage";

    }


    @RequestMapping("articleDetails")
    public String articleDetails(Model model, Integer articleId,Integer uId) throws Exception {
        Article article=articleService.queryArticleById(articleId);
        List<CommentCustom> commentList=commentService.commentListByArticleId(articleId);
        Score score=scoreService.queryScore(uId);
        model.addAttribute("commentList",commentList);
        model.addAttribute("article",article);
        model.addAttribute("uId",uId);
        model.addAttribute("score",score.getSc());
        return "articleDetails";
    }




    @RequestMapping("queryArticleListByUid")
    public String queryArticleListByUid(Model model,Integer page,Integer uId) throws Exception {
        int beginIndex=0;
        int size=4;
        int count=articleService.queryArticleByUid(uId).size();
        int pageCount=count/4;//总共的页数
        if(count%4!=0){
            pageCount++;
        }
        if(page<1){
            page=1;
        }
        if(page>pageCount){
            page=pageCount;
        }
        beginIndex=(page-1)*size;
        List<ArticleCustom> articleList = articleService.findArticleListIndexByUid(beginIndex,4,uId);
        model.addAttribute("articleList", articleList); //当前页的消息列表
        model.addAttribute("page",page);//当前的页数
        model.addAttribute("pageCount", pageCount);//总共的页数

        Score score=scoreService.queryScore(uId);
        User user=userService.queryUserById(uId);
        model.addAttribute("uId",user.getId());
        return "myArticle";

    }

    @RequestMapping("getMyArticle")
    public String  myArticle(Integer uId,Model model)throws Exception {
        List<ArticleCustom> articleList=articleService.findArticleListIndexByUid(0,4,uId);
        int count=articleService.queryArticleByUid(uId).size();
        int pageCount=count/4;
        if(count%4!=0){
            pageCount++;
        }
        model.addAttribute("uId",uId);
        model.addAttribute("articleList",articleList);
        model.addAttribute("page",1);
        model.addAttribute("pageCount", pageCount);//总共的页数
        return "myArticle";
    }
}
