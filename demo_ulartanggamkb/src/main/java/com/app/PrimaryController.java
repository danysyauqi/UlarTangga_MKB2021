package com.app;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    private void switchToHistory() throws IOException{
        App.setRoot("history");
    }
    @FXML
    private void switchToEasy() throws IOException{
        App.setRoot("easy");
    }
    @FXML
    private void switchToMedium() throws IOException{
        App.setRoot("medium");
    }
    @FXML
    private void switchToHard() throws IOException{
        App.setRoot("hard");
    }
    @FXML
    private void switchToHelp() throws IOException{
        App.setRoot("help");
    }
    @FXML
    private void switchToAuthor() throws IOException{
        App.setRoot("author");
    }
}
