package dev.ducnguyen.identity.configuration;

import dev.ducnguyen.identity.constant.RoleConstant;
import dev.ducnguyen.identity.entity.Role;
import dev.ducnguyen.identity.entity.User;
import dev.ducnguyen.identity.enums.Gender;
import dev.ducnguyen.identity.repository.RoleRepository;
import dev.ducnguyen.identity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(RoleConstant.USER_ROLE)
                        .description("User role")
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .name(RoleConstant.ADMIN_ROLE)
                        .description("Admin role")
                        .build());

                var roles = new HashSet<Role>();
                roles.add(adminRole);

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .email("admin@gmail.com")
                        .firstName("ad")
                        .surName("min")
                        .gender(Gender.OTHER)
                        .roles(roles)
                        .build();

                userRepository.save(user);

                log.warn("Admin user has been created with default password: \"admin\", please change it");
            }
        };
    };
}
