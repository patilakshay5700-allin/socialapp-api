package com.socialapp.service;


import com.socialapp.dto.UserProfileResponse;
import com.socialapp.dto.UserSummaryResponse;
import com.socialapp.model.User;
import com.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET USER PROFILE
    public UserProfileResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserProfileResponse.fromUser(user);
    }

    // FOLLOW A USER
    public String followUser(Long userIdToFollow, String currentUserEmail) {

        // Get logged in user
        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get user to follow
        User userToFollow = userRepository.findById(userIdToFollow)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Can't follow yourself
        if (currentUser.getId().equals(userIdToFollow)) {
            throw new RuntimeException("You cannot follow yourself");
        }

        // Already following?
        if (currentUser.getFollowing().contains(userToFollow)) {
            throw new RuntimeException("You are already following this user");
        }

        // Add to following set
        currentUser.getFollowing().add(userToFollow);
        userRepository.save(currentUser);

        return "You are now following " + userToFollow.getName();
    }

    // UNFOLLOW A USER
    public String unfollowUser(Long userIdToUnfollow, String currentUserEmail) {

        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User userToUnfollow = userRepository.findById(userIdToUnfollow)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Not following?
        if (!currentUser.getFollowing().contains(userToUnfollow)) {
            throw new RuntimeException("You are not following this user");
        }

        // Remove from following set
        currentUser.getFollowing().remove(userToUnfollow);
        userRepository.save(currentUser);

        return "You have unfollowed " + userToUnfollow.getName();
    }

    // GET FOLLOWERS LIST
    public List<UserSummaryResponse> getFollowers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getFollowers()
                .stream()
                .map(UserSummaryResponse::fromUser)
                .collect(Collectors.toList());
    }

    // GET FOLLOWING LIST
    public List<UserSummaryResponse> getFollowing(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getFollowing()
                .stream()
                .map(UserSummaryResponse::fromUser)
                .collect(Collectors.toList());
    }
}
