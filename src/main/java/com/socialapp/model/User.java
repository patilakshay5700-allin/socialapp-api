package com.socialapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String bio;

    private String profilePicture;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Users that THIS user is following
    // "I am following these people"
    @ManyToMany
    @JoinTable(
            name = "follows",                            // join table name
            joinColumns = @JoinColumn(name = "follower_id"),    // this user
            inverseJoinColumns = @JoinColumn(name = "following_id") // who they follow
    )
    private Set<User> following = new HashSet<>();

    // Users that are following THIS user
    // "These people follow me"
    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructors
    public User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getBio() { return bio; }
    public String getProfilePicture() { return profilePicture; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Set<User> getFollowing() { return following; }
    public Set<User> getFollowers() { return followers; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setBio(String bio) { this.bio = bio; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setFollowing(Set<User> following) { this.following = following; }
    public void setFollowers(Set<User> followers) { this.followers = followers; }
}