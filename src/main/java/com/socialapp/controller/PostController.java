package com.socialapp.controller;

import com.socialapp.dto.PostRequest;
import com.socialapp.dto.PostResponse;
import com.socialapp.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "Posts", description = "Create, read, update, delete posts and likes")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // CREATE POST
    @Operation(summary = "Create a new post")
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @Valid @RequestBody PostRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(request, userDetails.getUsername()));
    }

    // GET ALL POSTS
    @Operation(summary = "Get all posts")
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                postService.getAllPosts(userDetails.getUsername()));
    }

    // GET FEED — posts from people you follow
    @Operation(summary = "Get your feed - posts from people you follow")
    @GetMapping("/feed")
    public ResponseEntity<List<PostResponse>> getFeed(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                postService.getFeed(userDetails.getUsername()));
    }

    // GET SINGLE POST
    @Operation(summary = "Like a post")
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                postService.getPostById(id, userDetails.getUsername()));
    }

    // GET POSTS BY USER
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> getPostsByUser(
            @PathVariable Long userId,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                postService.getPostsByUser(userId, userDetails.getUsername()));
    }

    // UPDATE POST
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                postService.updatePost(id, request, userDetails.getUsername()));
    }

    // DELETE POST
    @Operation(summary = "Unlike a post")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        postService.deletePost(id, userDetails.getUsername());
        return ResponseEntity.ok("Post deleted successfully");
    }

    // LIKE POST
    @PostMapping("/{id}/like")
    public ResponseEntity<PostResponse> likePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                postService.likePost(id, userDetails.getUsername()));
    }

    // UNLIKE POST
    @DeleteMapping("/{id}/like")
    public ResponseEntity<PostResponse> unlikePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                postService.unlikePost(id, userDetails.getUsername()));
    }
}