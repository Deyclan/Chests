package qchess;

public class Move {
    
	private int x1, x2, y1, y2;
	private boolean castling = false;


	public Move(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Move(int x1, int y1, int x2, int y2, boolean castling) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.castling = castling;
	}

        // GETTERS
	public int getX1() { return x1; }
	public int getX2() { return x2; }
	public int getY1() { return y1; }
	public int getY2() { return y2; }
	public boolean isCastling() { return castling; }
	
        
        @Override
	public String toString(){
		return (char)('A'+x1) + "" + (y1+1) + " " + (char)('A'+x2) + "" + (y2+1);
	}
}
