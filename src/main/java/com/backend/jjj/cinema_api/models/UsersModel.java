package com.backend.jjj.cinema_api.models;

import com.backend.jjj.cinema_api.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsersModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String name;
    private String email;
    private String password;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private UserRole role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role==UserRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("CLIENT"),new SimpleGrantedAuthority("EMPLOYEE"));
        }else if (this.role==UserRole.EMPLOYEE){
            return List.of(new SimpleGrantedAuthority("CLIENT"),new SimpleGrantedAuthority("EMPLOYEE"));
        }
        return List.of(new SimpleGrantedAuthority("CLIENT"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
