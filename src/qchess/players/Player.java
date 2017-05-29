package qchess.players;

import qchess.Move;
import qchess.Board;


public abstract class Player {

    protected boolean color;

    public Player(boolean color) {
        this.color = color;
    }

    /**
     * Get the next move of the player
     *
     * @param b the actual board
     * @return the move the player wants to make
     */
    public Move getNextMove(Board b) {
        return null;
    }

    //GETTER
    public boolean getColor() {
        return color;
    }
}
