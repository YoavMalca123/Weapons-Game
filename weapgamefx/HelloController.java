package com.example.weapgamefx;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    public Label GameTitle;
    public Label secondLabel;
    public Label errorLabel;
    public TextField playerCount;
    public Button startbutton;

    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    protected void onEnterClick() {
        int playernum = Integer.parseInt(playerCount.getText());
        if(playernum <=1)
        {
            errorLabel.setVisible(true);
        }
        else {
            errorLabel.setVisible(false);
        }
    }
    @FXML
    protected void startGame() throws IOException {
        int playernum = Integer.parseInt(playerCount.getText());
        if(playernum >= 1)
        {
//            root = FXMLLoader.load(getClass().getResource("game"));
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
            GameTitle.setVisible(false);
            secondLabel.setVisible(false);
            errorLabel.setVisible(false);
            playerCount.setVisible(false);
            startbutton.setVisible(false);
        }

    }
}