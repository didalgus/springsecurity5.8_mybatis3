package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.SignInRequest;
import org.example.dto.SignUpRequest;
import org.example.entity.UserEntity;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;

    @GetMapping("/user/signin")
    public String signIn(SignInRequest signInRequest) {
        UserEntity user = userService.signIn(signInRequest);
        return user.getPassword();
    }

    @PostMapping("/user/signup")
    public String signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        String result = userService.save(signUpRequest);
        return result;
    }

    @GetMapping("/user/list")
    public List<UserEntity> listUser() {
        List<UserEntity> data = userService.findAll();
        return data;
    }
}
