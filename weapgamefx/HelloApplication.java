package com.example.weapgamefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Weapons game");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
//package com.example.weapgamefx;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.GridPane;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//public class HelloApplication extends Application {
//
//    // Sample matrix
//    private int[][] matrix = {
//            {1, 2, 3},
//            {4, 5, 6},
//            {7, 8, 9}
//    };
//
//    @Override
//    public void start(Stage primaryStage) {
//        GridPane gridPane = new GridPane();
//
//        // Loop through the matrix and add elements to the GridPane
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                Text text = new Text(Integer.toString(matrix[i][j]));
//                gridPane.add(text, j, i); // Add text to the grid
//            }
//        }
//
//        Scene scene = new Scene(gridPane, 200, 200);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Matrix Display");
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
