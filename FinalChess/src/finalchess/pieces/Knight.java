
package finalchess.pieces;

import chess.Board;
import chess.Position;
import finalchess.players.Player;

/**
 * Knights - moves/characteristics
 * can move only in an L-shape;
 * can move to any position not already inhabited by another piece of the same color;
 * can skip over any other pieces to reach its destination position;
 */
public class Knight extends Piece{

    public Knight(Player color, Position pos, Board board) {
        super(color, pos, board);
    }

    public Knight(Board board, Piece other) {
        super(board, other);
    }

    @Override
    public void addAllPossibleMoves() {
        posMoves.clear();

        //1 north, 2 west:
        Position pos = new Position(currentPos.getRow() + 1, currentPos.getColumn() - 2);
        checkEncounter(pos);

        //1 north, 2 east:
        pos = new Position(currentPos.getRow() + 1, currentPos.getColumn() + 2);
        checkEncounter(pos);

        //2 north, 1 west:
        pos = new Position(currentPos.getRow() + 2, currentPos.getColumn() - 1);
        checkEncounter(pos);

        //2 north, 1 east:
        pos = new Position(currentPos.getRow() + 2, currentPos.getColumn() + 1);
        checkEncounter(pos);

        //2 south, 1 west:
        pos = new Position(currentPos.getRow() - 2, currentPos.getColumn() - 1);
        checkEncounter(pos);

        //2 south, 1 east:
        pos = new Position(currentPos.getRow() - 2, currentPos.getColumn() + 1);
        checkEncounter(pos);

        //1 south, 2 east:
        pos = new Position(currentPos.getRow() - 1, currentPos.getColumn() + 2);
        checkEncounter(pos);

        //1 south, 2 west:
        pos = new Position(currentPos.getRow() - 1, currentPos.getColumn() - 2);
        checkEncounter(pos);
    }

    private void checkEncounter(Position pos) {
        if (board.containsEnemy(currentPos, pos)) {
            addMove(pos);
            return;
        }
        if (board.isFree(pos)) {
            addMove(pos);
        }
    }
}
