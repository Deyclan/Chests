
package chess;

import finalchess.pieces.Piece;

/**
 * Initiate a moving piece, checks is it will be possible
 */
public class Move {
    private Position startPos;
    private Position endPos;
    private Piece movingPiece;
    private Piece killedPiece;
    private boolean castling;
    private int reward;
    
    public Move(Position startPos, Position endPos, Piece movingPiece, Board board){
        this.startPos = startPos;
        this.endPos = endPos;
        this.movingPiece = movingPiece;

        if (board.containedEnemy(startPos,endPos) != null){
            this.reward = board.containedEnemy(startPos,endPos).getReward();
        }
        else
            this.reward = 0;
    }
    
    //For castling
    public Move(Position startPos, Position endPos){
        this.startPos = startPos;
        this.endPos = endPos;
        castling = true;
    }

    public Move(){}

    
    public Position getEndPos(){
        return endPos;
    }
    public Position getStartPos(){
        return startPos;
    }
    
    public Piece getMovingPiece(){
        return movingPiece;
    }
    
    public void setMovingPiece(Piece piece){
        movingPiece = piece;
    }
    
    @Override
    public String toString(){
        String result = positionToString(startPos) + "->" + positionToString(endPos);
        return result;        
    }
    
    private String positionToString(Position pos){
        char letter = (char) (pos.getColumn() + 65);
        String result = letter + "" + (pos.getRow()+1);
        return result;        
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
