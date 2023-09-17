package org.example.controller;

import org.example.dto.BoardRegRequest;
import org.example.entity.BoardEntity;
import org.example.enums.RegType;
import org.example.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BoardRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Test
    void getBoardSeqContent() {
    }

    @Test
    void 게시물_조회() throws Exception {

        when(boardService.findBySeq(any())).thenReturn(getBoardEntity());

        mockMvc.perform(get("/api/board/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.seq").value(2))
                        .andDo(print());

    }

    @Test
    void 게시물_목록_조회() throws Exception {
        when(boardService.findByTitle(null))
                .thenReturn(Collections.singletonList(getBoardEntity()));

        mockMvc.perform(get("/api/board/list")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk())
                        .andDo(print());

    }

    @Test
    void 게시물_목록_조회_조건_검색() throws Exception {
        when(boardService.findByTitle("Dooly"))
                .thenReturn(Collections.singletonList(getBoardEntity()));

        mockMvc.perform(get("/api/board/list?title=Dooly")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @DisplayName("게시물 등록")
    @Test
    void 게시물_등록() throws Exception {
        given(boardService.save(any()))
                .willReturn("OK");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/board/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getBoardRegRequest().toString()))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    private BoardRegRequest getBoardRegRequest() {
        return BoardRegRequest.builder()
                .title("Dooly")
                .userId("Go-Gil-dong")
                .content("baby dinosaur dooly")
                .build();
    }

    private BoardEntity getBoardEntity() {
        return BoardEntity.builder()
                .seq(2L)
                .regName("Go-gil-dong")
                .title("Dooly")
                .content("baby dinosaur dooly")
                .regType(RegType.User)
                .regDt(LocalDateTime.now())
                .build();
    }

}