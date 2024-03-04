package com.example.weapgamefx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UpdateMatrixOnButtonClick extends Application {

    private static final int MATRIX_SIZE = 5;
    private static final int NUMBER_OF_Ts = 3;

    private List<String> matrix;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));

        // Create initial matrix
        matrix = createMatrix(MATRIX_SIZE);

        // Create a VBox to hold the matrix
        VBox matrixContainer = new VBox();
        updateMatrixUI(matrixContainer);

        // Button to add '$' in a random slot
        Button addButton = new Button("Add $");
        addButton.setOnAction(e ->
        {
            addDollarRandomly();
            updateMatrixUI(matrixContainer);
        });

        root.getChildren().addAll(addButton, matrixContainer);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Update Matrix on Button Click");
        primaryStage.show();
    }

    private List<String> createMatrix(int size) {
        List<String> matrix = new ArrayList<>();
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < size; i++) {
            row.append("_".repeat(size));
            matrix.add(row.toString());
            row.setLength(0); // Clear StringBuilder for the next row
        }
        return matrix;
    }

    private void addDollarRandomly() {
        Random random = new Random();
        int rows = matrix.size();
        int cols = matrix.get(0).length();

        int row = random.nextInt(rows);
        int col = random.nextInt(cols);

        String currentRow = matrix.get(row);
        char[] chars = currentRow.toCharArray();
        chars[col] = '$';
        matrix.set(row, String.valueOf(chars));
    }

    private void updateMatrixUI(VBox matrixContainer) {
        matrixContainer.getChildren().clear();

        for (String row : matrix) {
            HBox rowBox = new HBox();
            String rowWithSpaces = addSpacesBetweenCharacters(row);
            for (char cell : rowWithSpaces.toCharArray()) {
                Text cellText = new Text(Character.toString(cell));
                cellText.setStyle("-fx-border-color: black; -fx-padding: 5px;");
                rowBox.getChildren().add(cellText);
            }
            matrixContainer.getChildren().add(rowBox);
        }
    }

    private String addSpacesBetweenCharacters(String str) {
        return str.chars()
                .mapToObj(ch -> (char) ch + "  ")
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
