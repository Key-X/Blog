package com.xxx.blog.service;

import com.xxx.blog.po.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> lsitCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

}
