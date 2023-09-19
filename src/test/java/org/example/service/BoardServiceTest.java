package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.constants.EnvConstants;
import org.example.dto.BoardRegRequest;
import org.example.entity.BoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    void 게시물_조회() {

        BoardEntity boardEntity1 = boardService.findBySeq(1L);
        BoardEntity boardEntity2 = boardService.findBySeq(2L);

        log.info("boardEntity1.getTitle() : {}", boardEntity1.getTitle());
        log.info("boardEntity2.getContent() : {}", boardEntity2.getContent());
        log.info("boardEntity2.getRegDt() : {}", boardEntity2.getRegDt().toString());

        assertAll(
                () -> assertEquals("YOTSUBA&!", boardEntity1.getTitle()),
                () -> assertEquals("Ongaku mo Itsumo Hare Nochi Guu: Jungle wa Itsumo Hare Nochi Guu Original Soundtrack.", boardEntity2.getContent())
        );
    }

    @Test
    void 게시물_목록_조회() {

        String title = null;
        List<BoardEntity> boardEntities = boardService.findByTitle(title);

        log.info("list count :  {}", boardEntities.size());
        assertTrue(boardEntities.size() > 0);
    }

    @Test
    void 게시물_목록_조회_조건() {

        String title = "title";
        List<BoardEntity> boardEntities = boardService.findByTitle(title);

        log.info("list count :  {}", boardEntities.size());
        assertTrue(boardEntities.size() > 0);

        //boardEntities.stream().forEach(v-> System.out.println(v.getTitle()));
        List<BoardEntity> filterEntities = boardEntities.stream().filter(v -> v.getTitle().indexOf(title) >= 0).collect(Collectors.toList());

        log.info("filter count :  {}", filterEntities.size());
        assertEquals(boardEntities.size(), filterEntities.size());
    }

    @Test
    void 게시물_등록() {

        BoardRegRequest boardRegRequest = BoardRegRequest.builder()
                .title("Dooly")
                .userId("Go-Gil-dong")
                .content("baby dinosaur dooly")
                .build();

        var result = boardService.save(boardRegRequest);

        log.info("result : {}", result);
        assertEquals("OK", result);
    }

}