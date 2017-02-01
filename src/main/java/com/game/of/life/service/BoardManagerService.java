package com.game.of.life.service;

import com.game.of.life.dto.BoardDto;

import java.util.List;

/**
 * Created by gergopados on 2017. 02. 01..
 */
public interface BoardManagerService {
    int[][] getNextGeneration(int[][] board) throws Exception;

    List<BoardDto> getAllPattern();

    Long savePattern(BoardDto boardDto);
}