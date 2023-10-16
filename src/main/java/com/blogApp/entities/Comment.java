package com.blogApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("comments")
public class Comment {
    @Id
    private String commentId;

    private String commentBody;

    private LocalDateTime created;

    private LocalDateTime updated;
}
