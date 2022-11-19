package com.app;

import java.io.IOException;
import javafx.fxml.FXML;

public class History {
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}