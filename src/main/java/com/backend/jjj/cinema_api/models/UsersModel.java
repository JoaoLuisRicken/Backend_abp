package com.backend.jjj.cinema_api.models;

import com.backend.jjj.cinema_api.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String name;
    private String email;
    private String password;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private UserRole role;


}
