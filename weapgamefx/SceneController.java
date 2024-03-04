package com.example.weapgamefx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class SceneController {

    private  MatrixWithRandomTs matrixController;
    public Label GameTitle;
    public Label secondLabel;
    public Label errorLabel;
    public TextField playerCount;
    public TextField PlayerTextboxID;
    public Button StartButtonID;
    public Button EnterButtonID;
    public Button PlayerNamesID;

    private Stage stage;
    private Scene scene;
    private Parent root;

    int numofplayers;
    @FXML
    protected void onEnterClick() {  //upon clicking the enter for the player number count.
        int playernum = Integer.parseInt(playerCount.getText());
        if(playernum <=1)
        {
            errorLabel.setVisible(true);
        }
        else {
            numofplayers = playernum;
            playerCount.clear();
            EnterButtonID.setVisible(false);
            playerCount.setVisible(false);
            errorLabel.setVisible(false);
            PlayerNamesID.setVisible(true);

            //char [][] board = new char[size][size];

            //MakeBoard(board);

        }
    }
    List<Player> players = new ArrayList<>();
    int count = 0;

    public void setMatrixWithRandomTs(MatrixWithRandomTs matrixWithRandomTs) {
        this.matrixWithRandomTs = matrixWithRandomTs;
    }
    char [][] board = new char[10][10];
    private MatrixWithRandomTs matrixWithRandomTs;

    public void readPlayerName(ActionEvent event) throws IOException  //upon clicking on the enter in the player name textfield
    {
        if(count==0)
        {
            System.out.println("--CREATED A BOARD OMG!!!!!");

        }
        //System.out.println(numofplayers);
        if(count < numofplayers && matrixWithRandomTs != null)
        {
            String playerName = PlayerTextboxID.getText();
            PlayerTextboxID.clear();
            //matrixWithRandomTs.setPlayersNumber(4);
            matrixWithRandomTs.start(new Stage());
            stage.close();

//            count++;
//            matrixController.FillPlayerList(players,board,playerName);


        }
        else {
            System.out.println("limit reached!");
            MatrixWithRandomTs matrixSceneController = new MatrixWithRandomTs();
            matrixSceneController.start(new Stage());
            //matrixSceneController.start();
            //matrixSceneController.show();

//            root = FXMLLoader.load(getClass().getResource("game.fxml"));
//            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
        }
//        String playerName = PlayerTextboxID.getText();
//        PlayerTextboxID.clear();
//        count++;
//        players.add(new Player(playerName, 1, 2));
//        for (Player player : players) {
//            System.out.print(player.getName());
//        }
//        System.out.println(" ");
//        System.out.println(count);


//        if(counter != Integer.parseInt(playerCount.getText()))
//        {
//
//            //FillPlayerList();
//        }
//        else
//        {
//            System.out.println("LIMIT REACHED!!!");
//            System.out.println("LIMIT REACHED!!!");
//            System.out.println("LIMIT REACHED!!!");
//
//
//        }
//        counter++;
//        String playerName = PlayerNamesID.getText();
//        PlayerNamesID.clear();



    }
    public void startGame(ActionEvent event) throws IOException {
        int playernum = Integer.parseInt(playerCount.getText());

        if(playernum >1) {
            root = FXMLLoader.load(getClass().getResource("game.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(playernum <= 1)
        {
            errorLabel.setVisible(true);
        }
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}