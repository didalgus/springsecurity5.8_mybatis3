package org.example.entity;

import lombok.*;
import org.example.enums.RegType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {

    private Long seq;
    private String title;
    private String content;
    private RegType regType;
    private String userId;
    private Long regDt;

}
