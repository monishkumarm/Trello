package com.iiitb.trello.model.dtos;

import com.iiitb.trello.model.entities.BoardEntity;

import java.util.List;

public class BoardsDto {
    public List<BoardEntity> Boards;

    public BoardsDto(List<BoardEntity> boards){
        this.Boards = boards;
    }
}
