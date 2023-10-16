package com.blogApp.services.Impl;

import com.blogApp.entities.Post;
import com.blogApp.payload.PostDTO;
import com.blogApp.repositories.PostRepository;
import com.blogApp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepo;

    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDTO createPost(PostDTO postDto) {
        String id = generatePostId(postDto.getLanguage());
        postDto.setPostId(id);

        Post post = modelMapper.map(postDto, Post.class);
        post = postRepo.save(post);

        return modelMapper.map(post, PostDTO.class);
    }

    public static String generatePostId(String language) {

        Random random = new Random();
        int length = 4;
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<length; i++) {
            sb.append(random.nextInt(10));
        }

        String lang = language.toUpperCase();
        return lang + sb.toString();
    }

    @Override
    public PostDTO getPostByPostId(String postId) {
        Post post = postRepo.findById(postId).orElse(null);
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPost() {
        List<Post> allPost = postRepo.findAll();

        List<PostDTO> allPostDto = allPost.stream().map((p) -> modelMapper.map(p, PostDTO.class)).collect(Collectors.toList());
        return allPostDto;
    }

    @Override
    public PostDTO updatePost(String postId, PostDTO postDTO) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("The given post is not found."));

        if(postDTO.getPostDescription() != null) {
            post.setPostDescription(postDTO.getPostDescription());
        }
        if(postDTO.getLanguage() != null) {
            post.setLanguage(postDTO.getLanguage());
        }

        Post savePost = postRepo.save(post);

        return modelMapper.map(savePost, PostDTO.class);

    }

    @Override
    public String deletePostById(String postId) {
        postId.equalsIgnoreCase(postId);
        Post post = postRepo.findById(postId).orElse(null);

        if(post == null) {
            return "The given id not available or invalid input.";
        } else{
            postRepo.delete(post);
            return "The post is deleted successfully!!!.";
        }
    }

    @Override
    public List<PostDTO> getAllPostByLanguage(String language) {
        List<Post> postByLanguage = postRepo.findAllPostByLanguage(language);
//        List<PostDTO> postByLanguage = postRepo.findPostByLanguage(language);
        List<PostDTO> postDTOList = postByLanguage.stream().map((p) -> modelMapper.map(p, PostDTO.class)).collect(Collectors.toList());
        return postDTOList;
    }

    @Override
    public PostDTO getPostByLanguage(String language) {
//        Post postByLanguage = postRepo.findPostByLanguage(postByLanguage.getLanguage().equalsIgnoreCase(language));
        Post postByLanguage = postRepo.findPostByLanguage(language);
        return modelMapper.map(postByLanguage, PostDTO.class);

    }
}
