package chess;

import chess.pieces.*;
import java.util.ArrayList;


public class Board {
	public static final int a=0, b=1, c=2, d=3, e=4, f=5, g=6, h=7;
	
	private Tile[][] tiles;
	/**	
	 * 	 8  r . b . k . n . 
	 *	 7  . p . p . p . p     black pieces
	 *	 6  p . p . p . p . 
	 *	 5  . . . . . . . . 
	 *	 4  . . . . . . . . 
	 *	 3  P . P . P . P . 
	 *	 2  . P . P . P . P     WHITE PIECES
	 *	 1  R . B . K . N .      
	 *    	
	 *          a b c d e f g h
	 *    
	 * P=pawn, K=king, R=rook, N=knight, B=Bishop
	 */
	
	public Board(Tile[][] tiles) {
		this.tiles = tiles;
	}


	public Board() {
                //WHITE side
		boolean co = Piece.WHITE;
		tiles = new Tile[8][8];
		tiles[a][0] = new Tile(new Rook(co));
                tiles[b][0] = new Tile();
		tiles[c][0] = new Tile(new Bishop(co));
                tiles[d][0] = new Tile();
		tiles[e][0] = new Tile(new King(co));
                tiles[f][0] = new Tile();
		tiles[g][0] = new Tile(new Knight(co));
                tiles[h][0] = new Tile();
		
		for(int i = 0; i < 8; i=i+2) {
			tiles[i][2] = new Tile(new Pawn(co));
                        tiles[i+1][2] = new Tile();
                        tiles[i+1][1] = new Tile(new Pawn(co));
                        tiles[i][1] = new Tile();
		}
		
		for(int i = 3; i < 5; i++) {
			for(int j = 0; j < 8; j++) {
				tiles[j][i] = new Tile();
			}
		}
		//BLACK side
		co = Piece.BLACK;
		tiles[a][7] = new Tile(new Rook(co));
                tiles[b][7] = new Tile();
		tiles[c][7] = new Tile(new Bishop(co));
                tiles[d][7] = new Tile();
		tiles[e][7] = new Tile(new King(co));
                tiles[f][7] = new Tile();
		tiles[g][7] = new Tile(new Knight(co));
                tiles[h][7] = new Tile();
		
		for(int i = 0; i < 8; i=i+2) {
			tiles[i+1][6] = new Tile(new Pawn(co));
                        tiles[i][6] = new Tile();
                        tiles[i][5] = new Tile(new Pawn(co));
                        tiles[i+1][5] = new Tile();
		}
	}

	
	public String toString() {
		String str = "";
		for(int i = 7; i >= 0; i--) {
			str += (i+1) + "  ";
			for(int j = 0; j < 8; j++) {
				str += tiles[j][i] + " ";
			}
			str += "\n";
		}
		
		str += "\n   a b c d e f g h";
		
		return str;
	}
	
	public ArrayList<Move> getMoves(boolean color) {
		return getMoves(color, true);
	}
	
	
	/**
	 * Checks if player color is under check at the moment
	 * 
	 * @param color
	 * @return
	 */
	public boolean isCheck(boolean color) {
		int x = -1, y = -1;
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(tiles[i][j].isOccupied() && 
						tiles[i][j].getPiece().getColor() == color &&
						tiles[i][j].getPiece().toString().equalsIgnoreCase("K")) {
					x = i; y = j;
				}
			}
		
		// check a move if after making this move the king can be killed (moving into check)
		ArrayList<Move> opponentMoves = getMoves(!color, false);
		
		// check all opponent moves if they kill king (opponent moves in next round)
		for(int j = 0; j < opponentMoves.size(); j++) {
			if(opponentMoves.get(j).getX2() == x && opponentMoves.get(j).getY2() == y)
				return true;
		}
		
		return false;	
	}
	
	/**
	 * Checks if player color is under check in the next move
	 * 
	 * @param color of the player, 
         * @param moves the list of the player's next moves,
	 * @return true : yes ; false : no.
	 */
	public boolean isCheckAfter(boolean color, ArrayList<Move> moves) {
		
		Tile[][] newTiles = getTilesAfter(moves);
		
		int x = -1, y = -1;
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(newTiles[i][j].isOccupied() && 
						newTiles[i][j].getPiece().getColor() == color &&
						newTiles[i][j].getPiece().toString().equalsIgnoreCase("K")) {
					x = i; y = j;
				}
			}
		
		// check a move if after making this move the king can be killed (moving into check)
		ArrayList<Move> opponentMoves = getMovesAfter(!color, moves, false);
		
		// check all opponent moves if they kill king (opponent moves in next round)
		for(int j = 0; j < opponentMoves.size(); j++) {
			if(opponentMoves.get(j).getX2() == x && opponentMoves.get(j).getY2() == y)
				return true;
		}
		
		return false;	
	}
	
	public ArrayList<Move> getMoves(boolean color, boolean checkCheck) {
		ArrayList<Move> moves = new ArrayList<>();
		
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(tiles[i][j].isOccupied() && 
						tiles[i][j].getPiece().getColor() == color) {
					moves.addAll(tiles[i][j].getPiece().getMoves(this, i, j));
				}
			}
		
		// check if move is valid (must not be check after move) and throw away erroneous moves
		if(checkCheck) {
			// find king (of correct color)
			int x = -1, y = -1;
			for(int i = 0; i < 8; i++)
				for(int j = 0; j < 8; j++) {
					if(tiles[i][j].isOccupied() && 
							tiles[i][j].getPiece().getColor() == color &&
							tiles[i][j].getPiece().toString().equalsIgnoreCase("K")) {
						x = i; y = j;
					}
				}
			
			ArrayList<Move> removeThese = new ArrayList<>();
			for(int i = 0; i < moves.size(); i++) {
				// check a move if after making this move the king can be killed (moving into check)
				ArrayList<Move> checkThis = new ArrayList<>(moves.subList(i, i+1));
				ArrayList<Move> opponentMoves = getMovesAfter(!color, checkThis, false);
				
				int xUpdated = x, yUpdated = y;
				if(checkThis.get(0).getX1() == x && checkThis.get(0).getY1() == y) { // get updated king position
					xUpdated = checkThis.get(0).getX2();
					yUpdated = checkThis.get(0).getY2();
				}
				
				// check all opponent moves if they kill king (opponent moves in next round)
				for(int j = 0; j < opponentMoves.size(); j++) {
					if(opponentMoves.get(j).getX2() == xUpdated && opponentMoves.get(j).getY2() == yUpdated)
						removeThese.add(checkThis.get(0));
				}
			}
			
			moves.removeAll(removeThese); // remove invalid moves
		}
		
		return moves;
	}
	
	public ArrayList<Move> getMovesAfter(boolean color, ArrayList<Move> moves) {
		return getMovesAfter(color, moves, true);
	}
	
	public ArrayList<Move> getMovesAfter(boolean color, ArrayList<Move> moves, boolean checkCheck) {
		
		Tile[][] temp = new Tile[8][8];
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				temp[x][y] = new Tile(this.tiles[x][y]);
			}
		}
		
		Board b = new Board(temp);

		for(int i = 0; i < moves.size(); i++)
			b.makeMove(moves.get(i));
		
		ArrayList<Move> futureMoves = b.getMoves(color, checkCheck);
		
		return futureMoves;
	}
	
	public Tile[][] getTilesAfter(ArrayList<Move> moves) {
		
		Tile[][] temp = new Tile[8][8];
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				temp[x][y] = new Tile(this.tiles[x][y]);
			}
		}
		
		Board b = new Board(temp);

		for(int i = 0; i < moves.size(); i++)
			b.makeMove(moves.get(i));
		
		Tile[][] temp2 = new Tile[8][8];
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				temp2[x][y] = new Tile(b.getTile(x, y));
			}
		}
		
		return temp2;
	}
	
	/**
	 * @param m
	 * @return -1 if black wins
	 * 			1 if white wins
	 * 			0 if game continues
	 */
	public int makeMove(Move m) {
		Tile oldTile = tiles[m.getX1()][m.getY1()];
				
		tiles[m.getX2()][m.getY2()] = tiles[m.getX1()][m.getY1()];
		tiles[m.getX1()][m.getY1()] = new Tile();
		
		if(m.isCastling()) {
			if(m.getX2() == g && m.getY2() == 1-1) {
				tiles[f][1-1] = tiles[h][1-1];
				tiles[h][1-1] = new Tile();
			}
			if(m.getX2() == c && m.getY2() == 1-1) {
				tiles[d][1-1] = tiles[a][1-1];
				tiles[a][1-1] = new Tile();			
			}
			if(m.getX2() == g && m.getY2() == 8-1) {
				tiles[f][8-1] = tiles[h][8-1];
				tiles[h][8-1] = new Tile();
			}
			if(m.getX2() == c && m.getY2() == 8-1) {
				tiles[d][8-1] = tiles[a][8-1];
				tiles[a][8-1] = new Tile();	
			}
		}
		
		// pawn at top?
		if(oldTile.getPiece().toString().equals("P") && m.getY2() == 8-1)
			tiles[m.getX2()][m.getY2()] = new Tile(new Knight(Piece.WHITE));
		
		if(oldTile.getPiece().toString().equals("p") && m.getY2() == 1-1)
			tiles[m.getX2()][m.getY2()] = new Tile(new Knight(Piece.BLACK));
		
		return 0;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

}
