
package finalchess.pieces;

import chess.Board;
import chess.Position;
import finalchess.players.Player;

/**
 * Kings - moves/characteristics
 * can move one square at a time in any direction;
 * cannot move onto a square that is currently occupied by a piece from its own team;
 * cannot move to any square that puts them into a "check" position;
 * an participate in a move known as "castling", where the piece can move up to three squares while exchanging places with a rook chess piece.
 */
public class King extends Piece {

    boolean hasMoved = false;

    public King(Player color, Position pos, Board board) {
        super(color, pos, board);
    }
    public King(Board board, Piece other) {
        super(board, other);
    }

    @Override
    public void updateCurrentPos(Position pos) {
        this.currentPos = pos;
        hasMoved = true;
    }

    @Override
    public void addAllPossibleMoves() {
        posMoves.clear();
        //1 s + 1w
        Position pos = new Position(currentPos.getRow() - 1, currentPos.getColumn() - 1);
        checkSpace(pos);
        //1s
        pos = new Position(currentPos.getRow() - 1, currentPos.getColumn());
        checkSpace(pos);
        //1 s + 1e
        pos = new Position(currentPos.getRow() - 1, currentPos.getColumn() + 1);
        checkSpace(pos);
        //1w
        pos = new Position(currentPos.getRow(), currentPos.getColumn() - 1);
        checkSpace(pos);
        //1e
        pos = new Position(currentPos.getRow(), currentPos.getColumn() + 1);
        checkSpace(pos);
        //1 n + 1w
        pos = new Position(currentPos.getRow() + 1, currentPos.getColumn() - 1);
        checkSpace(pos);
        //1 n
        pos = new Position(currentPos.getRow() + 1, currentPos.getColumn());
        checkSpace(pos);
        //1 n + 1e
        pos = new Position(currentPos.getRow() + 1, currentPos.getColumn() + 1);
        checkSpace(pos);
    }

    private void checkCastling() {
        queenSideCastling();
        kingSideCastling();
    }

    private void queenSideCastling() {
        if (hasMoved) { return; }
        for (int column = 4; column < 7; column++) {
            Position pos = new Position(currentPos.getRow(), column);
            if (!board.isFree(pos)) { return; }
        }

        Position queenSideTower = new Position(currentPos.getRow(), 7);
        if (board.containsEnemy(currentPos, queenSideTower)) {
            return;
        }

        if (!board.isUnmovedTower(queenSideTower)) { return; }
        addMove(queenSideTower);
    }

    private void kingSideCastling() {
        if (hasMoved) {
            return;
        }
        for (int column = 2; column > 0; column--) {
            Position pos = new Position(currentPos.getRow(), column);
            if (!board.isFree(pos)) {
                return;
            }
        }

        Position kingSideTower = new Position(currentPos.getRow(), 0);
        if (board.containsEnemy(currentPos, kingSideTower)) {
            return;
        }

        if (!board.isUnmovedTower(kingSideTower)) {
            return;
        }
        addMove(kingSideTower);
    }



}
