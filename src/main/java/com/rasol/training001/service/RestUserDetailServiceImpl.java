package com.rasol.training001.service;

import com.rasol.training001.exception.NotFoundException;
import com.rasol.training001.model.dto.SecurityUser;
import com.rasol.training001.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestUserDetailServiceImpl implements RestUserDetailService {

    private final UserRepository userRepository;

    public RestUserDetailServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return new SecurityUser(userRepository.findById(id).orElseGet(() -> {
            throw NotFoundException.getUserNotFoundException();
        }));
    }
}