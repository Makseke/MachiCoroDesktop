package org.machicoro;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WindowLaunchController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}