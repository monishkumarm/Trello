package com.iiitb.trello.controller;

import com.iiitb.trello.model.entities.BoardEntity;
import com.iiitb.trello.services.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="/boards")
@CrossOrigin("*")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping(value="/create-board")
    public Optional<BoardEntity> createBoard(@RequestBody BoardEntity newBoard) {
        return boardService.createBoard(newBoard);
    }
}
