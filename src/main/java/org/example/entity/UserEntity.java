package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserEntity {
    private Long seq;
    private String id;
    private String name;

    @JsonIgnore
    private String password;
    private String authority;
    private Timestamp create_at;

}
