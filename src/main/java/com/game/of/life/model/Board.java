package com.game.of.life.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gergopados on 2017. 02. 01..
 */
@Entity
@Table(name = "BOARD")
public class Board implements Serializable {
    private static final long serialVersionUID = -9052493849930897139L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "BOARD", nullable = false, length = 4000)
    private String board;

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

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
}
