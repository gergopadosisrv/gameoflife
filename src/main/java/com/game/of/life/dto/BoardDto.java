package com.game.of.life.dto;

/**
 * Created by gergopados on 2017. 02. 01..
 */
public class BoardDto {
    private Long id;

    private String name;

    private int[][] board;

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
