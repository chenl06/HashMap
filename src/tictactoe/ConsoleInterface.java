package tictactoe;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleInterface {
    private Scanner scanner;
    private PrintStream printer;

    public ConsoleInterface(Scanner scanner, PrintStream printer) {
        this.scanner = scanner;
        this.printer = printer;
    }

    public boolean askIfPlayerShouldBeAComputer(int playerNr) {
        printer.println("Player " + playerNr + ": Do you want to be a player? (true/false)");
        return scanner.nextBoolean();
    }

    public void printBoard(Board board) {
        System.out.println(board.toString());
    }


}
