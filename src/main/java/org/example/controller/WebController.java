package org.example.controller;

import org.example.entity.BoardEntity;
import org.example.entity.UserEntity;
import org.example.service.BoardService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String main() {
        //return "redirect:/board";
        return "main";
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

    @GetMapping("/users")
    public String users(Model model) {
        List<UserEntity> users = userService.findAll();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/sign-out")
    public String signOut() {
        return "sign-out";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "/sign-up";
    }

    @GetMapping("/error/403")
    public String error403() {
        return "error403";
    }
}
