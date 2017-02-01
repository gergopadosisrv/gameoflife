package com.game.of.life.serviceImpl;

import com.game.of.life.service.LifParser;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by gergopados on 2017. 02. 01..
 */
@Service
public class LifParserImpl implements LifParser {
    public int[][] parse(String path) throws FileNotFoundException {

        File file = new File(path);
        Scanner input = new Scanner(file);
        int[][] board = null;

        while (input.hasNext()) {
            String nextLine = input.nextLine();

            if (nextLine.startsWith("#L") || nextLine.startsWith("#D") || nextLine.startsWith("#N"))
                continue;

        }

        input.close();

        return board;
    }
}
