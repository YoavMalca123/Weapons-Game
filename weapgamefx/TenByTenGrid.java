package com.example.weapgamefx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;

public class TenByTenGrid extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5); // Horizontal gap between cells
        gridPane.setVgap(5); // Vertical gap between cells

        // Create a 10x10 grid of Text nodes and add them to the GridPane
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                //Text text = new Text("Row: " + row + "\nCol: " + col); // Text to display
                Text text = new Text("s");
                gridPane.add(text, col, row); // Add text to the grid at specified row and column
            }
        }
        Text test = new Text("T");
        gridPane.add(test,1,3);

//        MakeBoard();
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("10x10 Grid with Text");
        primaryStage.show();
    }
//    public void MakeBoard() {
//        Text Tree = new Text("T");
//        Text Sword = new Text("S");
//        Text Fireball = new Text("F");
//        Text Ring = new Text("R");
//
//
//        // Add trees, swords, fireBalls, and magicRings randomly
//        addEntities(Tree, 3, new GridPane()); // 3 trees
//        addEntities(Sword, 2,new GridPane()); // 2 swords
//        addEntities(Fireball, 2,new GridPane()); // 2 fireBalls
//        addEntities(Ring, 2,new GridPane()); // 2 magicRings
//    }
//
//
//    public void addEntities(Text symbol, int count, GridPane gridPane) {
//        while (count > 0) {
//            int x = (int) (Math.random() * 10);
//            int y = (int) (Math.random() * 10);
//            Text targetText = null;
//
//            for (javafx.scene.Node node : gridPane.getChildren()) {
//                if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null &&
//                        GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
//                    targetText = (Text) node;
//                    break;
//                }
//            }
//            if (targetText.getText().equals('_')) {
//                gridPane.add(symbol, x, y);
//                count--;
//            }
//        }
//    }
//

    public static void main(String[] args) {
        launch(args);
    }
}

