package tictactoe;
/**
 * @author Edward Oh
 * @author Chen Luo
 */

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintStream printer = System.out;

        // Create a Tic-Tac-Toe board
        Board board = new Board();

        // Create a ConsoleInterface for input and output
        ConsoleInterface consoleInterface = new ConsoleInterface(scanner, printer);
        Player player1 = null;
        Player player2 = null;
        if (consoleInterface.askIfPlayerShouldBeAComputer(1)){
            player1 = new ConsolePlayer(scanner, printer, Board.Piece.X);
        }
        else {
            player1 = new ComputerPlayer(Board.Piece.X);
        }
        if (consoleInterface.askIfPlayerShouldBeAComputer(2)){
            player2 = new ConsolePlayer(scanner, printer, Board.Piece.O);
        }
        else {
            player2 = new ComputerPlayer(Board.Piece.O);
        }
        Player currentPlayer = player1;
        boolean gameEnded = false;
        // Print the current board
        consoleInterface.printBoard(board);
        while (!gameEnded) {
            // Determine the current player and make their move
            currentPlayer.makeNextMove(board);
            //Print after making a move
            consoleInterface.printBoard(board);

            Board.State gameState = board.getGameState();
            // Check the game state after the move
            if (gameState == Board.State.INCOMPLETE) {
                // Switch to the next player for the next turn
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
            else if (gameState == Board.State.DRAW) {
                System.out.println("The game is draw");
                gameEnded = true;
            }
            else {
                gameEnded = true;
                System.out.println("Game Over. Result: player" + (currentPlayer == player1 ? "1" : "2")+ " won");
            }
        }
        // Game is over, print the final board and result
        consoleInterface.printBoard(board);
    }

}