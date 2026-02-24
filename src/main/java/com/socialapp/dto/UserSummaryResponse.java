package com.socialapp.dto;


import com.socialapp.model.User;

public class UserSummaryResponse {

    private Long id;
    private String name;
    private String email;
    private String profilePicture;

    public UserSummaryResponse() {}

    public static UserSummaryResponse fromUser(User user) {
        UserSummaryResponse response = new UserSummaryResponse();
        response.id = user.getId();
        response.name = user.getName();
        response.email = user.getEmail();
        response.profilePicture = user.getProfilePicture();
        return response;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getProfilePicture() { return profilePicture; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
}
