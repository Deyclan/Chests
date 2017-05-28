/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalchess;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 *
 * @author Nejko
 */
public class Menu extends Scene {

    public Menu(Group root, double width, double height, Main parent) {
        super(root, width, height);
        
        Button button2 = new Button("Play");
        button2.setOnAction(e -> parent.playChess());
        button2.setMinSize(160, 60);
        button2.setLayoutX(150 - button2.getMinWidth() / 2); 
        button2.setLayoutY(500);
        button2.getStyleClass().add("button1");
        root.getChildren().add(button2);
        
        
        
    }
    
}
