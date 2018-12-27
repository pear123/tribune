package com.example.demo.entity;

import java.util.Date;

public class Article implements Comparable<Article> {
    private Integer articleId;
    private Date createtime;
    private String title;
    private String content;
    private Integer uId;
    private Integer commentCount;
    private Date lastCommentCreatetime;


    public Article() {
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Date getLastCommentCreatetime() {
        return lastCommentCreatetime;
    }

    public void setLastCommentCreatetime(Date lastCommentCreatetime) {
        this.lastCommentCreatetime = lastCommentCreatetime;
    }

    @Override
    public int compareTo(Article o) {
        int i=0;
        if(o.getLastCommentCreatetime()==null){
            if(this.getLastCommentCreatetime()==null){
                i=o.getCreatetime().compareTo(this.createtime);
            }else{
                i=o.getCreatetime().compareTo(this.getLastCommentCreatetime());
            }
        }else{
            if(this.getLastCommentCreatetime()==null){
                i=o.getLastCommentCreatetime().compareTo(this.getCreatetime());
            }else{
                i=o.getLastCommentCreatetime().compareTo(this.getLastCommentCreatetime());
                if(i==0){
                    return o.getCreatetime().compareTo(this.getCreatetime());
                }
            }

        }
        return i;
    }
}
