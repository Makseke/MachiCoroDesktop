package org.machicoro.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.*;
import org.machicoro.WindowApp;

import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WindowConfig {
    public static WindowConfig instance;

    public static WindowConfig getInstance() {
        if (instance == null) {
            instance = new WindowConfig();
        }
        return instance;
    }

    private final String windowName = "MachiCoro V0.1";
    private int windowWight = 940;
    private int windowHeight = 380;

    private FXMLLoader loader;

    public Stage primaryStage;

    public void updateWindow(String windowName) throws IOException {
        loader = new FXMLLoader(WindowApp.class.getResource(windowName));
        Scene scene = new Scene(loader.load(), getWindowWight(), getWindowHeight());
        primaryStage.setTitle(getWindowName());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
