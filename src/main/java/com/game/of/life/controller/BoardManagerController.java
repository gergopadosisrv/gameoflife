package com.game.of.life.controller;

import com.game.of.life.service.BoardManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gergopados on 2017. 02. 01..
 */
@RestController
public class BoardManagerController {

    @Autowired
    private BoardManagerService boardManagerService;

    @RequestMapping(value = "/getNextGeneration", method = RequestMethod.POST)
    public ResponseEntity<?> greeting(@RequestBody int[][] board) {
        try {
            int[][] nextGeneration = boardManagerService.getNextGeneration(board);
            return new ResponseEntity<int[][]>(nextGeneration, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
