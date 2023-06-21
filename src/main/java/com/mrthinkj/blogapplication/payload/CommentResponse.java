package com.mrthinkj.blogapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private List<CommentDTO> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPage;
    private boolean isLastPage;
}
