package com.rasol.training001.service;

import com.rasol.training001.exception.ConflictException;
import com.rasol.training001.exception.NotFoundException;
import com.rasol.training001.exception.UnAuthorizedException;
import com.rasol.training001.model.dto.User;
import com.rasol.training001.model.entity.UserEntity;
import com.rasol.training001.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity().setId(user.getId()).setPassword(this.encryptPassword(user.getPassword()));
        userRepository.findById(user.getId()).ifPresent( k -> {
            throw ConflictException.getUserAlreadyExistsException();
        });

        userRepository.save(userEntity);

        return null;
    }

    private String encryptPassword(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        md.update(password.getBytes());

        return new String(md.digest());
    }

    @Override
    public User loginUser(User user){
        UserEntity userEntity = userRepository.findById(user.getId()).orElseGet(() -> {
            throw NotFoundException.getUserNotFoundException();
        });
        if(!userEntity.getPassword().equals(this.encryptPassword(user.getPassword()))){
            throw UnAuthorizedException.getUserPasswordIsNotCorrectException();
        }

        // need update modifiedDate
        userRepository.save(userEntity);

        return null;
    }
}
