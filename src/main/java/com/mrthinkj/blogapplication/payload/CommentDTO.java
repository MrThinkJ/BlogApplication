package com.mrthinkj.blogapplication.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    @NotEmpty
    @Size(min = 5, message = "Name should be at least 5 characters")
    private String name;

    @NotEmpty
    @Email(message = "Wrong format of email")
    private String email;

    @NotEmpty
    @Size(min = 5, message = "Comment body should be at least 5 characters")
    private String body;
}
