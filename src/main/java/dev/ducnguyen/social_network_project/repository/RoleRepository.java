package dev.ducnguyen.social_network_project.repository;

import dev.ducnguyen.social_network_project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
