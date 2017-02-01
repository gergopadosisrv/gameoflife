package com.game.of.life.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by gergopados on 2017. 02. 01..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LifParserImplTests {

    @Autowired
    private LifParser lifParser;

    @Test
    public void w() {
        try {
            lifParser.parse("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
