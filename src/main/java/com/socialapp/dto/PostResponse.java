package com.socialapp.dto;

import com.socialapp.model.Post;
import com.socialapp.model.User;
import java.time.LocalDateTime;

public class PostResponse {

    private Long id;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
    private String userName;
    private int likesCount;       // total likes on this post
    private boolean isLiked;      // did the current logged in user like this?

    public PostResponse() {}

    // Basic conversion without isLiked info
    public static PostResponse fromPost(Post post) {
        PostResponse response = new PostResponse();
        response.id = post.getId();
        response.content = post.getContent();
        response.imageUrl = post.getImageUrl();
        response.createdAt = post.getCreatedAt();
        response.updatedAt = post.getUpdatedAt();
        response.userId = post.getUser().getId();
        response.userName = post.getUser().getName();
        response.likesCount = post.getLikedBy().size();
        response.isLiked = false;
        return response;
    }

    // Full conversion WITH isLiked info (pass current logged in user)
    public static PostResponse fromPost(Post post, User currentUser) {
        PostResponse response = fromPost(post);
        response.isLiked = post.getLikedBy().contains(currentUser);
        return response;
    }

    // Getters
    public Long getId() { return id; }
    public String getContent() { return content; }
    public String getImageUrl() { return imageUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Long getUserId() { return userId; }
    public String getUserName() { return userName; }
    public int getLikesCount() { return likesCount; }
    public boolean isLiked() { return isLiked; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setLikesCount(int likesCount) { this.likesCount = likesCount; }
    public void setLiked(boolean liked) { this.isLiked = liked; }
}
