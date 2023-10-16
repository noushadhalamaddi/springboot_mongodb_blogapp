package com.blogApp.controllers;

import com.blogApp.payload.PostDTO;
import com.blogApp.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/home")
    public String get() {
        return "Welcome to BlogApp - POSTS";
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        PostDTO post = postService.createPost(postDTO);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable String postId) {
        String upperCase = postId.toUpperCase();
        if(!upperCase.matches("^(?=.*[A-Z])(?=.*\\d).+$")){
            return ResponseEntity.badRequest().build();
        }
        PostDTO byPostId = postService.getPostByPostId(upperCase);
        return new ResponseEntity<>(byPostId, HttpStatus.FOUND);
    }


    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts () {
        List<PostDTO> allPost = postService.getAllPost();
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePostById(@PathVariable String postId) {
        postService.deletePostById(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePostById(@PathVariable String postId, @RequestBody PostDTO postDTO) {
        String upperCase = postId.toUpperCase();
        if(!upperCase.matches("^(?=.*[A-Z])(?=.*\\d).+$")){
            return new ResponseEntity("No post find for this given Id", HttpStatus.BAD_REQUEST);
        }
        PostDTO updatePost = postService.updatePost(postId, postDTO);
        return new ResponseEntity<>(updatePost, HttpStatus.CREATED);
    }

    @GetMapping("/allposts/language/{language}")
    public ResponseEntity<List<PostDTO>> getAllPostByLanguage(@PathVariable String language) {
        List<PostDTO> postByLanguage = postService.getAllPostByLanguage(language);
        return new ResponseEntity<>(postByLanguage, HttpStatus.FOUND);
    }

    @GetMapping("/language/{lang}")
    public ResponseEntity<PostDTO> getPostByLanguage(@PathVariable("lang") String language) {
        PostDTO postByLanguage = postService.getPostByLanguage(language);
        return new ResponseEntity<>(postByLanguage, HttpStatus.FOUND);
    }
}
