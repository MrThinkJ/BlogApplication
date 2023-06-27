package com.mrthinkj.blogapplication.payload;

import com.mrthinkj.blogapplication.entity.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private long id;
    @NotEmpty
    @Size(min = 5, message = "Category name should be at least 5 characters")
    private String name;
    @NotEmpty
    @Size(min = 10, message = "Category description should be at least 10 characters")
    private String description;
    private List<Post> posts;
}
