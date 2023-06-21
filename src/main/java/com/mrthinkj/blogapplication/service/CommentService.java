package com.mrthinkj.blogapplication.service;

import com.mrthinkj.blogapplication.payload.CommentDTO;
import com.mrthinkj.blogapplication.payload.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(Long postId, CommentDTO commentDTO);
    CommentResponse findAllComments(Long postId, int pageNo, int pageSize, String sortBy, String sortDir);
    CommentDTO findCommentById(Long postId, Long id);
    CommentDTO updateCommentById(Long postId, Long id, CommentDTO commentDTO);
    void deleteCommentById(Long postId, Long id);
}
