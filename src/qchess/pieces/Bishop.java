package qchess.pieces;

import qchess.Board;
import qchess.Move;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(boolean color) {
        super(color);
        value = 3;
    }


    @Override
    public Bishop clone() {
        return new Bishop(color);
    }

    @Override
    public ArrayList<Move> getMoves(Board b, int x, int y) {
        ArrayList<Move> moves = new ArrayList<>();
        // NE
        for (int i = 1; i < 8; i++) {
            if (insideOfBoard(x + i, y + i)) {
                if (b.getSquare(x + i, y + i).isInUse()) {
                    if (b.getSquare(x + i, y + i).getPiece().color != color)
                        moves.add(new Move(x, y, x + i, y + i));
                    break;
                } else
                    moves.add(new Move(x, y, x + i, y + i));
            }
        }
        // NW
        for (int i = 1; i < 8; i++) {
            if (insideOfBoard(x - i, y + i)) {
                if (b.getSquare(x - i, y + i).isInUse()) {
                    if (b.getSquare(x - i, y + i).getPiece().color != color)
                        moves.add(new Move(x, y, x - i, y + i));
                    break;
                } else
                    moves.add(new Move(x, y, x - i, y + i));
            }
        }
        // SE
        for (int i = 1; i < 8; i++) {
            if (insideOfBoard(x + i, y - i)) {
                if (b.getSquare(x + i, y - i).isInUse()) {
                    if (b.getSquare(x + i, y - i).getPiece().color != color)
                        moves.add(new Move(x, y, x + i, y - i));
                    break;
                } else
                    moves.add(new Move(x, y, x + i, y - i));
            }
        }
        // SW
        for (int i = 1; i < 8; i++) {
            if (insideOfBoard(x - i, y - i)) {
                if (b.getSquare(x - i, y - i).isInUse()) {
                    if (b.getSquare(x - i, y - i).getPiece().color != color)
                        moves.add(new Move(x, y, x - i, y - i));
                    break;
                } else
                    moves.add(new Move(x, y, x - i, y - i));
            }
        }
        return moves;
    }
    
    @Override
    public String toString() {
        if (color == Piece.WHITE) {
            return "B";
        } else {
            return "b";
        }
    }
}
