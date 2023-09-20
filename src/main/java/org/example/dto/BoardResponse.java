package org.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.constants.EnvConstants;
import org.example.entity.BoardEntity;
import org.example.enums.RegType;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel("게시물")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {

    @ApiModelProperty("일련번호")
    private Long seq;

    @ApiModelProperty("제목")
    private String title;

    @ApiModelProperty("내용")
    private String content;

    @ApiModelProperty("글 분류 : 사용자(User), Kafka(Kafka)")
    private RegType regType;

    @ApiModelProperty("사용자 아이디")
    private String userId;

    @ApiModelProperty("등록일시")
    private String regDt;

    public static List<BoardResponse> listOf(List<BoardEntity> boardEntities) {

        if (boardEntities.isEmpty()) {
            return Collections.emptyList();
        }

        return boardEntities.stream().map(v -> of(v)).collect(Collectors.toList());
    }

    public static BoardResponse of(BoardEntity boardEntity) {
        return BoardResponse.builder()
                .seq(boardEntity.getSeq())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .regType(boardEntity.getRegType())
                .userId(boardEntity.getUserId())
                .regDt(new Timestamp(boardEntity.getRegDt()).toString())
                .build();
    }
}
