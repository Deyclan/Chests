
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

        for(int i = 1; i < 9; i++){
            //N-W
            Position pos = new Position(currentPos.getRow() + i, currentPos.getColumn() - i );
            checkSpace(pos);
            //N-E
            Position pos2 = new Position(currentPos.getRow() + i, currentPos.getColumn() + i);
            checkSpace(pos2);
            //S-E
            Position pos3 = new Position(currentPos.getRow() - i, currentPos.getColumn() + i);
            checkSpace(pos3);
            //S-W
            Position pos4 = new Position(currentPos.getRow() - i, currentPos.getColumn() - i);
            checkSpace(pos4);
        }
    }
}
