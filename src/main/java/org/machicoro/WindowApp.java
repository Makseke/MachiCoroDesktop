package org.machicoro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowApp extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(WindowApp.class.getResource("launch-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("MachiCoro V0.1!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void toServer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WindowApp.class.getResource("server-launch.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("MachiCoro V0.1!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}