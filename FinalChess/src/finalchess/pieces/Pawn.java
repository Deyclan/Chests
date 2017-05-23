
package finalchess.pieces;

import chess.Board;
import chess.Position;
import finalchess.players.Player;

/**
 * Pawns - moves/characteristics
 * can move one square at a time in any direction;
 * cannot move onto a square that is currently occupied by a piece from its own team;
 * cannot move to any square that puts them into a "check" position;
 * an participate in a move known as "castling", where the piece can move up to three squares while exchanging places with a rook chess piece.
 */
public class Pawn extends Piece {

    boolean hasMoved = false;


    public Pawn(Player color, Position position, Board board) {
        super(color, position, board);
    }

    public Pawn(Board board, Piece other) {
        super(board, other);
    }

    @Override
    public void updateCurrentPos(Position pos) {
        super.updateCurrentPos(pos);
        hasMoved = true;
    }

    @Override
    protected void addAllPossibleMoves() {
        posMoves.clear();

        Position pos;
        if (!hasMoved){

            // 2 S
            pos = new Position(currentPos.getRow() - 2, currentPos.getColumn());
            checkFisrtMoveSouth(pos);

            // 2 N
            pos = new Position(currentPos.getRow() + 2, currentPos.getColumn());
            checkFisrtMoveNorth(pos);

        }

        // 1 S
        pos = new Position(currentPos.getRow() - 1, currentPos.getColumn());
        checkFree(pos);

        // 1 N
        pos = new Position(currentPos.getRow() + 1, currentPos.getColumn());
        checkFree(pos);

        // 1 N + 1 E
        pos = new Position(currentPos.getRow() + 1, currentPos.getColumn() + 1);
        checkContainsEnemy(pos);

        // 1 N + 1 W
        pos = new Position(currentPos.getRow() + 1, currentPos.getColumn() - 1);
        checkContainsEnemy(pos);

        // 1 S + 1 E
        pos = new Position(currentPos.getRow() - 1, currentPos.getColumn() + 1);
        checkContainsEnemy(pos);

        // 1 S + 1 W
        pos = new Position(currentPos.getRow() - 1, currentPos.getColumn() - 1);
        checkContainsEnemy(pos);

    }

    private void checkFisrtMoveSouth(Position pos){
        if (board.isFree(new Position(currentPos.getRow() - 1,currentPos.getColumn()))
                && board.isFree(new Position(currentPos.getRow() - 2,currentPos.getColumn()))){
            addMove(pos);
        }
    }

    private void checkFisrtMoveNorth(Position pos){
        if (board.isFree(new Position(currentPos.getRow() + 1,currentPos.getColumn()))
                && board.isFree(new Position(currentPos.getRow() + 2,currentPos.getColumn()))){
            addMove(pos);
        }
    }

    private void checkFree(Position pos) {
        if (board.isFree(pos)){
            addMove(pos);
        }
    }

    private void checkContainsEnemy(Position pos){
        if (board.containsEnemy(currentPos,pos)){
            addMove(pos);
        }
    }
}
