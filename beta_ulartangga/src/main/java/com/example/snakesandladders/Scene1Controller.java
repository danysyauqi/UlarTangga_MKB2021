package com.example.snakesandladders;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class Scene1Controller {

    AudioClip musicsfx;
    private Stage stage;
    private Scene scene;
    private Parent root;

    // -----------------------------------------------------------------------------------------------------------------

    static String playerName1;
    static String playerName2;

    @FXML
    private TextField player1name;

    @FXML
    private TextField player2name;

    @FXML
    private Button exitButton;

    @FXML
    private ToggleButton sfx;

    // -----------------------------------------------------------------------------------------------------------------
    public void playeasy (ActionEvent event) throws IOException{
        playerName1 = player1name.getText();
        playerName2 = player2name.getText();
        root = FXMLLoader.load(getClass().getResource("easy.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void play(ActionEvent event) throws IOException {
        playerName1 = player1name.getText();
        playerName2 = player2name.getText();
        root = FXMLLoader.load(getClass().getResource("normal.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void playhard(ActionEvent event) throws IOException {
        playerName1 = player1name.getText();
        playerName2 = player2name.getText();
        root = FXMLLoader.load(getClass().getResource("hard.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void sfxmusic(ActionEvent event) throws IOException{
        musicsfx = new AudioClip(Scene1Controller.class.getResource("game.mp3").toString());
        musicsfx.play();
    }

    public void exit(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Do you want exit?");
        alert.setContentText("Click OK to exit, or Cancel to stay.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) exitButton.getScene().getWindow();
            stage.close();

        }
    }

    // -----------------------------------------------------------------------------------------------------------------

}
