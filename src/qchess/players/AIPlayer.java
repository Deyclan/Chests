package qchess.players;

import qchess.algo.MinimaxAlphaBeta;
import qchess.Move;
import qchess.Board;

public class AIPlayer extends Player {

    MinimaxAlphaBeta minimax;

    public AIPlayer(boolean color, int maxDepth) {
        super(color);
        minimax = new MinimaxAlphaBeta(color, maxDepth);
    }


    @Override
    public Move getNextMove(Board b) {
        Move move = minimax.decision(b);
        return move;
    }

}
