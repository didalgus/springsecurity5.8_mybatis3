package org.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.example.enums.RegType;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel("게시물 등록")
public class BoardRegRequest {

    @ApiModelProperty("제목")
    @NotEmpty(message = "Please enter a title")
    private String title;

    @ApiModelProperty("내용")
    @NotEmpty(message = "Please enter your content")
    private String content;

    @ApiModelProperty("글 분류 : 사용자(User), Kafka(Kafka)")
    private RegType regType;

    @ApiModelProperty("사용자 아이디")
    @NotEmpty(message = "User ID is required")
    private String userId;

}
