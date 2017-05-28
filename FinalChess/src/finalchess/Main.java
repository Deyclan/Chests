/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalchess;

import finalchess.interfaces.ChessBoardController;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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

    public void playChess(int difficulty) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("interfaces/ChessBoard.fxml"));
            AnchorPane chess = (AnchorPane) loader.load();
            this.primaryStage.setScene(new Scene(chess));

            Chess game = new Chess(difficulty);

            ChessBoardController controller = loader.getController();
            controller.setMain(this);
            controller.setGame(game);

            controller.init();
            game.run();
        } catch (IOException e) {
            System.out.println("Failed to open Chess : /n"+e);
        }
    }

}
