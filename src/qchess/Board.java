package qchess;

import qchess.pieces.King;
import qchess.pieces.Bishop;
import qchess.pieces.Knight;
import qchess.pieces.Pawn;
import qchess.pieces.Piece;
import qchess.pieces.Rook;
import java.util.ArrayList;

public class Board {

    public static final int a = 0, b = 1, c = 2, d = 3, e = 4, f = 5, g = 6, h = 7;

    private Square[][] square;

    /**
     * 8  r . b . k . n .
     * 7  . p . p . p . p   black pieces 
     * 6  p . p . p . p . 
     * 5  . . . . . . . . 
     * 4  . . . . . . . . 
     * 3  P . P . P . P . 
     * 2  . P . P . P . P  WHITE PIECES 
     * 1  R . B . K . N .
     * 
     *    a b c d e f g h
     *
     * P=pawn, K=king, R=rook, N=knight, B=Bishop
     */

    /**
     * Board set with number of rows/columns
     * @param squares
     */
    public Board(Square[][] squares) {
        this.square = squares;
    }

    public Board() {
        //WHITE side
        boolean co = Piece.WHITE;
        square = new Square[8][8];
        square[a][0] = new Square(new Rook(co));
        square[b][0] = new Square();
        square[c][0] = new Square(new Bishop(co));
        square[d][0] = new Square();
        square[e][0] = new Square(new King(co));
        square[f][0] = new Square();
        square[g][0] = new Square(new Knight(co));
        square[h][0] = new Square();
        for (int i = 0; i < 8; i = i + 2) {
            square[i][2] = new Square(new Pawn(co));
            square[i + 1][2] = new Square();
            square[i + 1][1] = new Square(new Pawn(co));
            square[i][1] = new Square();
        }

        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                square[j][i] = new Square();
            }
        }
        //BLACK side
        co = Piece.BLACK;
        square[a][7] = new Square(new Rook(co));
        square[b][7] = new Square();
        square[c][7] = new Square(new Bishop(co));
        square[d][7] = new Square();
        square[e][7] = new Square(new King(co));
        square[f][7] = new Square();
        square[g][7] = new Square(new Knight(co));
        square[h][7] = new Square();
        for (int i = 0; i < 8; i = i + 2) {
            square[i + 1][6] = new Square(new Pawn(co));
            square[i][6] = new Square();
            square[i][5] = new Square(new Pawn(co));
            square[i + 1][5] = new Square();
        }
    }
    

    public ArrayList<Move> getMoves(boolean color) {
        return getMoves(color, true);
    }

    /**
     * Checks if player color is under check at the moment;
     * @param color of the player;
     * @return true : yes ; false : no.
     */
    public boolean isCheck(boolean color) {
        int x = -1, y = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (square[i][j].isInUse()
                        && square[i][j].getPiece().getColor() == color
                        && square[i][j].getPiece().toString().equalsIgnoreCase("K")) {
                    x = i;
                    y = j;
                }
            }
        }
        // check a move if after making this move the king can be killed (moving into check)
        ArrayList<Move> opponentMoves = getMoves(!color, false);
        // check all opponent moves if they kill king (opponent moves in next round)
        for (int j = 0; j < opponentMoves.size(); j++) {
            if (opponentMoves.get(j).getX2() == x && opponentMoves.get(j).getY2() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if player color is under check in the next move
     * @param color of the player,
     * @param moves the list of the player's next moves,
     * @return true : yes ; false : no.
     */
    public boolean isNextInCheck(boolean color, ArrayList<Move> moves) {
        Square[][] tmpSquares = getNextSquares(moves);
        int x = -1, y = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tmpSquares[i][j].isInUse()
                        && tmpSquares[i][j].getPiece().getColor() == color
                        && tmpSquares[i][j].getPiece().toString().equalsIgnoreCase("K")) {
                    x = i;
                    y = j;
                }
            }
        }
        // check a move if after making this move the king can be killed (moving into check)
        ArrayList<Move> opponentMoves = getNextMoves(!color, moves, false);
        // check all opponent moves if they kill king (opponent moves in next round)
        for (int j = 0; j < opponentMoves.size(); j++) {
            if (opponentMoves.get(j).getX2() == x && opponentMoves.get(j).getY2() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param color player's color
     * @param checkCheck if move is checked
     * @return list of moves
     */
    public ArrayList<Move> getMoves(boolean color, boolean checkCheck) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (square[i][j].isInUse()
                        && square[i][j].getPiece().getColor() == color) {
                    moves.addAll(square[i][j].getPiece().getMoves(this, i, j));
                }
            }
        }
        // check if move is insideOfBoard (must not be check after move) and throw away erroneous moves
        if (checkCheck) {
            // find king (of correct color)
            int x = -1, y = -1;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (square[i][j].isInUse()
                            && square[i][j].getPiece().getColor() == color
                            && square[i][j].getPiece().toString().equalsIgnoreCase("K")) {
                        x = i;
                        y = j;
                    }
                }
            }

            ArrayList<Move> badMoves = new ArrayList<>();
            for (int i = 0; i < moves.size(); i++) {
                // check if move creates a check
                ArrayList<Move> checkMove = new ArrayList<>(moves.subList(i, i + 1));
                ArrayList<Move> opponentMoves = getNextMoves(!color, checkMove, false);
                int xUpdated = x, yUpdated = y;
                if (checkMove.get(0).getX1() == x && checkMove.get(0).getY1() == y) { // get updated king position
                    xUpdated = checkMove.get(0).getX2();
                    yUpdated = checkMove.get(0).getY2();
                }

                // check all opponent moves if they kill king (opponent moves in next round)
                for (int j = 0; j < opponentMoves.size(); j++) {
                    if (opponentMoves.get(j).getX2() == xUpdated && opponentMoves.get(j).getY2() == yUpdated) {
                        badMoves.add(checkMove.get(0));
                    }
                }
            }
            moves.removeAll(badMoves); // remove invalid moves
        }
        return moves;
    }

    /**
     * Get the list of moves to do after
     * @param color the player's color
     * @param moves list of moves
     * @return list of moves
     */
    public ArrayList<Move> getNextMoves(boolean color, ArrayList<Move> moves) {
        return getNextMoves(color, moves, true);
    }

    public ArrayList<Move> getNextMoves(boolean color, ArrayList<Move> moves, boolean checkCheck) {
        Square[][] temp = new Square[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                temp[x][y] = new Square(this.square[x][y]);
            }
        }
        Board b = new Board(temp);
        for (int i = 0; i < moves.size(); i++) {
            b.makeMove(moves.get(i));
        }
        ArrayList<Move> nextMoves = b.getMoves(color, checkCheck);
        return nextMoves;
    }

    /**
     * Gets the board after having done one move
     * @param moves the list of moves to simulate
     * @return the square of the board
     */
    public Square[][] getNextSquares(ArrayList<Move> moves) {
        Square[][] tmpSquares = new Square[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                tmpSquares[x][y] = new Square(this.square[x][y]);
            }
        }
        Board b = new Board(tmpSquares);
        for (int i = 0; i < moves.size(); i++) {
            b.makeMove(moves.get(i));
        }
        Square[][] tmpSquares2 = new Square[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                tmpSquares2[x][y] = new Square(b.getSquare(x, y));
            }
        }
        return tmpSquares2;
    }

    /**
     * Makes the move a player did
     * @param m the move to make
     * @return -1 if black wins 1 if white wins 0 if game continues
     */
    public int makeMove(Move m) {
        Square preSquare = square[m.getX1()][m.getY1()];

        square[m.getX2()][m.getY2()] = square[m.getX1()][m.getY1()];
        square[m.getX1()][m.getY1()] = new Square();

        // checks for castling move
        if (m.isCastling()) {
            if (m.getX2() == c && m.getY2() == 0) {
                square[d][0] = square[a][0];
                square[a][0] = new Square();
            }
            if (m.getX2() == c && m.getY2() == 7) {
                square[d][7] = square[a][7];
                square[a][7] = new Square();
            }
        }
        // pawn at the other side ? (checks white and black sides)
        if (preSquare.toString().equals("P") && m.getY2() == 7) {
            square[m.getX2()][m.getY2()] = new Square(new Knight(Piece.WHITE));
        }
        if (preSquare.toString().equals("p") && m.getY2() == 0) {
            square[m.getX2()][m.getY2()] = new Square(new Knight(Piece.BLACK));
        }
        return 0;
    }

    /**
     * Getter for square (position)
     * @param x row
     * @param y column
     * @return square
     */
    public Square getSquare(int x, int y) {
        return square[x][y];
    }
    
    
    @Override
    public String toString() {
        String str = "";
        for (int i = 7; i >= 0; i--) {
            str += (i + 1) + "  ";
            for (int j = 0; j < 8; j++) {
                str += square[j][i] + " ";
            }
            str += "\n";
        }
        str += "   a b c d e f g h\n\n ";
        return str;
    }
}
