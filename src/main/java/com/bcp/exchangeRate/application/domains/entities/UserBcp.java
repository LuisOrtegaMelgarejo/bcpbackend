package com.bcp.exchangeRate.application.domains.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "User")
public class UserBcp implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false)
    String username;
    @Column(name = "password", nullable = false)
    String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList permissions = new ArrayList<>();
        permissions.add("SUPERADMIN");
        return permissions;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}