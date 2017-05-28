package finalchess.pieces;

import chess.Board;
import chess.Position;
import finalchess.players.Player;

/**
 * Rooks - moves/characteristics can move any number of squares, up and down and
 * side to side; can move anywhere from 1 to 7 squares in any direction, so long
 * as it is not obstructed by any other piece from its own team; can participate
 * in a "castling" move with the King piece.
 */
public class Rook extends Piece {

    boolean hasMoved = false;

    public Rook(Player color, Position pos, Board board) {
        super(color, pos, board);
    }

    public Rook(Board board, Piece other) {
        super(board, other);
    }

    @Override
    protected void addAllPossibleMoves() {
        posMoves.clear();
        for(int i = 1; i < 9; i++){
            //N
            Position pos = new Position(currentPos.getRow() + i, currentPos.getColumn());
            checkSpace(pos);
            //E
            Position pos2 = new Position(currentPos.getRow(), currentPos.getColumn() + i);
            checkSpace(pos2);
            //S
            Position pos3 = new Position(currentPos.getRow() - i, currentPos.getColumn());
            checkSpace(pos3);
            //W
            Position pos4 = new Position(currentPos.getRow(), currentPos.getColumn() - i);
            checkSpace(pos4);
        }
    }
    
        @Override
    public void updateCurrentPos(Position pos) {
        this.currentPos = pos;
        hasMoved = true;
    }

}
