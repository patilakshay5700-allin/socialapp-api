package com.socialapp.repository;


import com.socialapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Get all posts by a specific user ordered by newest first
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);

    // Get all posts ordered by newest first (for feed)
    List<Post> findAllByOrderByCreatedAtDesc();
}
