package tictactoe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Board implements Cloneable {
    private Piece[][] board;
    public static int MAX_COLUMNS = 3;
    public static int MAX_ROWS = 3;
    public static enum Piece {
        NONE, X, O;
    }

    public static class Position {
        public int column;
        public int row;

        /**
         * A constructor for the position
         * @param row row
         * @param column column
         */
        public Position(int row, int column) {
            this.column = column;
            this.row = row;
        }

        /**
         * Returns the row and column comma-separated: e.g., 0,1
         * @return rows and columns
         */
        public String toString() {
            return row + "," + column;
        }

        /**
         * new Copy
         * @return shadow copy of Position
         */
        public Position clone() {
            return new Position(this.row, this.column);
        }

        /**
         * Returns true if the positions have the same row and column
         * @param otherObject otherboard
         * @return true
         */
        public boolean equals(Object otherObject) {
            return this == otherObject ||
                    (otherObject instanceof Position &&
                            this.row == ((Position) otherObject).row &&
                            this.column == ((Position) otherObject).column);
        }


    }

    public static enum State {
        DRAW, INCOMPLETE, OWINS, XWINS;
    }

    /**
     * Default constructor for the board. Creates a 3x3 Tic Tac Toe board
     * where each position in the board is NONE.
     */
    public Board() {
        board = new Piece[MAX_ROWS][MAX_COLUMNS];
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int col = 0; col < MAX_COLUMNS; col++) {
                board[row][col] = Piece.NONE; // Initialize all cells as none
            }
        }
    }

    /**
     * Returns a deep copy of the Board instance.
     * @return shadow copy
     * @throws CloneNotSupportedException ex
     */
    public Board clone() throws CloneNotSupportedException {
        Board clonedBoard = new Board();
        // Copy the pieces from the original board to the cloned board
        for (int row = 0; row < MAX_ROWS; row++) {
            if (MAX_COLUMNS >= 0) System.arraycopy(this.board[row], 0, clonedBoard.board[row], 0, MAX_COLUMNS);
        }
        return clonedBoard;
    }

    /**
     * Two boards are considered equal if they have the same pieces in the same positions.
     * @param otherObject other board
     * @return true
     */
    public boolean equals(Object otherObject) {
        return this == otherObject || (otherObject != null && getClass() == otherObject.getClass()
                && java.util.Arrays.deepEquals(board, ((Board) otherObject).board));
    }

    /**
     * Returns the piece at the given position
     * @param position position
     * @return board position
     */
    public Piece getPiece(Position position) {
        return board[position.row][position.column];
    }

    /**
     * Puts the given piece on the board at the given spot.
     * @param position postion
     * @param piece piece
     * @throws IllegalMoveException
     */
    public void playPiece(Position position, Piece piece) throws IllegalMoveException {
        if (position.row < 0 || position.row >= MAX_ROWS || position.column < 0 || position.column >= MAX_COLUMNS) {
            throw new IllegalMoveException("Invalid row or column");
        }
        if (board[position.row][position.column] != Piece.NONE) {
            throw  new IllegalMoveException("Position is already occupied");
        }
        if (piece == Piece.NONE) {
            throw new IllegalMoveException("Cannot play Piece.NONE");
        }
        board[position.row][position.column] = piece;
    }

    /**
     * Returns a collection of empty positions (squares) on the Tic Tac Toe board.
     * @return
     */
    public Collection<Position> emptyPositions() {
        Collection<Position> emptyPositions = new ArrayList<>();
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int col = 0; col < MAX_COLUMNS; col++) {
                if (board[row][col] == Piece.NONE) {
                    emptyPositions.add(new Position(row, col));
                }
            }
        }
        return emptyPositions;
    }

    /**
     * Calculates and returns the state of the game.
     * @return state
     */
    public State getGameState() {
        State winner = checkWinner();
        return (winner != State.INCOMPLETE) ? winner : (checkBoardFull() ? State.DRAW : State.INCOMPLETE);
    }

    /**
     * Returns a String that contains the pieces in the positions of this board
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int col = 0; col < MAX_COLUMNS; col++) {
                if (board[row][col] == Piece.NONE) {
                    sb.append("-").append(" ");
                }else {
                    sb.append(board[row][col]).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Hashcode
     * @return hashcode of the board
     */
    public int hashCode() {
        return java.util.Arrays.deepHashCode(board);
    }


    private boolean checkBoardFull() {
        boolean full = true;
        Collection<Position> checkForBoard = emptyPositions();
        for (Position p : checkForBoard) {
            if (getPiece(new Position(p.row, p.column)) == Piece.NONE) {
                full = false;
            }
        }
        return full;
    }

    private State checkWinner() {
        State state = State.INCOMPLETE;
        for (int i = 0; i < MAX_ROWS; i++) {
            // Check rows
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != Board.Piece.NONE) {
                state = (board[i][0] == Board.Piece.X) ? Board.State.XWINS : Board.State.OWINS;
            }

            // Check columns
            else if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != Board.Piece.NONE) {
                state = (board[0][i] == Board.Piece.X) ? Board.State.XWINS : Board.State.OWINS;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != Board.Piece.NONE) {
            state = (board[0][0] == Board.Piece.X) ? Board.State.XWINS : Board.State.OWINS;
        }
        else if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != Board.Piece.NONE) {
            state = (board[0][2] == Board.Piece.X) ? Board.State.XWINS : Board.State.OWINS;
        }
        return state;
    }

}
