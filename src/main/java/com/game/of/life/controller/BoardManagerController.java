package com.game.of.life.controller;

import com.game.of.life.dto.BoardDto;
import com.game.of.life.service.BoardManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gergopados on 2017. 02. 01..
 */
@RestController
@RequestMapping("/api/")
public class BoardManagerController {

    @Autowired
    private BoardManagerService boardManagerService;

    @RequestMapping(value = "/getNextGeneration", method = RequestMethod.POST)
    public ResponseEntity<?> greeting(@RequestBody BoardDto board) {
        try {
            int[][] nextGeneration = boardManagerService.getNextGeneration(board.getBoard());
            BoardDto newBoard = new BoardDto();
            newBoard.setBoard(nextGeneration);
            return new ResponseEntity<BoardDto>(newBoard, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/savePattern", method = RequestMethod.POST)
    public ResponseEntity<?> savePattern(@RequestBody BoardDto board) {
        Long id = boardManagerService.savePattern(board);
        return new ResponseEntity<Long>(id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAllPattern", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPattern() {
        List<BoardDto> boards = boardManagerService.getAllPattern();
        return new ResponseEntity<List<BoardDto>>(boards, HttpStatus.OK);
    }
}
