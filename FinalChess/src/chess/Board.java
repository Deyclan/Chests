
package chess;

import finalchess.pieces.*;
import finalchess.players.Player;
import java.util.ArrayList;

/**
 * Board of the game
 */
public class Board {

    private Piece pieces[][];
    public static final int a=0, b=1, c=2, d=3, e=4, f=5, g=6, h=7;

    private Position whiteKingPosition;
    private Position blackKingPosition;

    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;

    
    public Board(Piece[][] tiles) {
		this.pieces = tiles;
	}
    
    
    

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

    public Piece containedEnemy(Position startPos, Position endPos){
        if (containsEnemy(startPos,endPos)){
            return pieces[endPos.getRow()][endPos.getColumn()];
        }
        return null;
    }

    public ArrayList<Piece> getBlackPieces() {
        return blackPieces;
    }

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    /**
     *
     * @param color : White = True, Black = False
     * @return Corresponding ArrayList of Piece
     */
    public ArrayList<Piece> getPiecesToPlay(boolean color){
        if (color){
            return getWhitePieces();
        }
        else return getBlackPieces();
    }

    public void makeMove(Move move){
        move.getMovingPiece().updateCurrentPos(move.getEndPos());
    }
    public void undoMove(Move move){
        move.getMovingPiece().updateCurrentPos(move.getStartPos());
    }
}
