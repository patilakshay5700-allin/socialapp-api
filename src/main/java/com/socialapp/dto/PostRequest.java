package com.socialapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostRequest {

    @NotBlank(message = "Content is required")
    @Size(max = 500, message = "Post cannot exceed 500 characters")
    private String content;

    private String imageUrl;     // Optional

    public PostRequest() {}

    // Getters
    public String getContent() { return content; }
    public String getImageUrl() { return imageUrl; }

    // Setters
    public void setContent(String content) { this.content = content; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
