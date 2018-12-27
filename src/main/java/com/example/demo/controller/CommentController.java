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


    @RequestMapping("addComment")
    public @ResponseBody
    String addComment(Model model,Integer articleId,Integer uId,String content){
        Comment comment=new Comment();
        Date date=new Date();
        comment.setArticleId(articleId);
        comment.setCommentCreatetime(date);
        comment.setContent(content);
        comment.setuId(uId);
        boolean b=commentService.addComment(comment);
        if (b) {
            return "success";
        }
        return "fail";
    }



    @RequestMapping("deleteComment")
    public @ResponseBody
    String deleteComment(Model model,Integer commentId){
        boolean b=commentService.deleteComment(commentId);
        if (b) {
            return "success";
        }
        return "fail";
    }
}
