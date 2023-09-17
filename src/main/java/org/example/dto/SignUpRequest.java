package org.example.dto;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
public class SignUpRequest {

    @NotNull(message = "User Id : Please enter your id")
    @Size(min = 2, max = 25, message = "User Id : Please enter 1 characters or more")
    private String userId;

    @NotEmpty(message = "User Name : Please enter your name")
    @Size(min = 2, max = 20, message = "User Name : The name must be at least 2 characters and no more than 20 characters")
    private String userName;

    @NotBlank(message = "User Password : Please enter your password")
    private String userPassword;

    @NotBlank(message = "User Authority : Please select a permission")
    private String userAuthority;
}
