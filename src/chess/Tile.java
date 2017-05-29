package chess;

import chess.pieces.Piece;

public class Tile {

    private boolean occupied;
    private Piece piece;

    
    // To create a tile with no piece on it
    public Tile() {
        occupied = false;
    }
    // To create a tile with a piece on it
    public Tile(Piece piece) {
        occupied = true;
        this.piece = piece;
    }
    // To create temp tiles
    public Tile(Tile tile) {
        this.occupied = tile.isOccupied();
        this.piece = tile.isOccupied() ? tile.getPiece().clone() : null;
    }
    
    
    // GETTERS
    public Piece getPiece() { return piece; }
    public boolean isOccupied() { return occupied; }

    
    @Override
    public String toString() {
        if (occupied) {
            return piece.toString();
        } else {
            return ".";
        }
    }

}
