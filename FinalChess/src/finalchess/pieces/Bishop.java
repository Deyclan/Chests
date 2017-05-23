
package finalchess.pieces;

import chess.Position;

/**
 * Bishops - moves/characteristics
 * can move any number of squares diagonally;
 * cannot move past any piece that is obstructing its path;
 * can take any other piece on the board that is within its bounds of movement;
 */
public class Bishop extends Piece{

    public Bishop(Player color, Position pos, Board board){super(color, pos, board);}
    public King(Board board, Piece other){super(board, other);}

    @Override
    public void updateCurrentPos(Position pos){
        this.currentPos = pos;
    }

    @Override
    protected void addAllPossibleMoves(){
        posMoves.clear();

        //N-W
        for(int i = 1; i < 9; i++){
            Position pos = new Position(currentPos.getRow() + 1, currentPos.getColumn() -1 );
            checkSpace(pos);
        }
    }
}
