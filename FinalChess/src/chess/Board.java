
package chess;

import finalchess.pieces.Piece;
import finalchess.players.Player;
import java.util.ArrayList;

/**
 * Board of the game
 */
public class Board {

    private Piece pieces[][];

    private Position whiteKingPosition;
    private Position blackKingPosition;

    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;

    public Board() { }

    public boolean containsEnemy(Position startPos, Position endPos) {
        if (isOutOfBounds(endPos)) {
            return false;
        }
        if (isFree(endPos)) {
            return false;
        }
        Player movingPieceColor = pieces[startPos.getRow()][startPos.getColumn()].getPieceColor();
        Player recivingPieceColor = pieces[endPos.getRow()][endPos.getColumn()].getPieceColor();

        return (movingPieceColor != recivingPieceColor);

    }

    public boolean isFree(Position endPos) {
        if (isOutOfBounds(endPos)) {
            return false;
        }
        return (pieces[endPos.getRow()][endPos.getColumn()] == null);
    }

    public boolean isOutOfBounds(Position endPos) {
        return endPos.getRow() >= 8 || endPos.getRow() < 0
                || endPos.getColumn() >= 8 || endPos.getColumn() < 0;
    }

    public boolean isUnmovedTower(Position queenSideTower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
