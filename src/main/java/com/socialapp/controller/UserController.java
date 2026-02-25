package com.socialapp.controller;


import com.socialapp.dto.UserProfileResponse;
import com.socialapp.dto.UserSummaryResponse;
import com.socialapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Users", description = "User profiles, follow and unfollow")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET USER PROFILE
    @Operation(summary = "Get user profile with follower counts")
    @GetMapping("/{id}/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserProfile(id));
    }

    // FOLLOW USER
    @Operation(summary = "Follow a user")
    @PostMapping("/{id}/follow")
    public ResponseEntity<String> followUser(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        String message = userService.followUser(
                id,
                userDetails.getUsername()
        );
        return ResponseEntity.ok(message);
    }

    // UNFOLLOW USER
    @Operation(summary = "Unfollow a user")
    @DeleteMapping("/{id}/follow")
    public ResponseEntity<String> unfollowUser(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        String message = userService.unfollowUser(
                id,
                userDetails.getUsername()
        );
        return ResponseEntity.ok(message);
    }

    // GET FOLLOWERS
    @GetMapping("/{id}/followers")
    public ResponseEntity<List<UserSummaryResponse>> getFollowers(
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.getFollowers(id));
    }

    // GET FOLLOWING
    @GetMapping("/{id}/following")
    public ResponseEntity<List<UserSummaryResponse>> getFollowing(
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.getFollowing(id));
    }
}

