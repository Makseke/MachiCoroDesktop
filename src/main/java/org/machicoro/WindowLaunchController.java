package org.machicoro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

import static java.lang.System.exit;

public class WindowLaunchController {

    @FXML
    protected void onServerButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WindowApp.class.getResource("server-launch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        WindowApp.primaryStage.setTitle("MachiCoro V0.1!");
        WindowApp.primaryStage.setScene(scene);
        WindowApp.primaryStage.show();
    }

    @FXML
    protected void onClientButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WindowApp.class.getResource("client-launch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        WindowApp.primaryStage.setTitle("MachiCoro V0.1!");
        WindowApp.primaryStage.setScene(scene);
        WindowApp.primaryStage.show();
    }

    @FXML
    protected void onExitButtonClick() {
        exit(0);
    }
}