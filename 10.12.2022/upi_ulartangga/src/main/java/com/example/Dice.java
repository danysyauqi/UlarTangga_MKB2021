package com.example;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


/**
 * JavaFX App
 */
public class Dice extends Application {
        
    public int rand;
    public Label randResault;

    public int cirPos[][]= new int[10][10];
    public int ladderPosition[][]=new int[6][3];

    public static final int Tile_Size = 60;
    public static final int width = 10;
    public static final int height = 10;
    private Group tileGroup = new Group();

    public Circle player1;
    public Circle player2;
    
    public int playerPosition1= 1;
    public int playerPosition2= 1;

    public boolean player1Turn = true;
    public boolean player2Turn = true;

    public static int player1XPos=30;
    public static int player1YPos=570;

    public static int player2XPos=30;
    public static int player2YPos=570;

    public int posCir1 = 1;
    public int posCir2 = 1;

    public boolean gameStart = true;
    public Button gameButton;

    
    private Parent createConetent(){
        StackPane root = new StackPane();
        root.setPrefSize((width*Tile_Size), (height*Tile_Size));
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Tile tile = new Tile(Tile_Size, Tile_Size);
                tile.setTranslateX(j*Tile_Size);
                tile.setTranslateY(i*Tile_Size);
                tileGroup.getChildren().add(tile);
            }
        }
        player1 = new Circle(30);
        player1.setId("Player 1");
        player1.getStyleClass().add("resources/css/style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(30);
        player2.setId("Player 2");
        player2.getStyleClass().add("resources/css/style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);
        // Setting button jenis, fungsi, dll
        Button button1 = new Button("Player 1");
        button1.setTranslateX(630);
        button1.setTranslateY(140);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                if (gameStart){
                    if (player1Turn){
                        getDiceValue();
                        randResault.setText(String.valueOf(rand));
                        move1Player();
                        translatePlayer(player1XPos, player1YPos, player1);
                        player1Turn = false;
                        player2Turn = true;
                        playerPosition1 += rand;
                    }
                }
            }
        });
        // Setting button jenis, fungsi, dll 
        Button button2 = new Button("Player 2");
        button2.setTranslateX(720);
        button2.setTranslateY(140);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                if (gameStart){
                    if (player2Turn){
                        getDiceValue();
                        randResault.setText(String.valueOf(rand));
                        move2Player();
                        translatePlayer(player2XPos, player2YPos, player2);
                        player2Turn = false;
                        player1Turn =true;
                        playerPosition2 += rand;
                    }
                }
            }
        });
        // Setting button jenis, fungsi, dll
        gameButton = new Button("Start Game");
        gameButton.setTranslateX(670);
        gameButton.setTranslateY(80);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                gameButton.setText("Game Started");
                player1XPos = 30;
                player1YPos =570;

                player2XPos = 30;
                player2YPos =570;

                player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);

                player2.setTranslateX(player2XPos);
                player2.setTranslateY(player2YPos);

                gameStart = true;
            }
        });
        
        randResault = new Label("0");
        randResault.setTranslateX(770);
        randResault.setTranslateY(80);
        /*
         * Image img =  new Image("snakebg.jpg");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(550);
        bgImage.setFitWidth(550);
         */
        


        // Munculkan semua button
        tileGroup.getChildren().addAll(player1,player2,button1,button2,gameButton,randResault);
        return root;
    }

    private void move1Player(){
        for(int i = 0; i < rand; i++){
            if (posCir1 % 2 == 1){
                player1XPos += 60;

            }
            if (posCir1 % 2 == 0){
                player1XPos -= 60;
            }
            if (player1XPos > 570){
                player1YPos -= 60;
                player1XPos -= 60;
                posCir1++;
            }
            if(player1XPos < 30){
                player1YPos -= 60;
                player1XPos += 60;
                posCir1++;
            }

            if(player1XPos <20 || player1YPos <20){
                player1YPos = 30;
                player1XPos = 30;
                gameStart = false;
                randResault.setText("Player one won");
                gameButton.setText("Start Again");
            }
        }
    }
    private void move2Player(){
        for(int i = 0; i < rand; i++){
            if (posCir2 % 2 == 1){
                player2XPos += 60;
            }
            if (posCir2 % 2 == 0){
                player2XPos -= 60;
            }
            if (player2XPos > 570){
                player2YPos -= 60;
                player2XPos -= 60;
                posCir2++;
            }
            if(player2XPos < 30){
                player2YPos -= 60;
                player2XPos += 60;
                posCir2++;
            }

            if(player2XPos <20 || player2YPos <20){
                player2YPos = 30;
                player2XPos = 30;
                gameStart = false;
                randResault.setText("Player Two won");
                gameButton.setText("Start Again");
            }
        }
    }
    private void getDiceValue(){
        rand = (int)(Math.random()*6+1);
    }

    private void translatePlayer(int x, int y, Circle b){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(createConetent());
        primaryStage.setTitle("Snakes And Ladder MKB 2021");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void run() {
        launch();
    }

}