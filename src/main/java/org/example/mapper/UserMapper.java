package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dto.SignInRequest;
import org.example.dto.SignUpRequest;
import org.example.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id=#{userId} AND password=#{userPassword}")
    UserEntity findByUser(SignInRequest signInRequest);

    @Select("SELECT * FROM user WHERE id=#{userId}")
    Optional<UserEntity> findById(String userId);

    @Insert("INSERT INTO user (id, name, password, authority) VALUES (#{userId}, #{userName}, #{userPassword}, #{userAuthority})")
    int save(SignUpRequest signUpRequest);

    @Select("SELECT * FROM user")
    List<UserEntity> findAll();

}
