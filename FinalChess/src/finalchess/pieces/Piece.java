/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalchess.pieces;

import chess.Board;
import chess.Move;
import chess.Position;
import finalchess.players.Player;
import java.util.ArrayList;

/**
 *
 * @author Nejko
 */
public abstract class Piece {

    protected ArrayList<Move> posMoves = new ArrayList<Move>();
    protected Player pieceColor;
    protected Position currentPos;
    protected Board board;

    public Piece(Player color, Position position, Board board) {
        currentPos = position;
        pieceColor = color;
        this.board = board;
    }
    
    //Cloning
     public Piece(Board board, Piece other) {
        pieceColor = other.getPieceColor();
        this.board = board;
        currentPos = new Position(other.getPosition());
    }

    abstract protected void addAllPossibleMoves();

    public Player getPieceColor(){
        return pieceColor;
    }
    
    
    
    public void updateCurrentPos(Position pos){
        this.currentPos = pos;
    }
    
    public Position getPosition(){
        return currentPos;
    }
    
    protected void addMove(Position destination) {
        Move move = new Move(currentPos, destination, this);
        posMoves.add(move);
    }
    
    public ArrayList<Move> getMoves(){
        addAllPossibleMoves();
        return posMoves;
    }
   
}
