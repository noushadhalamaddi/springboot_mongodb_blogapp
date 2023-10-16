package com.blogApp.services;

import com.blogApp.payload.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDto);

    PostDTO getPostByPostId(String postId);

    List<PostDTO> getAllPost();

    String deletePostById(String postId);

    List<PostDTO> getAllPostByLanguage(String language);

    PostDTO updatePost(String postId, PostDTO postDTO);

    PostDTO getPostByLanguage(String language);
}
