package com.blogApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("techBlogApp")
public class Post {

    @Id
    private String postId;

    private String postTitle;

    private String language;

    private String postDescription;

    @DocumentReference
    private List<Comment> comments = new ArrayList<>();


}
