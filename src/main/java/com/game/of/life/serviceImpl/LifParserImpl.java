package com.game.of.life.serviceImpl;

import com.game.of.life.service.LifParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by gergopados on 2017. 02. 01..
 */
@Service
public class LifParserImpl implements LifParser {
    public int[][] parse(String path) throws IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("ak47.lif");

        Scanner input = new Scanner(is);


        int offsetX = 0, offsetY = 0;
        int maxX = 0, maxY = 0;
        while (input.hasNext()) {
            String nextLine = input.nextLine();
            if (!nextLine.startsWith("#P")) {
                continue;
            }

            String[] splitted = nextLine.split(" ");
            int ox = Integer.parseInt(splitted[1]);
            int oy = Integer.parseInt(splitted[2]);

            if (ox < offsetX) offsetX = ox;
            if (oy < offsetY) offsetY = oy;

            if (ox > maxX) maxX = ox;
            if (oy > maxY) maxY = oy;
        }
        input.close();
        is.close();

        int[][] board = new int[200][200];

        is = classloader.getResourceAsStream("ak47.lif");
        Scanner input2 = new Scanner(is);

        int xPos = 0, yPos = 0, temp = 0;
        offsetX = Math.abs(offsetX);
        offsetY = Math.abs(offsetY);
        while (input2.hasNext()) {
            String nextLine = input2.nextLine();
            if (nextLine.startsWith("#L") || nextLine.startsWith("#D") || nextLine.startsWith("#N"))
                continue;

            if (nextLine.startsWith("#P")) {
                String[] splitted = nextLine.split(" ");
                xPos = Integer.parseInt(splitted[1]);
                yPos = Integer.parseInt(splitted[2]);

                continue;
            }

            temp = xPos;
            for (char c : nextLine.toCharArray()) {
                if (c == '*')
                    board[temp + offsetX][yPos + offsetY] = 1;
                temp++;
            }
            yPos++;
        }


        input2.close();

        System.out.println(board.length);
        return board;
    }
}
