package qchess.pieces;

import qchess.Board;
import qchess.Move;
import java.util.ArrayList;

public abstract class Piece {
    
    public static final boolean WHITE = true, BLACK = false;
    protected boolean color;
    protected int value;

    public Piece(boolean color) {
        this.color = color;
        value = 0;
    }

    // GETTERS
    public int getValue() { return value; }
    public boolean getColor() { return color; }

    
    @Override
    public abstract Piece clone();

    /**
     * Gets all possible moves according to piece
     * @param b the actual board
     * @param x x location of piece
     * @param y y location of piece
     * @return arraylist of possible moves
     */
    public abstract ArrayList<Move> getMoves(Board b, int x, int y);

    /**
     * Checks that the move is within the board
     *
     * @param x x location of piece
     * @param y y location of piece
     * @return true if on board ; false if not.
     */
    static public boolean insideOfBoard(int x, int y) {
        return !(x < 0 || x > 7 || y < 0 || y > 7);
    }
}
