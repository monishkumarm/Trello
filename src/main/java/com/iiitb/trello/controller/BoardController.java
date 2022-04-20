package com.iiitb.trello.controller;

import com.iiitb.trello.model.dtos.BoardsDto;
import com.iiitb.trello.model.entities.BoardEntity;
import com.iiitb.trello.services.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value="/api/get-boards")
    public ResponseEntity<BoardsDto> getBoards() {
        var boards = boardService.getBoards();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @PostMapping(value="/api/create-board")
    public Optional<BoardEntity> createBoard(@RequestBody BoardEntity newBoard) {
        return boardService.createBoard(newBoard);
    }
}
