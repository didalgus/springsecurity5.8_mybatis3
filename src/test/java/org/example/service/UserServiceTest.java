package org.example.service;

import org.example.entity.UserEntity;
import org.example.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    private UserService userService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userMapper);
    }

    @Test
    public void 사용자_목록_조회() {

        when(userMapper.findAll()).thenReturn(Collections.singletonList(new UserEntity()));
        List<UserEntity> list = userService.findAll();

        assertNotNull(list.size());
    }

    @Test
    public void 사용자_등록() {

        when(userMapper.save(any())).thenReturn(1);
        String result = userService.save(any());

        assertEquals(result, "OK");
    }

}