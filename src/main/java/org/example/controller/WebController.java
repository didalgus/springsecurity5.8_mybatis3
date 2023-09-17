package org.example.controller;

import org.example.dto.BoardResponse;
import org.example.entity.BoardEntity;
import org.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String main() {
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String board(Model model) {
        List<BoardEntity> list = boardService.findByTitle(null);
        model.addAttribute("lists", list);
        return "board";
    }

    @GetMapping("/board/{boardSeq}")
    public String view(Model model, @PathVariable @Valid @NotNull Long boardSeq) {

        BoardEntity board = boardService.findBySeq(boardSeq);
        model.addAttribute("board", board);
        return "boardDetail";
    }


    @GetMapping("/board/form")
    public String boardForm(Model model, @RequestParam(value = "seq", required = false) Long boardSeq) {

        BoardEntity board = new BoardEntity();

        if (boardSeq != null)
            board = boardService.findBySeq(boardSeq);

        model.addAttribute("board", board);
        return "boardForm";
    }

}
