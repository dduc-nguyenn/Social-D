package dev.ducnguyen.identity.repository;

import dev.ducnguyen.identity.entity.Friendship;
import dev.ducnguyen.identity.entity.User;
import dev.ducnguyen.identity.enums.FriendshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, String> {
//    boolean existsByUser1IdAndUser2Id(String user1, String user2);
//    Optional<Friendship> findByUser1IdAndUser2Id(String user1, String user2);

    List<Friendship> findByUser1AndStatus(User user1, FriendshipStatus status);
    List<Friendship> findByUser2AndStatus(User user2, FriendshipStatus status);

    Optional<Friendship> findByUser1AndUser2(User user1, User user2);
}
