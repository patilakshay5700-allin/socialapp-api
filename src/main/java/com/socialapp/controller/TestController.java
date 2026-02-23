package com.socialapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("This is public — anyone can access this!");
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedRoute(
            @AuthenticationPrincipal UserDetails userDetails) {

        // @AuthenticationPrincipal automatically gives us the logged in user
        return ResponseEntity.ok(
                "✅ JWT Working! You are logged in as: "
                        + userDetails.getUsername()
        );
    }
}
