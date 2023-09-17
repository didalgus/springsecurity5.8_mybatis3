package org.example.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Setter
@Getter
public class BoardEditRequest {

    private long seq;

    @ApiModelProperty("제목")
    @NotEmpty(message = "Please enter a title")
    private String title;

    @ApiModelProperty("내용")
    @NotEmpty(message = "Please enter your content")
    private String content;

    @ApiModelProperty("작성자")
    @NotEmpty(message = "Please enter your name")
    private String regName;
}
