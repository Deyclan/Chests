package qchess.players;

import java.util.ArrayList;
import qchess.Move;
import qchess.Board;
import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner sc = new Scanner(System.in);
    String srcx, srcy, destx,desty;

    
    public HumanPlayer(boolean color) {
        super(color);
    }

    @Override
    public Move getNextMove(Board b) {
        ArrayList<Move> moves = b.getMoves(color);
        System.out.println("Little help of moves you can do ;) : ");
        System.out.println(moves);
        int n = moves.size();
        if (n == 0) {
            return null;
        }
        System.out.println("\nWhat piece you'd like to move? (letter first, press enter than number)");
        srcx = sc.next();srcy = sc.next();
        System.out.println("Where you'd like to move that piece? (same as before)");
        destx = sc.next(); desty = sc.next();
        System.out.println();
        Move userMove = new Move(analyseInput(srcx), Integer.parseInt(srcy)-1, analyseInput(destx), Integer.parseInt(desty)-1);
        for (Move move: moves){
            if (moves.contains(userMove))
                return userMove;
        }
        System.out.println("Sorry, you can't make this move (see helper) \n");
        this.getNextMove(b);
        return null;
    }
    
    /*
    * Function in order to analyse the human player input
    */
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
