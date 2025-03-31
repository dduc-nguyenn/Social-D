package dev.ducnguyen.identity.entity;

import dev.ducnguyen.identity.enums.FriendshipStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    FriendshipStatus status = FriendshipStatus.PENDING;

    @Column(nullable = false)
    LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    User user2;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
