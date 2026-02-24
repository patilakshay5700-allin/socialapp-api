package com.socialapp.dto;


import com.socialapp.model.User;

public class UserProfileResponse {

    private Long id;
    private String name;
    private String email;
    private String bio;
    private String profilePicture;
    private int followersCount;
    private int followingCount;

    public UserProfileResponse() {}

    public static UserProfileResponse fromUser(User user) {
        UserProfileResponse response = new UserProfileResponse();
        response.id = user.getId();
        response.name = user.getName();
        response.email = user.getEmail();
        response.bio = user.getBio();
        response.profilePicture = user.getProfilePicture();
        response.followersCount = user.getFollowers().size();
        response.followingCount = user.getFollowing().size();
        return response;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }
    public String getProfilePicture() { return profilePicture; }
    public int getFollowersCount() { return followersCount; }
    public int getFollowingCount() { return followingCount; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setBio(String bio) { this.bio = bio; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
    public void setFollowersCount(int followersCount) { this.followersCount = followersCount; }
    public void setFollowingCount(int followingCount) { this.followingCount = followingCount; }
}
