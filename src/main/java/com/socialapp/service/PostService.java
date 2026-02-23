package com.socialapp.service;


import com.socialapp.dto.PostRequest;
import com.socialapp.dto.PostResponse;
import com.socialapp.model.Post;
import com.socialapp.model.User;
import com.socialapp.repository.PostRepository;
import com.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public PostResponse createPost(PostRequest request, String email) {

        // Find the logged in user from DB
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post(
                request.getContent(),
                request.getImageUrl(),
                user
        );

        Post saved = postRepository.save(post);
        return PostResponse.fromPost(saved);
    }

    // GET ALL POSTS
    public List<PostResponse> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostResponse::fromPost)   // convert each Post → PostResponse
                .collect(Collectors.toList());
    }

    // GET SINGLE POST
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return PostResponse.fromPost(post);
    }

    // GET POSTS BY USER
    public List<PostResponse> getPostsByUser(Long userId) {
        return postRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(PostResponse::fromPost)
                .collect(Collectors.toList());
    }

    // UPDATE
    public PostResponse updatePost(Long id, PostRequest request, String email) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Check if the logged in user owns this post
        if (!post.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You can only edit your own posts");
        }

        post.setContent(request.getContent());
        post.setImageUrl(request.getImageUrl());

        Post updated = postRepository.save(post);
        return PostResponse.fromPost(updated);
    }

    // DELETE
    public void deletePost(Long id, String email) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Check if the logged in user owns this post
        if (!post.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You can only delete your own posts");
        }

        postRepository.delete(post);
    }
}
