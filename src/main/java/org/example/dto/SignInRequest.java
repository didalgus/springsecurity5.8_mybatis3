package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    private String userId;
    private String userName;
    private String userPassword;
    private String userAuthority;
}
