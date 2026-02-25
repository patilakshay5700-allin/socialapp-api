package com.socialapp.service;

import com.socialapp.dto.PostRequest;
import com.socialapp.dto.PostResponse;
import com.socialapp.model.Post;
import com.socialapp.model.User;
import com.socialapp.repository.PostRepository;
import com.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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

    // Helper — get user by email (used in many methods)
    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // CREATE POST
    public PostResponse createPost(PostRequest request, String email) {
        User user = getUserByEmail(email);
        Post post = new Post(request.getContent(), request.getImageUrl(), user);
        Post saved = postRepository.save(post);
        return PostResponse.fromPost(saved, user);
    }

    // GET ALL POSTS
    public List<PostResponse> getAllPosts(String email) {
        User currentUser = getUserByEmail(email);
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(post -> PostResponse.fromPost(post, currentUser))
                .collect(Collectors.toList());
    }

    // GET SINGLE POST
    public PostResponse getPostById(Long id, String email) {
        User currentUser = getUserByEmail(email);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return PostResponse.fromPost(post, currentUser);
    }

    // GET POSTS BY USER
    public List<PostResponse> getPostsByUser(Long userId, String email) {
        User currentUser = getUserByEmail(email);
        return postRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(post -> PostResponse.fromPost(post, currentUser))
                .collect(Collectors.toList());
    }

    // UPDATE POST
    public PostResponse updatePost(Long id, PostRequest request, String email) {
        User currentUser = getUserByEmail(email);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You can only edit your own posts");
        }

        post.setContent(request.getContent());
        post.setImageUrl(request.getImageUrl());
        Post updated = postRepository.save(post);
        return PostResponse.fromPost(updated, currentUser);
    }

    // DELETE POST
    public void deletePost(Long id, String email) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You can only delete your own posts");
        }

        postRepository.delete(post);
    }

    // LIKE POST
    public PostResponse likePost(Long postId, String email) {
        User currentUser = getUserByEmail(email);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Already liked?
        if (post.getLikedBy().contains(currentUser)) {
            throw new RuntimeException("You already liked this post");
        }

        post.getLikedBy().add(currentUser);
        Post saved = postRepository.save(post);
        return PostResponse.fromPost(saved, currentUser);
    }

    // UNLIKE POST
    public PostResponse unlikePost(Long postId, String email) {
        User currentUser = getUserByEmail(email);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Not liked yet?
        if (!post.getLikedBy().contains(currentUser)) {
            throw new RuntimeException("You have not liked this post");
        }

        post.getLikedBy().remove(currentUser);
        Post saved = postRepository.save(post);
        return PostResponse.fromPost(saved, currentUser);
    }

    // GET FEED
    public List<PostResponse> getFeed(String email) {
        User currentUser = getUserByEmail(email);

        // Get list of users that current user follows
        List<User> following = new ArrayList<>(currentUser.getFollowing());

        // If not following anyone return empty feed
        if (following.isEmpty()) {
            return new ArrayList<>();
        }

        // Get posts from followed users
        return postRepository.findFeedPosts(following)
                .stream()
                .map(post -> PostResponse.fromPost(post, currentUser))
                .collect(Collectors.toList());
    }
}
