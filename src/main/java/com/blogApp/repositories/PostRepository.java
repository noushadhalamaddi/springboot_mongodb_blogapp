package com.blogApp.repositories;

import com.blogApp.entities.Post;
import com.blogApp.payload.PostDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    Post findPostByLanguage(String language);

    List<Post> findAllPostByLanguage(String language);
}
