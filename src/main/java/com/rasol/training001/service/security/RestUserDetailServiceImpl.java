package com.rasol.training001.service.security;

import com.rasol.training001.exception.NotFoundException;
import com.rasol.training001.model.dto.SecurityUser;
import com.rasol.training001.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestUserDetailServiceImpl implements RestUserDetailService {

    private final UserRepository userRepository;

    @Autowired
    public RestUserDetailServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return new SecurityUser(userRepository.findById(userId).orElseGet(() -> {
            throw NotFoundException.getUserNotFoundException();
        }));
    }


}
