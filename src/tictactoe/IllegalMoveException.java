package tictactoe;

public class IllegalMoveException extends RuntimeException{
    public IllegalMoveException() {
        super();
    }

    public IllegalMoveException(String message) {
        super(message);
    }
}
