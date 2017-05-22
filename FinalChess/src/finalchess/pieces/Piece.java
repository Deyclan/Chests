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
public abstract class Piece {

    public static final boolean WHITE = true, BLACK = false;
    private boolean color;
    protected int value;

    public Piece(boolean color) {
        this.color = color;
        value = 0;
    }


    public boolean getColor() {
        return color;
    }
    public int getValue() {
        return value;
    }

    public abstract ArrayList<Move> getMoves(Board b, int x, int y);
}
