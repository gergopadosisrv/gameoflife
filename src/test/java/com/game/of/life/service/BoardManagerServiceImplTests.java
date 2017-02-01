package com.game.of.life.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by gergopados on 2017. 02. 01..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardManagerServiceImplTests {
    @Autowired
    private BoardManagerService boardManagerService;

    @Test
    public void getNextGenerationWorksAsExpectedWithNullParam() {
        try {
            boardManagerService.getNextGeneration(null);
            Assert.fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void getNextGenerationWorksAsExpectedWithEmptyArray() {
        try {
            boardManagerService.getNextGeneration(new int[0][0]);
            Assert.fail();
        } catch (Exception e) {

        }
    }

    @Test
    public void getNextGenerationWorksAsExpected() {
        try {
            int[][] board = boardManagerService.getNextGeneration(new int[1][1]);

            Assert.assertNotNull(board);
            Assert.assertTrue(board.length == 1);
            Assert.assertTrue(board[0].length == 1);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void getNextGenerationWorksAsExpectedWithBlinkerParam() {
        try {
            int[][] blinkerBoard = new int[3][3];

            blinkerBoard[0][1] = 1;
            blinkerBoard[1][1] = 1;
            blinkerBoard[2][1] = 1;

            int[][] board = boardManagerService.getNextGeneration(blinkerBoard);

            Assert.assertTrue(board[0][0] == 0);
            Assert.assertTrue(board[0][1] == 0);
            Assert.assertTrue(board[0][2] == 0);
            Assert.assertTrue(board[1][0] == 1);
            Assert.assertTrue(board[1][1] == 1);
            Assert.assertTrue(board[1][2] == 1);
            Assert.assertTrue(board[2][0] == 0);
            Assert.assertTrue(board[2][1] == 0);
            Assert.assertTrue(board[2][2] == 0);

            int[][] thirdGeneration = boardManagerService.getNextGeneration(board);

            Assert.assertArrayEquals(thirdGeneration, blinkerBoard);

        } catch (Exception e) {
            Assert.fail();
        }
    }
}
