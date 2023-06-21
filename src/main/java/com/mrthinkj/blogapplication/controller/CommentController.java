package com.mrthinkj.blogapplication.controller;

import com.mrthinkj.blogapplication.payload.CommentDTO;
import com.mrthinkj.blogapplication.payload.CommentResponse;
import com.mrthinkj.blogapplication.service.impl.CommentServiceImpl;
import com.mrthinkj.blogapplication.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long postId,
                                                    @RequestBody CommentDTO commentDTO) {
        System.out.println(commentDTO);
        CommentDTO commentDTORes = commentService.createComment(postId, commentDTO);
        return new ResponseEntity<>(commentDTORes, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public CommentResponse getCommentsByPostId(@PathVariable(name = "postId") Long postId,
                                          @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                          @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                          @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                          @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return commentService.findAllComments(postId, pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentByIdAndPostId(@PathVariable Long postId,
                                                     @PathVariable Long id) {
        return ResponseEntity.ok(commentService.findCommentById(postId, id));
    }

    @PutMapping("/{postId}/comments/{id}")
    public CommentDTO updateCommentById(@PathVariable Long postId,
                                        @PathVariable Long id,
                                        @RequestBody CommentDTO commentDTO){
        return commentService.updateCommentById(postId, id, commentDTO);
    }

    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long postId,
                                                    @PathVariable Long id){
        commentService.deleteCommentById(postId, id);
        return new ResponseEntity<>("Comment entity deleted successfully", HttpStatus.OK);
    }
}
