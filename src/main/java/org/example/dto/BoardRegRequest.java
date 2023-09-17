package org.example.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import org.example.enums.RegType;

import javax.validation.constraints.NotEmpty;

@Builder
public class BoardRegRequest {

    @ApiModelProperty("제목")
    @NotEmpty(message = "Please enter a title")
    private String title;

    @ApiModelProperty("내용")
    @NotEmpty(message = "Please enter your content")
    private String content;

    @ApiModelProperty("글 분류 : 사용자(User), Kafka(Kafka)")
    private RegType regType;

    @ApiModelProperty("사용자아이디")
    @NotEmpty(message = "User ID is required")
    private String userId;

}
