/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalchess;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

/**
 *
 * @author Nejko
 */
public class Menu extends Scene {

    public Menu(Group root, double width, double height, Main parent) {
        super(root, width, height);
        
        final ImageView imv = new ImageView();
        Image img = new Image(Menu.class.getResourceAsStream("chessBG.jpeg"));
        imv.setImage(img);
        root.getChildren().add(imv);
        
        Button btn1 = new Button("Easy");
        btn1.setOnAction(e -> parent.playChess(0));
        btn1.setMinSize(160, 60);
        btn1.setLayoutX(300 - btn1.getMinWidth()/2); 
        btn1.setLayoutY(125);
        btn1.setStyle("-fx-background-color: b5e37f;");
        root.getChildren().add(btn1);
        
        Button btn2 = new Button("Medium");
        btn2.setOnAction(e -> parent.playChess(1));
        btn2.setMinSize(160, 60);
        btn2.setLayoutX(300 - btn2.getMinWidth()/2); 
        btn2.setLayoutY(275);
        btn2.setStyle("-fx-background-color: eeda6a");
        root.getChildren().add(btn2);
        
        Button btn3 = new Button("Hard");
        btn3.setOnAction(e -> parent.playChess(2));
        btn3.setMinSize(160, 60);
        btn3.setLayoutX(300 - btn3.getMinWidth()/2); 
        btn3.setLayoutY(425);
        btn3.setStyle("-fx-background-color: c63349");
        root.getChildren().add(btn3);
        
    }
    
}
