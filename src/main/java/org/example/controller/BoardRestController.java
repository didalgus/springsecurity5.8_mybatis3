package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.BoardEditRequest;
import org.example.dto.BoardRegRequest;
import org.example.dto.BoardResponse;
import org.example.entity.BoardEntity;
import org.example.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Slf4j
@Api(tags = "Board")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    @ApiOperation("게시물 조회")
    @GetMapping("/board/{boardSeq}")
    public ResponseEntity view(@PathVariable @Valid @NotNull Long boardSeq) {
        return new ResponseEntity(BoardResponse.of(boardService.findBySeq(boardSeq)), HttpStatus.OK);
    }

    @ApiOperation("게시물 목록 조회")
    @GetMapping("/board/list")
    public ResponseEntity<List<BoardResponse>> list(@RequestParam(value = "title", required = false) String title) {
        return new ResponseEntity<>(BoardResponse.listOf(boardService.findByTitle(title)), HttpStatus.OK);
    }

    @ApiOperation("게시물 등록")
    @PostMapping("/board/write")
    @ResponseStatus(value = HttpStatus.OK)
    public void write(@RequestBody @Valid BoardRegRequest boardRegRequest) {
        boardService.save(boardRegRequest);         // Sample - @ResponseStatus
    }

    @ApiOperation("게시물 수정")
    @PutMapping("/board/{boardSeq}")
    public ResponseEntity edit(@PathVariable @Valid @NotNull Long boardSeq,
                     @RequestBody BoardEditRequest board) {

        board.setSeq(boardSeq);
        if(boardService.update(board) == 1) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/board/{boardSeq}")
    public ResponseEntity delete(@PathVariable @Valid @NotNull Long boardSeq) {
        return new ResponseEntity(boardService.delete(boardSeq), HttpStatus.OK);
    }

}
