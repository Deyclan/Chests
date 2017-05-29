package qchess.pieces;

import qchess.Board;
import qchess.Move;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(boolean color) {
        super(color);
        value = 1;
    }

    @Override
    public Pawn clone() {
        return new Pawn(color);
    }

    @Override
    public ArrayList<Move> getMoves(Board b, int x, int y) {
        ArrayList<Move> moves = new ArrayList<>();
        if (color == Piece.WHITE) {
            // N
            if (insideOfBoard(x, y + 1) && !b.getSquare(x, y + 1).isInUse()) {
                moves.add(new Move(x, y, x, y + 1));
            }
            // kill diagonally
            if (insideOfBoard(x + 1, y + 1) && b.getSquare(x + 1, y + 1).isInUse() && b.getSquare(x + 1, y + 1).getPiece().getColor() != color) {
                moves.add(new Move(x, y, x + 1, y + 1));
            }
            if (insideOfBoard(x - 1, y + 1) && b.getSquare(x - 1, y + 1).isInUse() && b.getSquare(x - 1, y + 1).getPiece().getColor() != color) {
                moves.add(new Move(x, y, x - 1, y + 1));
            }
        } else {
            // S
            if (insideOfBoard(x, y - 1) && !b.getSquare(x, y - 1).isInUse()) {
                moves.add(new Move(x, y, x, y - 1));
            }
            // kill diagonally
            if (insideOfBoard(x + 1, y - 1) && b.getSquare(x + 1, y - 1).isInUse() && b.getSquare(x + 1, y - 1).getPiece().getColor() != color) {
                moves.add(new Move(x, y, x + 1, y - 1));
            }
            if (insideOfBoard(x - 1, y - 1) && b.getSquare(x - 1, y - 1).isInUse() && b.getSquare(x - 1, y - 1).getPiece().getColor() != color) {
                moves.add(new Move(x, y, x - 1, y - 1));
            }
        }

        return moves;
    }
    
    @Override
    public String toString() {
        if (color == Piece.WHITE) {
            return "P";
        } else {
            return "p";
        }
    }
}
