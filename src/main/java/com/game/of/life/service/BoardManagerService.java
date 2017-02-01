package com.game.of.life.service;

/**
 * Created by gergopados on 2017. 02. 01..
 */
public interface BoardManagerService {
    int[][] getNextGeneration(int[][] board) throws Exception;
}
