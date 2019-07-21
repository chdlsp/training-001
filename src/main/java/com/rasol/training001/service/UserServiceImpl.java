package com.rasol.training001.service;

import com.rasol.training001.exception.ConflictException;
import com.rasol.training001.exception.NotFoundException;
import com.rasol.training001.exception.UnAuthorizedException;
import com.rasol.training001.model.dto.User;
import com.rasol.training001.model.entity.UserEntity;
import com.rasol.training001.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity().setId(user.getId()).setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.findById(user.getId()).ifPresent( k -> {
            throw ConflictException.getUserAlreadyExistsException();
        });

        userRepository.save(userEntity);

        return null;
    }

//    private String encryptPassword(String password){
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("SHA-256");
//        }catch(NoSuchAlgorithmException e){
//            e.printStackTrace();
//        }
//        md.update(password.getBytes());
//
//        return new String(md.digest());
//    }

    @Override
    public User loginUser(User user, HttpServletRequest request, HttpSession httpSession){
        UserEntity userEntity = userRepository.findById(user.getId()).orElseGet(() -> {
            throw NotFoundException.getUserNotFoundException();
        });

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getId(), user.getPassword());
        final Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

//         need update modifiedDate
        userRepository.save(userEntity);

        return null;
    }

}
