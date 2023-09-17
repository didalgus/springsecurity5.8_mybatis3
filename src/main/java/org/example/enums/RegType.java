package org.example.enums;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ApiModel("등록 타입")
@AllArgsConstructor
@Getter
public enum RegType {
    User,
    Kafka
}
