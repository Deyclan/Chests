/*
 * Check: An attack on a king by either an opposing piece or an opposing pawn is called check. When in check, a player must do one of the following:
    - Move the king so that it’s no longer under attack.
    - Block the attack by interposing a piece between the king and the attacker.
    - Capture the attacking piece.
* Checkmate: When a king is in check and can’t perform any of the preceding moves, it has been checkmated. If your king is checkmated, you lose the game. The term checkmate is commonly shortened to simply mate.
* Stalemate: Stalemate is the relatively rare situation when a player whose king isn’t in check has no legal move to make. Stalemate is considered a draw. Neither player wins, but the game is over.
 */
package finalchess;

import finalchess.interfaces.ChessBoardController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Main class which will init the game
 */
public class Chess {

    private ChessBoardController observer;
    private boolean isOver;

    public void run() {

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000)));
        timeline.play();
    }

    public void setObserver(ChessBoardController chessController) {
        this.observer = chessController;
    }

    public boolean isGameOver() {
        return isOver; //To change body of generated methods, choose Tools | Templates.
    }

}
