package com.game.of.life.service;

import java.io.FileNotFoundException;

/**
 * Created by gergopados on 2017. 02. 01..
 */
public interface LifParser {
    int[][] parse(String path) throws FileNotFoundException;
}
