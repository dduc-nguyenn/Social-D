package dev.ducnguyen.identity.repository;

import dev.ducnguyen.identity.entity.Post;
import dev.ducnguyen.identity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findByUserId(String userId);

    List<Post> findByUserInAndCreatedAtAfter(List<User> users, LocalDateTime createdAt);
}
