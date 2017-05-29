package chess.pieces;

import java.util.ArrayList;
import chess.Board;
import chess.Move;


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
        if (insideOfBoard(x, y + 1) && (!b.getTile(x, y + 1).isOccupied()
                || (b.getTile(x, y + 1).isOccupied() && b.getTile(x, y + 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x, y + 1));
        }
        // NE
        if (insideOfBoard(x + 1, y + 1) && (!b.getTile(x + 1, y + 1).isOccupied()
                || (b.getTile(x + 1, y + 1).isOccupied() && b.getTile(x + 1, y + 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x + 1, y + 1));
        }
        // E
        if (insideOfBoard(x + 1, y) && (!b.getTile(x + 1, y).isOccupied()
                || (b.getTile(x + 1, y).isOccupied() && b.getTile(x + 1, y).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x + 1, y));
        }
        // SE
        if (insideOfBoard(x + 1, y - 1) && (!b.getTile(x + 1, y - 1).isOccupied()
                || (b.getTile(x + 1, y - 1).isOccupied() && b.getTile(x + 1, y - 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x + 1, y - 1));
        }
        // S
        if (insideOfBoard(x, y - 1) && (!b.getTile(x, y - 1).isOccupied()
                || (b.getTile(x, y - 1).isOccupied() && b.getTile(x, y - 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x, y - 1));
        }
        // SW
        if (insideOfBoard(x - 1, y - 1) && (!b.getTile(x - 1, y - 1).isOccupied()
                || (b.getTile(x - 1, y - 1).isOccupied() && b.getTile(x - 1, y - 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x - 1, y - 1));
        }
        // W
        if (insideOfBoard(x - 1, y) && (!b.getTile(x - 1, y).isOccupied()
                || (b.getTile(x - 1, y).isOccupied() && b.getTile(x - 1, y).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x - 1, y));
        }
        // NW
        if (insideOfBoard(x - 1, y + 1) && (!b.getTile(x - 1, y + 1).isOccupied()
                || (b.getTile(x - 1, y + 1).isOccupied() && b.getTile(x - 1, y + 1).getPiece().getColor() != color))) {
            moves.add(new Move(x, y, x - 1, y + 1));
        }

        // Castling
        if (color == Piece.WHITE) {
            if (!hasMoved && x == Board.e && y == 0) {
                if (!b.getTile(Board.b, 0).isOccupied() && !b.getTile(Board.c, 0).isOccupied() && !b.getTile(Board.d, 0).isOccupied()
                        && b.getTile(Board.a, 0).isOccupied() && b.getTile(Board.a, 0).getPiece().toString().equals("R")) {
                    moves.add(new Move(x, y, x + 2, y, true));
                }
            } else
                hasMoved = true;
        } else {
            if (!hasMoved && x == Board.e && y == 7) {
                if (!b.getTile(Board.b, 7).isOccupied() && !b.getTile(Board.c, 7).isOccupied() && !b.getTile(Board.d, 7).isOccupied()
                        && b.getTile(Board.a, 7).isOccupied() && b.getTile(Board.a, 7).getPiece().toString().equals("R")) {
                    moves.add(new Move(x, y, x + 2, y, true));
                }
            } else {
                hasMoved = true;
            }
        }
        // TODO King cannot move into open fire
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
