
package finalchess.pieces;

import chess.Board;
import chess.Position;
import finalchess.players.Player;

/**
 * Rooks - moves/characteristics
 * can move any number of squares, up and down and side to side;
 * can move anywhere from 1 to 8 squares in any direction, so long as it is not obstructed by any other piece from its own team;
 * can participate in a "castling" move with the King piece.
 */
public class Rook extends Piece {

    public Rook(Player color, Position position, Board board){ super(color,position,board);}
    public Rook(Board board, Piece other){ super(board,other);}

    @Override
    public void addAllPossibleMoves() {

        posMoves.clear();

        for (int i = 1 ; i < 9 ; i++ ){
            // N
            Position pos = new Position(currentPos.getRow() + i, currentPos.getColumn());
            checkSpace(pos);
            // S
            pos = new Position(currentPos.getRow() - i, currentPos.getColumn());
            checkSpace(pos);
            // E
            pos = new Position(currentPos.getRow(), currentPos.getColumn() + i );
            checkSpace(pos);
            // W
            pos = new Position(currentPos.getRow(), currentPos.getColumn() - i );
            checkSpace(pos);
        }
    }

}
