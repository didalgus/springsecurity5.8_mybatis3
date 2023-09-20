package org.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;


@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel("게시물 수")
public class BoardEditRequest {

    private long seq;

    @ApiModelProperty("제목")
    @NotEmpty(message = "Please enter a title")
    private String title;

    @ApiModelProperty("내용")
    @NotEmpty(message = "Please enter your content")
    private String content;

    @ApiModelProperty("사용자 아이디")
    @NotEmpty(message = "Please enter your name")
    private String userId;
}
