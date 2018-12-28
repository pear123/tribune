package com.example.demo.controller;


import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("myComment")
public class CommentController {
    @Resource
    private CommentService commentService;


    /** 
    * @Description: 评论文章
    * @Param: [model, articleId, uId, content] 
    * @return: java.lang.String 
    * @Author: Lili Chen 
    * @Date: 2018/12/27 
    */
    @RequestMapping("addComment")
    public @ResponseBody
    String addComment(Model model,Integer articleId,Integer uId,String content){
        Comment comment=new Comment();
        Date date=new Date();
        comment.setArticleId(articleId);
        comment.setCommentCreatetime(date);
        comment.setContent(content);
        comment.setuId(uId);
        comment.setToUid(null);
        boolean b=commentService.addComment(comment);
        if (b) {
            return "success";
        }
        return "fail";
    }


   /** 
   * @Description: 删除评论 
   * @Param: [model, commentId] 
   * @return: java.lang.String 
   * @Author: Lili Chen 
   * @Date: 2018/12/27 
   */
    @RequestMapping("deleteComment")
    public @ResponseBody
    String deleteComment(Model model,Integer commentId){
        boolean b=commentService.deleteComment(commentId);
        if (b) {
            return "success";
        }
        return "fail";
    }

    /** 
    * @Description: 回复评论 
    * @Param: [model, articleId, uId, content, commentUid] 
    * @return: java.lang.String 
    * @Author: Lili Chen 
    * @Date: 2018/12/27 
    */
    @RequestMapping("replyComment")
    public @ResponseBody
    String replyComment(Model model,Integer articleId,Integer uId,String content,Integer commentUid){
        Date date=new Date();
        Comment comment=new Comment();
        comment.setArticleId(articleId);
        comment.setCommentCreatetime(date);
        comment.setContent(content);
        comment.setuId(uId);
        comment.setToUid(commentUid);
        boolean b=commentService.addComment(comment);
        if (b) {
            return "success";
        }
        return "fail";
    }

}
