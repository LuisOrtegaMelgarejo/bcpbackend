package com.bcp.exchangeRate.adapters.security;

import com.bcp.exchangeRate.application.domains.entities.UserBcp;
import com.bcp.exchangeRate.application.ports.out.UserRepositoryPort;
import com.bcp.exchangeRate.infrastructure.repository.SpringUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService, UserRepositoryPort {

    private SpringUserRepository repository;

    public UserService(SpringUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserBcp> userBcp = repository.findByUsername(username);
        if (!userBcp.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(userBcp.get().getUsername(), userBcp.get().getPassword(), new ArrayList<>());
    }
}
