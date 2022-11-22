package com.app;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Board {
    
    public int rand;
    public Label randomResault;

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
    public static int player1YPos=572;

    public static int player2XPos=30;
    public static int player2YPos=572;

    public boolean gameStart = false;
    public Button gameButton;
    
    boolean show = false;
    public boolean setShow(boolean b) {
        return show ;
    }
    
    private Parent createConetent(){
        Pane root = new Pane();
        root.setPrefSize((width*Tile_Size)+210, (height*Tile_Size));
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Tile tile = new Tile(Tile_Size, Tile_Size);
                tile.setTranslateX(j*Tile_Size);
                tile.setTranslateY(i*Tile_Size);
                tileGroup.getChildren().add(tile);
            }
        }
        player1 = new Circle(15);
        player1.setId("player1");
        player1.getStyleClass().add("style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(15);
        player2.setId("player2");
        player2.getStyleClass().add("style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);


        tileGroup.getChildren().addAll(player1,player2);
        return root;
    }
}
