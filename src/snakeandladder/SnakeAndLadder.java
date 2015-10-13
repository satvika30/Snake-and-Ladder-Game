/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeandladder;

import java.util.Scanner;

/**
 *
 * @author VAIO
 */
public class SnakeAndLadder {

    public Scanner in = new Scanner(System.in);
    private Board board = null;
    private Snake snakes = new Snake();
    private Ladder ladders = new Ladder();    
    private Players players = new Players();
    private boolean isGameWon = false;
    private Player currentPlayer = null;
    private int i;
    
    public void setUpGame() {
        createBoard();
        setUpSnakes();
        setUpLadders();
        addPlayers();
    }
    
    public void createBoard() {
        int row = getNextNumberFromPlayer("Enter Row For Board -> ");
        int col = getNextNumberFromPlayer("Enter Columns For Board -> ");
        
        board = Board.createBoard(row, col);
    }
    
    public void setUpSnakes() {
        int noOfSnakes = getNextNumberFromPlayer("Enter No of Snakes on a Board -> ");
        i = 1;
        while (i <= noOfSnakes) {
            int head = getNextNumberFromPlayer("Enter Head Position for Snake " + i + " -> ");
            int tail = getNextNumberFromPlayer("Enter Tail Position for Snake " + i + " -> ");
            boolean isNewSnakeAdded = addSnakes(head, tail);
            if (isNewSnakeAdded) {
		i++;
            }
        }
    }
    
    private boolean addSnakes(Integer head, Integer tail) {
	if (head == board.getDestinationNumber()) {
            System.out.println("Snake Head can not be at Destination position");
            return false;
	}
	if (tail == 1) {
            System.out.println("Snake Tail can not be set at first Position");
            return false;
	}
	
	if (!snakes.addNewSnake(head, tail)) {
            System.out.println("Please try again...");
            return false;
	} else {
            return true;
	}
		
    }
    
    public void setUpLadders() {
        int noOfLadders = getNextNumberFromPlayer("Enter No of Ladders on a Board -> ");
        i = 1;
        while (i <= noOfLadders) {
            int bottom = getNextNumberFromPlayer("Enter Bottom Position for Ladder " + i + " -> ");
	    int top = getNextNumberFromPlayer("Enter Top Position for Ladder " + i + " -> ");
	    boolean isNewLadderAdded = addLadder(bottom, top);
            if (isNewLadderAdded) {
                i++;
            }
        
        }
    }
    
    private boolean addLadder(Integer bottom, Integer top) {
	if (bottom == 1) {
            System.out.println("Ladder can not be set to 1");
            return false;
	}
	if (top == board.getDestinationNumber()) {
            System.out.println("Ladder top position can not be at winning position");
            return false;
	}
	
	if (!ladders.addNewLadder(bottom, top)) {
            System.out.println("Please try again...");
            return false;
	} else {
            return true;
        }
		
    }
    
    public void addPlayers() {
        int noOfPlayers = getNextNumberFromPlayer("Enter No of Players -> ");
        i = 1;
        while (i <= noOfPlayers) {
            boolean isPlayerAdded = false;
            String playerName = getNextStringFromPlayer("Enter Name for Player " + i + " -> ");
            
            isPlayerAdded = players.addPlayer(new Player(playerName));
            if (isPlayerAdded) {
                i++;
            }
        }
    }
    
    public void play() {
        changePlayer();
        while (!isGameWon()) {
            System.out.println("\n--------------------\n");
            System.out.println(currentPlayer.getName() + " : your turn -->\n");
            int input = getNextNumberFromPlayer("Enter a number from dice throw -> ");
            if (move(input)) {
                checkSnakeMove();
                checkLadderMove();

		if (isWinner()) {
                    System.out.println(currentPlayer.getName() + " You won the game ");
		}
            }
            changePlayer();
        }
    }
    
    private boolean move(Integer nextMove) {
	boolean isMoved = false;
        if (!isWinner()) {
            if (isValidMove(nextMove)) {
                int oldPosition = currentPlayer.getCurrentPosition();
                isMoved = changePlayerPosition(nextMove);
                printPosition(oldPosition, currentPlayer.getCurrentPosition());
            } else {
                System.out.println(currentPlayer.getName()
                                    + " Your current Position is "
                                    + currentPlayer.getCurrentPosition()
                                    + " So this move is not possible");
            }
        }
	return isMoved;
    }
    
    private boolean changePlayerPosition(Integer nextMove) {
    	currentPlayer.setCurrentPosition(currentPlayer.getCurrentPosition() + nextMove);
        return true;
    }
    
    private boolean changePlayer() {
        this.currentPlayer = players.getNextPlayer();
	return true;
    }
    
    private void checkSnakeMove() {
	if (!isWinner() && isPlayerAtSnakeHead()) {
            int oldPosition = currentPlayer.getCurrentPosition();
            System.out.println("Oh you are at snake head ["
				+ currentPlayer.getCurrentPosition() + "->"
				+ snakes.getSnakeTail(currentPlayer.getCurrentPosition())
				+ "]");
	    changePlayerPositionIfSnakeHead();
            printPosition(oldPosition, currentPlayer.getCurrentPosition());
	}		
    }
    
    private boolean isPlayerAtSnakeHead() {
	return snakes.isSnakeHead(currentPlayer.getCurrentPosition());
    }
    
    private void changePlayerPositionIfSnakeHead() {
	currentPlayer.setCurrentPosition(snakes.getSnakeTail(currentPlayer.getCurrentPosition()));
    }
	
    
    private void checkLadderMove() {
	if (!isWinner() && isPlayerAtLadderStart()) {
            int oldPosition = currentPlayer.getCurrentPosition();
            System.out.println("WOW you are at Ladder Start ["
				+ currentPlayer.getCurrentPosition() + "->"
				+ ladders.getLadderTop(currentPlayer.getCurrentPosition())
				+ "]");
            changePlayerPositionIfLadderStart();
            printPosition(oldPosition, currentPlayer.getCurrentPosition());

	}
    }
    
    private boolean isPlayerAtLadderStart() {
	return ladders.isLadderBottom(currentPlayer.getCurrentPosition());
    }

    private void changePlayerPositionIfLadderStart() {
        currentPlayer.setCurrentPosition(ladders.getLadderTop(currentPlayer
					.getCurrentPosition()));
    }
                        
    private boolean isValidMove(Integer nextMove) {
	return currentPlayer.getCurrentPosition() + nextMove <= board.getDestinationNumber();
    }
    private boolean isWinner() {
    	isGameWon = currentPlayer.getCurrentPosition() == board.getDestinationNumber();
	return isGameWon;
    }
    
    private boolean isGameWon() {
    	return isGameWon;
    }
    
    private int getNextNumberFromPlayer(String message) {
	int input = -1;
	try {
            System.out.print(message);
            input = Integer.parseInt(in.next());
	} catch (Exception e) {
            e.printStackTrace();
	}
	return input;
    }

    private String getNextStringFromPlayer(String message) {
	String input = "";
        try {
            System.out.print(message);
            input = in.next();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return input;

    } 
    
    private void printPosition(Integer oldPos, Integer newPos) {
	if (oldPos == 0) {
            System.out.println(currentPlayer.getName() + " You move to ["
				+ newPos + "]");
	} else {
            System.out.println(currentPlayer.getName()
				+ " Your position change [" + oldPos + " to " + newPos
				+ "]");
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        SnakeAndLadder game = new SnakeAndLadder();
        game.setUpGame();
        game.play();
    }
    
}
