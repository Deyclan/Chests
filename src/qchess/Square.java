package qchess;

import qchess.pieces.Piece;

public class Square {

    private boolean inUse;
    private Piece piece;

    
    // To create a square with no piece on it
    public Square() {
        inUse = false;
    }
    // To create a square with a piece on it
    public Square(Piece piece) {
        inUse = true;
        this.piece = piece;
    }
    // To create temp squares
    public Square(Square square) {
        this.inUse = square.isInUse();
        this.piece = square.isInUse() ? square.getPiece().clone() : null;
    }
    
    
    // GETTERS
    public Piece getPiece() { return piece; }
    public boolean isInUse() { return inUse; }

    
    @Override
    public String toString() {
        if (inUse) {
            return piece.toString();
        } else {
            return ".";
        }
    }

}
