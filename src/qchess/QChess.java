package qchess;

import qchess.pieces.Piece;
import qchess.players.AIPlayer;
import qchess.players.HumanPlayer;
import qchess.players.Player;
import java.util.Scanner;

/*
 * Check: An attack on a king by either an opposing piece or an opposing pawn is called check. When in check, a player must do one of the following:
    - Move the king so that it’s no longer under attack.
    - Block the attack by interposing a piece between the king and the attacker.
    - Capture the attacking piece.
* Checkmate: When a king is in check and can’t perform any of the preceding moves, it has been checkmated. If your king is checkmated, you lose the game. The term checkmate is commonly shortened to simply mate.
* Stalemate: Stalemate is the relatively rare situation when a player whose king isn’t in check has no legal move to make. Stalemate is considered a draw. Neither player wins, but the game is over.
 */
public class QChess {
    

    public static void main(String[] args) {
            Board board = new Board();
            System.out.println("Want to play chess? "
                    + "Choose your difficulty from easy (1) to extreme (4 -average time 3sec) !");
            Scanner sc = new Scanner(System.in);
            int difficulty = sc.nextInt();
            Player p1 = new AIPlayer(Piece.WHITE, difficulty);
            Player p2 = new HumanPlayer(Piece.BLACK);
            System.out.println("\nPlayer 1 - WHITE (bottom) - AIPlayer");
            System.out.println("Player 2 - BLACK (top) - HumanPlayer\n");
            int winner = run(p1, p2, board);
        switch (winner) {
            case 1:
                System.out.println("Player 1 won");
                break;
            case 0:
                System.out.println("It's a draw!");
                break;
            default:
                System.out.println("AI won");
                break;
        }
    }

    /**
     * Run the game
     *
     * @param player1 the player 1
     * @param player2 the player 2
     * @param b the chess board
     * @return 1 if p1 wins, 0 if draw, -1 if p2 wins
     */
    public static int run(Player player1, Player player2, Board b) {
        Move move;
        int result;
        int turn = 0;
        while (true) {
            if (turn++ > 200) {
                return 0;
            }
            move = player1.getNextMove(b);
            if (move == null && b.isCheck(player1.getColor())) // check and can't move
                return -1;
            if (move == null) // no check but can't move
                return 0;

            result = b.makeMove(move);
            System.out.println(b);

            move = player2.getNextMove(b);
            if (move == null && b.isCheck(player2.getColor())) // check and can't move
                return 1;
            if (move == null) // no check but can't move
                return 0;

            result = b.makeMove(move);
            System.out.println(b);			
        }
    }
}
