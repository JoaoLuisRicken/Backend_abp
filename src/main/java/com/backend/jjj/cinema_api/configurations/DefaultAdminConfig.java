package com.backend.jjj.cinema_api.configurations;

import com.backend.jjj.cinema_api.enums.UserRole;
import com.backend.jjj.cinema_api.models.UsersModel;
import com.backend.jjj.cinema_api.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class DefaultAdminConfig implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DefaultAdminConfig.class);

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // verifica se já existe um admin
        if (usersRepository.findByEmail("admin@cinema.com") == null) {
            UsersModel admin = new UsersModel();
            admin.setName("Administrador");
            admin.setEmail("admin@cinema.com");
            admin.setBirthday(LocalDate.of(2005,9,22));
            admin.setPassword(passwordEncoder.encode("admin123")); // senha padrão
            admin.setRole(UserRole.ADMIN);
            usersRepository.save(admin);
            log.info("✅ Usuário admin criado: {} / {}", admin.getEmail(), "admin123");
        } else {
            log.info("ℹ️ Usuário admin já existe, não será recriado.");
        }
    }
}
