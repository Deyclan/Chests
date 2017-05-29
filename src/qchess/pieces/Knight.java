package qchess.pieces;

import java.util.ArrayList;
import qchess.Board;
import qchess.Move;

public class Knight extends Piece {

    public Knight(boolean color) {
        super(color);
        value = 3;
    }

    @Override
    public Knight clone() {
        return new Knight(color);
    }

    @Override
    public ArrayList<Move> getMoves(Board b, int x, int y) {
        ArrayList<Move> moves = new ArrayList<>();
        // NNE
        if (insideOfBoard(x + 1, y + 2) && (!b.getSquare(x + 1, y + 2).isInUse()
                || (b.getSquare(x + 1, y + 2).isInUse() && b.getSquare(x + 1, y + 2).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x + 1, y + 2));
        }
        // ENE
        if (insideOfBoard(x + 2, y + 1) && (!b.getSquare(x + 2, y + 1).isInUse()
                || (b.getSquare(x + 2, y + 1).isInUse() && b.getSquare(x + 2, y + 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x + 2, y + 1));
        }
        // ESE
        if (insideOfBoard(x + 2, y - 1) && (!b.getSquare(x + 2, y - 1).isInUse()
                || (b.getSquare(x + 2, y - 1).isInUse() && b.getSquare(x + 2, y - 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x + 2, y - 1));
        }
        // SSE
        if (insideOfBoard(x + 1, y - 2) && (!b.getSquare(x + 1, y - 2).isInUse()
                || (b.getSquare(x + 1, y - 2).isInUse() && b.getSquare(x + 1, y - 2).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x + 1, y - 2));
        }
        // SSW
        if (insideOfBoard(x - 1, y - 2) && (!b.getSquare(x - 1, y - 2).isInUse()
                || (b.getSquare(x - 1, y - 2).isInUse() && b.getSquare(x - 1, y - 2).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x - 1, y - 2));
        }
        // WSW
        if (insideOfBoard(x - 2, y - 1) && (!b.getSquare(x - 2, y - 1).isInUse()
                || (b.getSquare(x - 2, y - 1).isInUse() && b.getSquare(x - 2, y - 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x - 2, y - 1));
        }
        // WNW
        if (insideOfBoard(x - 2, y + 1) && (!b.getSquare(x - 2, y + 1).isInUse()
                || (b.getSquare(x - 2, y + 1).isInUse() && b.getSquare(x - 2, y + 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x - 2, y + 1));
        }
        // NNW
        if (insideOfBoard(x - 1, y + 2) && (!b.getSquare(x - 1, y + 2).isInUse()
                || (b.getSquare(x - 1, y + 2).isInUse() && b.getSquare(x - 1, y + 2).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x - 1, y + 2));
        }
        return moves;
    }

    @Override
    public String toString() {
        if (color == Piece.WHITE) {
            return "N";
        } else {
            return "n";
        }
    }
}
