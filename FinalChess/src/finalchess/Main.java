/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalchess;

import finalchess.interfaces.ChessBoardController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Nejko
 */
public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    public Scene menu;

    @Override
    public void start(Stage primaryStage) {
       this.primaryStage = primaryStage;
       menu = new Menu(new Group(), 600,600, this);

        primaryStage.show();
        toMenu();
    }

    /**
     * Sets the scene to the Menu
     */
    public void toMenu() {
        this.primaryStage.setScene(this.menu);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void playChess() {
        ChessBoardController game = new ChessBoardController(new Group(), 600, 400, this);
        this.primaryStage.setScene(game);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> game.start()));
        timeline.play();
    }

}
