package org.machicoro.controller.window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.machicoro.WindowApp;
import org.machicoro.config.WindowConfig;
import org.machicoro.service.ServerService;

import java.io.IOException;

import static java.lang.System.exit;

public class WindowLaunchController {

    @FXML
    protected void onServerButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WindowApp.class.getResource("server-launch.fxml"));
        WindowConfig.setLoader(fxmlLoader);
        Scene scene = new Scene(fxmlLoader.load(), WindowConfig.getWindowWight(), WindowConfig.getWindowHeight());
        WindowApp.primaryStage.setTitle(WindowConfig.getWindowName());
        WindowApp.primaryStage.setScene(scene);
        WindowApp.primaryStage.show();
    }

    @FXML
    protected void onClientButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WindowApp.class.getResource("client-launch.fxml"));
        WindowConfig.setLoader(fxmlLoader);
        Scene scene = new Scene(fxmlLoader.load(), WindowConfig.getWindowWight(), WindowConfig.getWindowHeight());
        WindowApp.primaryStage.setTitle(WindowConfig.getWindowName());
        WindowApp.primaryStage.setScene(scene);
        WindowApp.primaryStage.show();
    }

    @FXML
    protected void onExitButtonClick() {
        exit(0);
    }
}