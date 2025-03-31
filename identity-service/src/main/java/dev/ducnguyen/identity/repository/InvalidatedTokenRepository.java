package dev.ducnguyen.identity.repository;

import dev.ducnguyen.identity.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
//    boolean existsByToken(String token);
}