package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.SignInRequest;
import org.example.dto.SignUpRequest;
import org.example.entity.UserEntity;
import org.example.mapper.UserMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    public UserEntity signIn(SignInRequest signInRequest) {
        return userMapper.findByUser(signInRequest);
    }

    public String save(SignUpRequest signUpRequest) {

        if (userMapper.save(signUpRequest) == 1) {
            return "OK";
        } else {
            return "FAIL";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserEntity> findAll() {
        return userMapper.findAll();
    }

}
