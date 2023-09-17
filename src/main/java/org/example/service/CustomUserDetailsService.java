package org.example.service;

import org.example.entity.UserEntity;
import org.example.mapper.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        UserEntity user = userMapper.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

      User return_user = new User(user.getId(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getAuthority())));
      return return_user;
    }
}
