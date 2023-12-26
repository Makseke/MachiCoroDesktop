package org.machicoro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.machicoro.config.WindowConfig;
import org.machicoro.util.LoggerConfig;

import java.io.IOException;

public class WindowApp extends Application {
//    public static Stage primaryStage;

    private WindowConfig windowConfig = WindowConfig.getInstance();


    @Override
    public void start(Stage stage) throws IOException {
        windowConfig.setPrimaryStage(stage);
        windowConfig.updateWindow("launch-menu.fxml");
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