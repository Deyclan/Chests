/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalchess.interfaces;

import chess.Board;
import finalchess.Chess;
import finalchess.Main;
import finalchess.pieces.*;
import finalchess.players.*;
import static finalchess.players.Player.BLACK;
import static finalchess.players.Player.WHITE;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nejko
 */
public class ChessBoardController implements Initializable {

    private Chess game;
    private Main main;
    public Board boardM = null;
    public Player currentPlayer;
    @FXML
    public GridPane boardMoves;
    @FXML
    public Label currentPlayerString;
    @FXML
    public TextField inputMove;
    public String userInput;
    public int turn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert boardMoves != null : "fx:id=\"boardMoves\" was not injected: check your FXML file 'ChessBoard.fxml'.";
        assert currentPlayerString != null : "fx:id=\"currentPlayerString\" was not injected: check your FXML file 'ChessBoard.fxml'.";
        assert inputMove != null : "fx:id=\"inputMove\" was not injected: check your FXML file 'ChessBoard.fxml'.";
        //assert lbNiveau != null : "fx:id=\"lbNiveau\" was not injected: check your FXML file 'ChessBoard.fxml'.";
    }

    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            userInput = inputMove.getText();
            // clear text
            inputMove.setText("");
        }
    }

    public void init() {
        game.setObserver(this);

        

        this.update();
    }

    public void update() {

        //Si la partie est finie, affiche une page game over
        if (game.isGameOver()) {
            endGame();
        } else {

            //actualise le jeu
            

            //actualise les infos
            currentPlayerString.setText(currentPlayer.toString());
            
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setGame(Chess game) {
        this.game = game;
    }

    /**
     * Starts a new game, with 4 human players and timeline
     */
    public void start() {

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                ae -> transitionMove()));
        timeline.play();
    }

    /**
     * Used to handle passes
     *
     * @param string
     */
    public void makeMove(String string) {
        if (string.equals("")) {
            // DO nothing
        } else {
            // get input 

            transitionMove();
        }
    }

    private Player nextPlayer() {
        if (currentPlayer.getColor() == WHITE) {
            currentPlayer.setColor(BLACK);
        } else {
            currentPlayer.setColor(WHITE);
        }
        return currentPlayer;
    }

    /**
     * Run between two goes - to set and unset panels and the piece-preparer, to
     * check for game ends, etc...
     */
    private void transitionMove() {

        // Check for the end of the game
        //Change player
        currentPlayer = nextPlayer();
        if (currentPlayer.getColor() == WHITE) {
            turn++;
        }

    }

    /**
     * Shows the game outcome. Is called only when the game ends.
     */
    private void endGame() {
        //end of game
    }

}
