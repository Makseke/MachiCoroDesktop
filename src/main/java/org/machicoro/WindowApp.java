package org.machicoro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.machicoro.config.WindowConfig;
import org.machicoro.util.LoggerConfig;

import java.io.IOException;

public class WindowApp extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(WindowApp.class.getResource("launch-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WindowConfig.getWindowWight(), WindowConfig.getWindowHeight());
        WindowApp.primaryStage.setTitle(WindowConfig.getWindowName());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("" +
                "███╗   ███╗ █████╗  ██████╗██╗  ██╗██╗     ██████╗ ██████╗ ██████╗  ██████╗ \n" +
                "████╗ ████║██╔══██╗██╔════╝██║  ██║██║    ██╔════╝██╔═══██╗██╔══██╗██╔═══██╗\n" +
                "██╔████╔██║███████║██║     ███████║██║    ██║     ██║   ██║██████╔╝██║   ██║\n" +
                "██║╚██╔╝██║██╔══██║██║     ██╔══██║██║    ██║     ██║   ██║██╔══██╗██║   ██║\n" +
                "██║ ╚═╝ ██║██║  ██║╚██████╗██║  ██║██║    ╚██████╗╚██████╔╝██║  ██║╚██████╔╝\n" +
                "╚═╝     ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═╝     ╚═════╝ ╚═════╝ ╚═╝  ╚═╝ ╚═════╝ \n" +
                "                                                                            ");
        LoggerConfig.getLogger().info("VERSION 0.1 HAS STARTED");
        launch();
    }
}