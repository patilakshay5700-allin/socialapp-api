package com.socialapp.dto;


import com.socialapp.model.Post;
import java.time.LocalDateTime;

public class PostResponse {

    private Long id;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Author info — we don't expose the whole User object
    private Long userId;
    private String userName;

    public PostResponse() {}

    // Static factory method — converts Post entity to PostResponse DTO
    public static PostResponse fromPost(Post post) {
        PostResponse response = new PostResponse();
        response.id = post.getId();
        response.content = post.getContent();
        response.imageUrl = post.getImageUrl();
        response.createdAt = post.getCreatedAt();
        response.updatedAt = post.getUpdatedAt();
        response.userId = post.getUser().getId();
        response.userName = post.getUser().getName();
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

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setUserName(String userName) { this.userName = userName; }
}
