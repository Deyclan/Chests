package qchess.pieces;

import java.util.ArrayList;
import qchess.Board;
import qchess.Move;

public class Rook extends Piece {

    public Rook(boolean color) {
        super(color);
        value = 5;
    }

    
    @Override
    public Rook clone() {
        return new Rook(color);
    }

    @Override
    public ArrayList<Move> getMoves(Board b, int x, int y) {
        ArrayList<Move> moves = new ArrayList<>();
        // N
        for (int i = 1; i < 8; i++) {
            if (insideOfBoard(x, y + i)) {
                if (b.getSquare(x, y + i).isInUse()) {
                    if (b.getSquare(x, y + i).getPiece().color != color)
                        moves.add(new Move(x, y, x, y + i));
                    break;
                } else
                    moves.add(new Move(x, y, x, y + i));
            }
        }
        // S
        for (int i = 1; i < 8; i++) {
            if (insideOfBoard(x, y - i)) {
                if (b.getSquare(x, y - i).isInUse()) {
                    if (b.getSquare(x, y - i).getPiece().color != color)
                        moves.add(new Move(x, y, x, y - i));
                    break;
                } else
                    moves.add(new Move(x, y, x, y - i));
            }
        }
        // W
        for (int i = 1; i < 8; i++) {
            if (insideOfBoard(x - i, y)) {
                if (b.getSquare(x - i, y).isInUse()) {
                    if (b.getSquare(x - i, y).getPiece().color != color)
                        moves.add(new Move(x, y, x - i, y));
                    break;
                } else
                    moves.add(new Move(x, y, x - i, y));
            }
        }
        // E
        for (int i = 1; i < 8; i++) {
            if (insideOfBoard(x + i, y)) {
                if (b.getSquare(x + i, y).isInUse()) {
                    if (b.getSquare(x + i, y).getPiece().color != color)
                        moves.add(new Move(x, y, x + i, y));
                    break;
                } else
                    moves.add(new Move(x, y, x + i, y));
            }
        }
        return moves;
    }
    
    @Override
    public String toString() {
        if (color == Piece.WHITE) {
            return "R";
        } else {
            return "r";
        }
    }
}
