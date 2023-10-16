package com.blogApp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private String commentId;
    private String commentBody;
    private LocalDateTime created;
    private LocalDateTime updated;
}
