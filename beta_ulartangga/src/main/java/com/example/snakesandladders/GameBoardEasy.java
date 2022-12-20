package com.example.snakesandladders;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameBoardEasy extends GameEasyController{
    private final PowerElement[] powerElements;
    private final Player[] players;
    private final Dice dice;

    public GameBoardEasy(ImageView player1moverEasy, ImageView player1tokenEasy, ImageView player2moverEasy, ImageView player2tokenEasy,
                        Button diceButton, ImageView diceHolder){
            int[][] laddersEasy = {{2,18},{4,36},{11,50},{22,41},{34,53},{37,58},{46,75},{59,78},{72,91},{73,94},{76,97},{79,100}};
            Ladder ladders1 = new Ladder(laddersEasy);
            int[][] snakes = {{84,35},{69,54},{52,29},{26,7}};
            Snake snakes1 = new Snake(snakes);
            this.powerElements = new PowerElement[2];
            this.powerElements[0] = snakes1;
            this.powerElements[1] = ladders1;
            this.players = new Player[2];
            this.players[0] = new Player(player1moverEasy, player1tokenEasy);
            this.players[1] = new Player(player2moverEasy, player2tokenEasy);
            this.dice = new Dice(diceButton, diceHolder);
    }

    

    private <T extends PowerElement> boolean checkForSnakesAndLadders(int player, int currentPlayerPosition, T element){
        int destinationTile = currentPlayerPosition;
        int[] destination = CoordinateLookup.getCoordinates(currentPlayerPosition);
        if (element.isPowerElement(currentPlayerPosition)){
            destination = element.destinationCoordinates(currentPlayerPosition);
            destinationTile = element.destinationTileNumber(currentPlayerPosition);
            this.players[player-1].moveToTile(destinationTile, destination);
            return true;
        }
        return false;
    }

    private int checkForSpecialTiles(int player) {
        int currentPlayerPosition = this.players[player-1].getCurrentPosition();
        if (currentPlayerPosition == 100)
            return 1;
        for (PowerElement element: this.powerElements) {
            if(checkForSnakesAndLadders(player, currentPlayerPosition, element))
                break;
        }
        return 0;
    }

    public int playeasy(MouseEvent event, int player){
        if (!(this.players[player-1].isLocked()))
            this.players[player-1].repeat(event);
        return checkForSpecialTiles(player);
    }

    public void moveTokenByOne(MouseEvent event, int player) {
        this.players[player-1].moveByOne();
    }

    public void rollDice(int player) {
        int dieRoll = dice.roll();
        if (this.players[player-1].getCurrentPosition() + dieRoll > 100)
            return;
        this.players[player-1].setCurrentDieRoll(dieRoll);
        if (dieRoll == 1)
            this.players[player-1].setLocked(false);
    }
}
