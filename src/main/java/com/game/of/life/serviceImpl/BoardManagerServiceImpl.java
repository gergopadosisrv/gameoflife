package com.game.of.life.serviceImpl;

import com.game.of.life.dao.BoardRepository;
import com.game.of.life.dto.BoardDto;
import com.game.of.life.model.Board;
import com.game.of.life.service.BoardManagerService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by gergopados on 2017. 02. 01..
 */
@Service
public class BoardManagerServiceImpl implements BoardManagerService {

    @Autowired
    private BoardRepository boardRepository;

    public int[][] getNextGeneration(int[][] board) throws Exception {
        if (board == null) {
            throw new Exception("the board is null");
        }

        if (board.length == 0 || board[0].length == 0) {
            throw new Exception("the board length is invalid");
        }

        int[][] nextGeneration = new int[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (isStayAlive(board, row, col) || isNewCellCreated(board, row, col)) {
                    nextGeneration[row][col] = 1;
                }
            }
        }

        return nextGeneration;
    }

    public List<BoardDto> getAllPattern() {
        Gson gson = new Gson();
        return StreamSupport.stream(boardRepository.findAll().spliterator(), false).map(x -> {
            return new BoardDto() {{
                setBoard(gson.fromJson(x.getBoard(), int[][].class));
                setId(x.getId());
                setName(x.getName());
            }};
        }).collect(Collectors.toList());
    }

    public Long savePattern(BoardDto boardDto) {
        Gson gson = new Gson();
        Board board = new Board();

        board.setId(boardDto.getId());
        board.setName(boardDto.getName());
        board.setBoard(gson.toJson(boardDto.getBoard()));

        Board created = boardRepository.save(board);

        return created.getId();
    }

    private int getNeighboursOfCell(int[][] board, int row, int column) {
        int n = 0;
        n += isCellAt(board, row - 1, column - 1) ? 1 : 0;
        n += isCellAt(board, row - 1, column + 0) ? 1 : 0;
        n += isCellAt(board, row - 1, column + 1) ? 1 : 0;
        n += isCellAt(board, row + 0, column - 1) ? 1 : 0;
        n += isCellAt(board, row + 0, column + 1) ? 1 : 0;
        n += isCellAt(board, row + 1, column - 1) ? 1 : 0;
        n += isCellAt(board, row + 1, column + 0) ? 1 : 0;
        n += isCellAt(board, row + 1, column + 1) ? 1 : 0;
        return n;
    }

    private boolean isCellAt(int[][] board, int row, int column) {
        return (row >= 0 && row < board.length &&
                column >= 0 && column < board[row].length &&
                board[row][column] == 1);
    }

    private boolean isStayAlive(int[][] board, int row, int column) {
        boolean isCellAt = isCellAt(board, row, column);
        if (!isCellAt) {
            return false;
        }

        int neighboursOfCell = getNeighboursOfCell(board, row, column);

        return neighboursOfCell >= 2
                && neighboursOfCell <= 3;
    }

    private boolean isNewCellCreated(int[][] board, int row, int column) {
        return !isCellAt(board, row, column)
                && getNeighboursOfCell(board, row, column) == 3;
    }
}
