package org.example.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel("사용자 로그인")
public class SignInRequest {
    private String userId;
    private String userName;
    private String userPassword;
    private String userAuthority;
}
