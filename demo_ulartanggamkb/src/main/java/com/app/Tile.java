package com.app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile (int x, int y){
        setWidth(Board.Tile_Size);
        setHeight(Board.Tile_Size);

        setFill(Color.YELLOW);
        setStroke(Color.BLUE);
    }
}
