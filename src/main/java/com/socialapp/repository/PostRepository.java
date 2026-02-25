package com.socialapp.repository;

import com.socialapp.model.Post;
import com.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Get all posts by a specific user newest first
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);

    // Get all posts newest first
    List<Post> findAllByOrderByCreatedAtDesc();

    // FEED QUERY — get posts from users that the current user follows
    // "Give me all posts where the post's author is someone I follow"
    @Query("SELECT p FROM Post p WHERE p.user IN :following ORDER BY p.createdAt DESC")
    List<Post> findFeedPosts(@Param("following") List<User> following);
}
