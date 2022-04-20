package com.iiitb.trello.services;

import com.iiitb.trello.model.dtos.BoardsDto;
import com.iiitb.trello.model.entities.BoardEntity;
import com.iiitb.trello.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository BoardRepository) {
        this.boardRepository = BoardRepository;
    }

    public BoardsDto getBoards() {
        return new BoardsDto(boardRepository.findAll());
    }

    public Optional<BoardEntity> createBoard(BoardEntity newBoard) {
        boardRepository.save(newBoard);
        return boardRepository.findById(newBoard.getId());
    }

}
