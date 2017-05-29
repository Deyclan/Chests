package chess.player;

import java.util.ArrayList;
import chess.Move;
import chess.Board;
import java.util.Scanner;

public class Human extends Player {
    Scanner sc = new Scanner(System.in);
    String srcx, srcy, destx,desty;

    public Human(boolean color) {
        super(color);
    }

    /**
     * Function to prompt the player to make a move after the first move has
     * already been made
     *
     * @param b the board to parse
     * @return the selected move
     */
    @Override
    public Move getNextMove(Board b) {
        System.out.println("What piece you'd like to move? (letter first, press enter than number)");
        srcx = sc.next();srcy = sc.next();
        System.out.println("Where you'd like to move that piece? (same as before)");
        destx = sc.next(); desty = sc.next();
        Move userMove = new Move(analyseInput(srcx), Integer.parseInt(srcy)-1, analyseInput(destx), Integer.parseInt(desty)-1);
        return userMove;
    }
    
    private int analyseInput(String character){
        character = character.toUpperCase();
        int result = 0;
        switch(character){
            case "A": result= Board.a; break;
            case "B": result= Board.b; break;
            case "C": result= Board.c; break;
            case "D": result= Board.d; break;
            case "E": result= Board.e; break;
            case "F": result= Board.f; break;
            case "G": result= Board.g; break;
            case "H": result= Board.h; break;
        }
        return result;
    }
}
