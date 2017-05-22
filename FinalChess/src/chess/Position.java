
package chess;

/**
 * Position handles the coords of a chess piece on board
 * @author Nejko
 */
public class Position {
    private int rank; // 1 to 8
    private int file; // a to h
    
    public Position(int row, int column){
        this.rank = row;
        this.file = column;
    }
    
    public Position(Position other){
        this.rank = other.rank;
        this.file = other.file;
    }

    public int getColumn() {
        return file;
    }

    public int getRow() {
        return rank;
    }
    
    public boolean equals(Position other){
        return (this.rank == other.rank) && (this.file == other.file);
    }
}
