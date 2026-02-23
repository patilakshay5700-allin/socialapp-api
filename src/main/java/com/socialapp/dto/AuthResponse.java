package com.socialapp.dto;

import lombok.Data;

@Data
public class AuthResponse {

    private String token;
    private String email;
    private String name;

    public AuthResponse() {}

    public AuthResponse(String token, String email, String name) {
        this.token = token;
        this.email = email;
        this.name = name;
    }

    // Getters — Jackson uses these to write JSON
    public String getToken() { return token; }
    public String getEmail() { return email; }
    public String getName() { return name; }

    // Setters
    public void setToken(String token) { this.token = token; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
}