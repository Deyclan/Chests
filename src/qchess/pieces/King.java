package qchess.pieces;

import java.util.ArrayList;
import qchess.Board;
import qchess.Move;


public class King extends Piece {
	
    boolean hasMoved = false;

    
    public King(boolean color) {
        super(color);
        value = 0;
    }

    public King(boolean color, boolean hasMoved) {
        super(color);
        this.hasMoved = hasMoved;
        value = 0;
    }

    @Override
    public King clone() {
        return new King(color, hasMoved);
    }

    @Override
    public ArrayList<Move> getMoves(Board b, int x, int y) {
        ArrayList<Move> moves = new ArrayList<>();
        // N
        if (insideOfBoard(x, y + 1) && (!b.getSquare(x, y + 1).isInUse()
                || (b.getSquare(x, y + 1).isInUse() && b.getSquare(x, y + 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x, y + 1));
        }
        // NE
        if (insideOfBoard(x + 1, y + 1) && (!b.getSquare(x + 1, y + 1).isInUse()
                || (b.getSquare(x + 1, y + 1).isInUse() && b.getSquare(x + 1, y + 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x + 1, y + 1));
        }
        // E
        if (insideOfBoard(x + 1, y) && (!b.getSquare(x + 1, y).isInUse()
                || (b.getSquare(x + 1, y).isInUse() && b.getSquare(x + 1, y).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x + 1, y));
        }
        // SE
        if (insideOfBoard(x + 1, y - 1) && (!b.getSquare(x + 1, y - 1).isInUse()
                || (b.getSquare(x + 1, y - 1).isInUse() && b.getSquare(x + 1, y - 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x + 1, y - 1));
        }
        // S
        if (insideOfBoard(x, y - 1) && (!b.getSquare(x, y - 1).isInUse()
                || (b.getSquare(x, y - 1).isInUse() && b.getSquare(x, y - 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x, y - 1));
        }
        // SW
        if (insideOfBoard(x - 1, y - 1) && (!b.getSquare(x - 1, y - 1).isInUse()
                || (b.getSquare(x - 1, y - 1).isInUse() && b.getSquare(x - 1, y - 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x - 1, y - 1));
        }
        // W
        if (insideOfBoard(x - 1, y) && (!b.getSquare(x - 1, y).isInUse()
                || (b.getSquare(x - 1, y).isInUse() && b.getSquare(x - 1, y).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x - 1, y));
        }
        // NW
        if (insideOfBoard(x - 1, y + 1) && (!b.getSquare(x - 1, y + 1).isInUse()
                || (b.getSquare(x - 1, y + 1).isInUse() && b.getSquare(x - 1, y + 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x - 1, y + 1));
        }

        // Castling
        if (color == Piece.WHITE) {
            if (!hasMoved && x == Board.e && y == 0) {
                if (!b.getSquare(Board.b, 0).isInUse() && !b.getSquare(Board.c, 0).isInUse() && !b.getSquare(Board.d, 0).isInUse()
                        && b.getSquare(Board.a, 0).isInUse() && b.getSquare(Board.a, 0).getPiece().toString().equals("R")) {
                    moves.add(new Move(x, y, x + 2, y, true));
                }
            } else
                hasMoved = true;
        } else {
            if (!hasMoved && x == Board.e && y == 7) {
                if (!b.getSquare(Board.b, 7).isInUse() && !b.getSquare(Board.c, 7).isInUse() && !b.getSquare(Board.d, 7).isInUse()
                        && b.getSquare(Board.a, 7).isInUse() && b.getSquare(Board.a, 7).getPiece().toString().equals("R")) {
                    moves.add(new Move(x, y, x + 2, y, true));
                }
            } else {
                hasMoved = true;
            }
        }
        return moves;
    }
    
    @Override
    public String toString() {
        if (color == Piece.WHITE) {
            return "K";
        } else {
            return "k";
        }
    }
}
