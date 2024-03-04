package com.example.weapgamefx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class MatrixWithRandomTs extends Application {

    private Scene scene;
    private int size = 10;
    private List<Entity> entities;
    private Map<Player, Character> playerSymbols;

    //public Label curDeck;
    private static final int MATRIX_SIZE = 10;
    private static final int NUMBER_OF_Ts = 3;

    public MatrixWithRandomTs() {
        this.size = 10;
        //this.curDeck = curDeck;
        //board = new char[size][size];
        entities = new ArrayList<>();
        playerSymbols = new HashMap<>();

        //MakeBoard();
    }

    public char[][] MakeBoard(char [][] board) {
        for (int i = 0; i < size; i++) {
            Arrays.fill(board[i], '_');
        }
        // Add trees, swords, fireBalls, and magicRings randomly
        addEntities('T', 3,board); // 3 trees
        addEntities('S', 2,board); // 2 swords
        addEntities('F', 2,board); // 2 fireBalls
        addEntities('R', 2,board); // 2 magicRings

        //printBoard();
        return board;
    }
    public void addEntities(char symbol, int count, char[][]board) {
        while (count > 0) {
            int x = (int) (Math.random() * size);
            int y = (int) (Math.random() * size);

            if (board[x][y] == '_') {
                Entity entity = new Entity(symbol, x, y);
                entities.add(entity);
                board[x][y] = symbol;
                count--;
            }
        }
    }
    public void printBoard(VBox matrixContainer, char[][]board) {
        //VBox root = new VBox();
        matrixContainer.getChildren().clear();
        for (int i = 0; i < size; i++) {
            HBox rowBox = new HBox();
            for (int j = 0; j < size; j++) {
                Text cellText = new Text(Character.toString(board[i][j]));
                cellText.setStyle("-fx-border-color: blue; -fx-size: 5px; -fx-padding: 5px; -fx-font-size: 18px;");
                Text space = new Text("   ");
                rowBox.getChildren().add(cellText);
                rowBox.getChildren().add(space);
                //rowBox.getChildren().add(up);
            }
            matrixContainer.getChildren().add(rowBox);
        }
        //matrixContainer.setPadding(new Insets(10));
    }

    public void addPlayer(Player player , char[][]board) {
        char playerSymbol = (char) ('0' + (playerSymbols.size() + 1));
        playerSymbols.put(player, playerSymbol);
        board[player.getX()][player.getY()] = playerSymbol;
    }
    public char getEntity(int x, int y, char[][]board) {
        return board[x][y];
    }
    public boolean isInBoard(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
    public void updatePlayerPosition(Player player, int newX, int newY, char[][]board) {
        char playerSymbol = playerSymbols.get(player);
        board[player.getX()][player.getY()] = '_';
        board[newX][newY] = playerSymbol;
        player.setPosition(newX, newY);
    }
    public void deleteEntity(int x, int y, char[][] board) {
        for (Entity entity : entities) {
            if (entity.getX() == x && entity.getY() == y) {
                board[x][y] = '_';
                entities.remove(entity);
                break;
            }
        }
    }

    public void removePlayer(Player player, char[][]board) {
        char playerSymbol = playerSymbols.get(player);
        playerSymbols.remove(player);
        board[player.getX()][player.getY()] = '_';
        for (Player p : playerSymbols.keySet()) {
            char symbol = playerSymbols.get(p);
            int x = p.getX();
            int y = p.getY();
            board[x][y] = symbol;
        }
    }

    public boolean isEmpty(int x, int y,char[][]board) {
        return board[x][y] == '_' || board[x][y] == 'S' || board[x][y] == 'F' || board[x][y] == 'R';
    }
    public Map<Player, Character> getPlayerSymbols(char[][]board) {
        return playerSymbols;
    }

    public String Move(char[][] board, String move, int playerNum, List<Player> players)
    {

        int newX = players.get(playerNum).getX();
        int newY = players.get(playerNum).getY();
        Label labelToUpdate = (Label) scene.lookup("#CurDeckID");
        Label CurPlayerUpdate = (Label) scene.lookup("#CurPlayerID");
        Label messegeUpdate = (Label) scene.lookup("#messegesID");
        messegeUpdate.setText("");
        if(playerNum >=0 && playerNum <= players.size()-1)
        {
            int nextNumber = (playerNum + 1) ;
            if (nextNumber > players.size()-1)
            {
                CurPlayerUpdate.setText("It Is Currently " + players.get(0).getName() + "'s Turn");
                labelToUpdate.setText(players.get(0).printWeaponQueue());
            }
            else
            {
                CurPlayerUpdate.setText("It Is Currently " + players.get(nextNumber).getName()+ "'s Turn");
                labelToUpdate.setText(players.get(nextNumber).printWeaponQueue());
            }
        }

        switch (move) {
            case "W":
                newX--;
                break;
            case "A":
                newY--;
                break;
            case "S":
                newX++;
                break;
            case "D":
                newY++;
                break;
            default:
                //System.out.println("Invalid move. Try again.");
                return "0"; // Invalid move(not in the moveset)
        }
        //return "1";
        if(newX >= 0 && newX < size && newY >= 0 && newY < size)  //if the move is on the board
        {
            if(getEntity(newX,newY,board) == 'S')
            {
                deleteEntity(newX, newY, board);
                updatePlayerPosition(players.get(playerNum),newX,newY,board);
                players.get(playerNum).addWeapon(new Weapon('S'));
                return "2";
            }
            if(getEntity(newX,newY,board) == 'F')
            {
                deleteEntity(newX, newY, board);
                updatePlayerPosition(players.get(playerNum),newX,newY,board);
                players.get(playerNum).addWeapon(new Weapon('F'));
                return "3";
            }
            if(getEntity(newX,newY,board) == 'R')
            {
                deleteEntity(newX, newY, board);
                updatePlayerPosition(players.get(playerNum),newX,newY,board);
                players.get(playerNum).addWeapon(new Weapon('R'));
                return "4";
            }
            if (getEntity(newX,newY,board) == '_') {  //checks if the slot is just empty '_'
                updatePlayerPosition(players.get(playerNum),newX,newY,board);
                return "1"; // Valid move
            }
            else if (isPlayerAt(newX, newY, players.get(playerNum))) {  //checks if there is another player on the slot we want, if yes then duel
                Player opponent = getPlayerAt(newX, newY, players.get(playerNum));  //returns the opponent in that slot
                if (opponent != null)
                {
                    //System.out.println("THERE IS ANOTHER PLAYER IN THAT SLOT! PLAYER IS: " + opponent.getName());
                    String toRemove = fight(opponent,players.get(playerNum));
                    if(toRemove != players.get(playerNum).getName())  //if our player won, we still need to move it to the new slot
                    {
                        updatePlayerPosition(players.get(playerNum),newX,newY,board);
                    }
                    RemovePlayerEntirely(toRemove,players,board);
                }
            }
        }
        return "0";
    }

    public boolean isPlayerAt(int x, int y, Player playerToCheck) {
        for (Player player : playerSymbols.keySet()) {
            if (player != playerToCheck && player.getX() == x && player.getY() == y) {  //checks all the players in the game and checks if their cords are the same as the new cords that we have inputed. if it matches then that means that there is another player there.
                return true;
            }
        }
        return false;
    }
    public Player getPlayerAt(int x, int y, Player playerToCheck) {
        for (Player player : playerSymbols.keySet()) {
            if (player != playerToCheck && player.getX() == x && player.getY() == y) {  //checks all the players in the game and checks if their cords are the same as the new cords that we have inputed. if it matches then that means that there is another player there.
                return player;
            }
        }
        return null;
    }


    public String fight(Player opponent, Player person) {
        Weapon playerWeapon = person.getWeapon();
        Weapon opponentWeapon = opponent.getWeapon();

        Label labelToUpdate = (Label) scene.lookup("#CurPlayerID");
        Label messegeupdate = (Label) scene.lookup("#messegesID");
        //messegeupdate.setText("New Text");
//        if (labelToUpdate != null) {
//            labelToUpdate.setText("New Text");
//        }

        if (playerWeapon != null && opponentWeapon != null)
        {
            if (playerWeapon.stronger(opponentWeapon)) {
                // Player wins
                opponent.giveWeapons(person);
                //System.out.println(person.getName() + " wins the duel!");
                messegeupdate.setText(person.getName() + " wins the duel!");
                return opponent.getName();

            } else if (opponentWeapon.stronger(playerWeapon)) {
                // Opponent wins

                person.giveWeapons(opponent);
                messegeupdate.setText(opponent.getName() + " wins the duel!");
                //System.out.println(opponent.getName() + " wins the duel!");
                return person.getName();

            } else {
                // Duel is a draw
                messegeupdate.setText("Duel is a draw!");
            }
        } else if (playerWeapon == null && opponentWeapon == null) {
            // Duel without weapons is a draw
            messegeupdate.setText("Duel is a draw!");
            return "DUELISDRAW";
        } else if (playerWeapon == null) {
            // Player loses due to lack of weapon
            person.giveWeapons(opponent);
            messegeupdate.setText(person.getName() + " loses the duel due to lack of a weapon!");
            return person.getName();
        } else {
            // Opponent loses due to lack of a weapon
            opponent.giveWeapons(person);
            messegeupdate.setText(opponent.getName() + " loses the duel due to lack of a weapon!");
            return opponent.getName();
        }
        return "something";
    }

    public void RemovePlayerEntirely(String validMove , List<Player> players, char[][]board)
    {
        String playerNameToRemove = validMove; // Replace with the actual player name you want to remove
        for (Player player : players) {
            if (player.getName()==playerNameToRemove) {
                removePlayer(getPlayerByName(validMove),board);
                //System.out.println("removePlayer part");
                players.remove(player);
                //System.out.println("players.remove part");
                break; // Player found and removed, exit the loop
            }
        }

    }
    public Player getPlayerByName(String PlayerName) {
        for (Player player : playerSymbols.keySet()) {
            if (player.getName() == PlayerName) {  //checks all the players in the game and checks if their cords are the same as the new cords that we have inputed. if it matches then that means that there is another player there.
                return player;
            }
        }
        return null;
    }


    @Override
    public void start(Stage primaryStage) {

        VBox test = new VBox();
        TextField PlayerNumber = new TextField();
        TextField PlayerNames = new TextField();
        TextField symbolInput = new TextField();
        symbolInput.setPromptText("Enter move: ");
        symbolInput.setStyle("-fx-font-size: 14px; " +
                "-fx-padding: 8px; " +
                "-fx-background-color: #f5f5f5; " +
                "-fx-border-color: #ccc; " +
                "-fx-border-radius: 5;");

        Label curPlayer = new Label();
        Label curDeck = new Label();
        Label messeges = new Label();
        curPlayer.setVisible(false);
        curDeck.setVisible(false);
        messeges.setVisible(false);

        curPlayer.setId("CurPlayerID");
        curDeck.setId("CurDeckID");
        messeges.setId("messegesID");
        curDeck.setStyle("-fx-size: 5px; -fx-padding: 5px; -fx-font-size: 18px;" +
                "-fx-background-color: rgb(240, 240, 240); " +
                "-fx-background-radius: 10; " + // Adjust the radius value as needed
                "-fx-border-color: rgb(230, 230, 230); " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 10;"); // Adjust the radius value similar to the background radius
        curPlayer.setStyle("-fx-size: 5px; -fx-padding: 5px; -fx-font-size: 18px;" +
                "-fx-background-color: rgb(240, 240, 240); " +
                "-fx-background-radius: 10; " + // Adjust the radius value as needed
                "-fx-border-color: rgb(230, 230, 230); " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 10;"); // Adjust the radius value similar to the background radius
        messeges.setStyle("-fx-size: 5px; -fx-padding: 5px; -fx-font-size: 18px; -fx-alignment: center;" +
                "-fx-background-color: rgb(240, 240, 240); " +
                "-fx-background-radius: 10; " + // Adjust the radius value as needed
                "-fx-border-color: rgb(230, 230, 230); " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 10;"); // Adjust the radius value similar to the background radius
        PlayerNumber.setStyle("-fx-font-size: 14px; " +
                "-fx-padding: 8px; " +
                "-fx-background-color: #f5f5f5; " +
                "-fx-border-color: #ccc; " +
                "-fx-border-radius: 5;");
        PlayerNames.setStyle("-fx-font-size: 14px; " +
                "-fx-padding: 8px; " +
                "-fx-background-color: #f5f5f5; " +
                "-fx-border-color: #ccc; " +
                "-fx-border-radius: 5;");


        symbolInput.setVisible(false);
        PlayerNames.setVisible(false);
        char [][] board = new char[size][size];
        MakeBoard(board);
        List<Player> players = new ArrayList<>();

        VBox root = new VBox();
        root.setStyle("-fx-background-image: url('file:///C:/Users/YOAV/Desktop/SCHOOL/Java/backgroundtest.jpg');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: cover;");
        //C:\Users\YOAV\Desktop\SCHOOL\Java
        root.setPadding(new Insets(10));
        VBox matrixContainer = new VBox();
        //List<Player> players = new ArrayList<>();
        //char [][] board = new char[size][size];
        //MakeBoard(board);
        HBox matrixN2test = new HBox();
        matrixN2test.getChildren().addAll(matrixContainer, test);

        PlayerNumber.setPromptText("Enter Player Ammount: ");
        PlayerNames.setPromptText("Enter Player Name: ");

        test.getChildren().addAll(PlayerNumber, PlayerNames, curPlayer,curDeck);
        int []numPlayers = {0};
        PlayerNumber.setOnAction(e -> {
                    numPlayers[0] = Integer.parseInt(PlayerNumber.getText());
                    if(PlayerNumber!=null && numPlayers[0]>1)
                    {
                        PlayerNames.setVisible(true);
                        PlayerNumber.setVisible(false);
                    }
                });

        String []playerName={""};
        int []countadd ={0};
        PlayerNames.setOnAction(e -> {
            if(countadd[0] >= numPlayers[0]-1)
            {
                PlayerNames.setVisible(false);
                symbolInput.setVisible(true);
                curPlayer.setVisible(true);
                curDeck.setVisible(true);
                messeges.setVisible(true);
                printBoard(matrixContainer, board);
            }
            if(countadd[0] < numPlayers[0])
            {
                playerName[0] = PlayerNames.getText();
                int foundspot = 0;
                int x = (int) (Math.random() * 10);
                int y = (int) (Math.random() * 10);
                while (foundspot == 0) {
                    if (getEntity(x, y,board) == '_') {
                        Player player = new Player(playerName[0], x, y);
                        players.add(player); // Start all players at random (x, y)
                        foundspot = 1;
                        addPlayer(player,board);
                        printBoard(matrixContainer, board);
                    }
                }
//                for (Player player : players) {
//                    addPlayer(player, board);
//
//                }
                countadd[0]++;
                //System.out.println("THE COUNTADD IS: " + countadd[0]);
                PlayerNames.clear();
                //printBoard(matrixContainer, board);
            }



        });




          //initialize our board with all the weapons, trees, etc.
        printBoard(matrixContainer, board);

        root.getChildren().addAll(symbolInput,messeges);

        curPlayer.setText("");
        curDeck.setText("");
        int[] counter = {0}; // Use an array to hold the counter

        symbolInput.setOnAction(e -> {
            //curPlayer.setText("It is currently " + players.get(counter[0]).getName() + "'s turn");
            String symbol = symbolInput.getText();
            if(players.size() <= 1)
            {
                messeges.setText("The winner is: " + players.get(0).getName() + "!");
                symbolInput.setVisible(false);
            }
            if (counter[0] != players.size())
            {
                if (!symbol.isEmpty()) {

                    //players.get(counter[0]).printWeaponQueue();
                    if (Move(board, symbol, counter[0], players) != "0") {
                        printBoard(matrixContainer, board);
                        symbolInput.clear();
                    } else{

                        //curPlayer.setText("IDK WHAT");
                        printBoard(matrixContainer, board);
                        symbolInput.clear();
                    }


                    //symbol = symbolInput.getText();
                    counter[0]++;
                    //System.out.println("i is currently: " + counter[0]);
                    //System.out.println("numPlayers is currently: " + numPlayers);
                    if(counter[0] >= players.size())
                    {
                        counter[0] = 0;
                    }
                    if(players.size() <= 1)
                    {
                        messeges.setText("The winner is: " + players.get(0).getName() + "!");
                        symbolInput.setVisible(false);
                    }
                    
                }


            }
        });

        root.getChildren().addAll(matrixN2test);
        scene = new Scene(root, 450, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("WEAPON'S GAME!!!");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
