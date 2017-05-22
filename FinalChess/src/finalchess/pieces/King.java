/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalchess.pieces;

import chess.Board;
import chess.Move;
import java.util.ArrayList;

/**
 *
 * @author Nejko
 */
public class King extends Piece{
    
    boolean hasMoved = false;
    
    public King (){
        super();
        
    }
    
            @Override
	public ArrayList<Move> getMoves(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<>();
		
		// N
		if(valid(x, y+1) && 
				(!b.getTile(x, y+1).isOccupied() || 
						(b.getTile(x, y+1).isOccupied() && b.getTile(x, y+1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x,y+1));
		
		// NE
		if(valid(x+1, y+1) && 
				(!b.getTile(x+1, y+1).isOccupied() || 
						(b.getTile(x+1, y+1).isOccupied() && b.getTile(x+1, y+1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x+1,y+1));
		
		// E
		if(valid(x+1,y) && 
				(!b.getTile(x+1,y).isOccupied() || 
						(b.getTile(x+1,y).isOccupied() && b.getTile(x+1,y).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x+1,y));
		
		
		// SE
		if(valid(x+1,y-1) && 
				(!b.getTile(x+1,y-1).isOccupied() || 
						(b.getTile(x+1,y-1).isOccupied() && b.getTile(x+1,y-1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x+1,y-1));
		
		// S
		if(valid(x,y-1) && 
				(!b.getTile(x,y-1).isOccupied() || 
						(b.getTile(x,y-1).isOccupied() && b.getTile(x,y-1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x,y-1));
		
		// SW
		if(valid(x-1,y-1) && 
				(!b.getTile(x-1,y-1).isOccupied() || 
						(b.getTile(x-1,y-1).isOccupied() && b.getTile(x-1,y-1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x-1,y-1));
		
		// W
		if(valid(x-1,y) && 
				(!b.getTile(x-1,y).isOccupied() || 
						(b.getTile(x-1,y).isOccupied() && b.getTile(x-1,y).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x-1,y));
		
		// NW
		if(valid(x-1,y+1) && 
				(!b.getTile(x-1,y+1).isOccupied() || 
						(b.getTile(x-1,y+1).isOccupied() && b.getTile(x-1,y+1).getPiece().getColor() != color)))
			moves.add(new Move(x,y,x-1,y+1));

		// Castling
		if(color == Piece.WHITE) {
			if(!hasMoved && x == Board.e && y == 1-1) {
				if(!b.getTile(Board.f, 1-1).isOccupied() &&
						!b.getTile(Board.g, 1-1).isOccupied() &&
						b.getTile(Board.h, 1-1).isOccupied() && 
						b.getTile(Board.h, 1-1).getPiece().toString().equals("R"))
					moves.add(new Move(x,y,x+2,y));
					
						
			}
			else 
				hasMoved = true;
		}
		else { // color == Piece.BLACK
			if(!hasMoved && x == Board.e && y == 8-1) {
				
			}
			else 
				hasMoved = true;
		}
		
		
		// TODO King cannot move into open fire
		
		
		return moves;
	}
}
