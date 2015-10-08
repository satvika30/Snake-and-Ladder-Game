/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeandladder;

/**
 *
 * @author VAIO
 */
public class Board {
    
    private static final int MIN_ROW = 10;
    private static final int MAX_ROW = 12;
  
    private static final int MIN_COL = 10;
    private static final int MAX_COL = 12;
    
    private Integer row = 10;
    private Integer col = 10;
    
    private Integer BOARD_SIZE = -1;
    private Integer[] board = null;
    
    private Board(int row, int col) {
        this.row = row;
        this.col = col;
        BOARD_SIZE = this.row * this.col;
        board = new Integer[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = i + 1;
        }
    }
    
    public int getDestinationNumber(){
	return BOARD_SIZE;
    }
    public static Board createBoard(int row, int col) {
        Board newBoard = new Board(row, col);
        return newBoard;
    }
}
