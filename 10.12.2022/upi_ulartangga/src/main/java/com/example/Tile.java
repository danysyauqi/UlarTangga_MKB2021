package com.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile (int x, int y){
        setWidth(Dice.Tile_Size);
        setHeight(Dice.Tile_Size);

        setFill(Color.YELLOW);
        setStroke(Color.BLUE);
    }
}
