
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalchess.players;

import chess.Board;
import chess.Move;
import finalchess.pieces.Piece;


/**
 * An AI player
 * @author Nejko
 */
public class AlphaBetaAI implements Runnable {

    private boolean IAColor;            // True = White, False = Black
    private Board board;
    private int level;                  // 0 = easy, 1 = normal, 2 = hard
    private boolean gameOver = false;


    /**
     * @param color Color that AI will play : True = White, False = Black
     * @param board The game board
     * @param dificulty The difficulty of the AI, will be equal to the depth of the minimax Search
     */
    public AlphaBetaAI(boolean color, Board board, int dificulty){

        this.board = board;
        this.IAColor = color;
        this.level = dificulty;

    }

    @Override
    public void run() {

        while (!gameOver) {

            //TODO

        }

    }


    /**
     * Minimax algorithme implementation.
     * @param board The game board
     * @param moveToMake The move that AI have to check
     * @param depth The depth of the search
     * @param minMove
     * @param maxMove
     * @param colorToPlay The color of the player ( Distinct the min and the max nodes )
     * @return Move that should better be played
     */
    public Move miniMax(Board board, Move moveToMake, int depth, Move minMove, Move maxMove, boolean colorToPlay){

        board.makeMove(moveToMake);

        if (depth == 0 || (board.getPiecesToPlay(colorToPlay).size() == 0)) {
            board.undoMove(moveToMake);
            return moveToMake;
        }

        if (colorToPlay == IAColor){    // Max Node.

            Move tempMove = minMove;

            for (Piece p:board.getPiecesToPlay(IAColor)){
                for (Move m : p.getMoves()){
                    Move minimax = miniMax(board,m,depth-1,tempMove,maxMove,!IAColor);
                    if (minimax.getReward() > tempMove.getReward()){
                        tempMove = minimax;
                    }
                }
            }
            board.undoMove(moveToMake);
            return tempMove;
        }

        if (colorToPlay == !IAColor){
            Move tempMove = maxMove;

            board.makeMove(moveToMake);

            for (Piece p : board.getPiecesToPlay(!IAColor)){
                for (Move m : p.getMoves()){
                    Move minimax = miniMax(board,m,depth-1,minMove,tempMove,IAColor);
                    if (minimax.getReward() < maxMove.getReward()){
                        maxMove = minimax;
                    }
                }
            }

            board.undoMove(moveToMake);
            return tempMove;
        }

        return null;

    }

}
