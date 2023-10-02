package tictactoe;
import java.io.PrintStream;
import java.util.Scanner;
public class ConsolePlayer implements Player {
    private Scanner scanner;
    private PrintStream out;
    private Board.Piece piece;

    public ConsolePlayer(Scanner scanner, PrintStream out, Board.Piece piece) {
        this.scanner = scanner;
        this.out = out;
        this.piece = piece;
    }

    public void makeNextMove(Board board) {
        boolean validInput = false;
        while (!validInput) {
            try {
                out.println("Player " + piece + ", enter your move (row and column separated by a space):");
                int row = scanner.nextInt();
                int column = scanner.nextInt();
                Board.Position position = new Board.Position(row, column);
                board.playPiece(position, piece);
                validInput = true;
            } catch (Exception e) {
                out.println("Invalid input. Please enter valid row and column values.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
