package org.machicoro.controller;

import javafx.fxml.FXML;
import org.machicoro.config.PlayerConfig;
import org.machicoro.config.WindowConfig;
import org.machicoro.enumaration.PlayerType;

import java.io.IOException;

import static java.lang.System.exit;

public class LaunchController {
    private WindowConfig windowConfig = WindowConfig.getInstance();
    private PlayerConfig playerConfig = PlayerConfig.getInstance();

    @FXML
    protected void onServerButtonClick() throws IOException {
        playerConfig.setPlayerType(PlayerType.HOST);
        windowConfig.updateWindow("server-launch.fxml");
    }

    @FXML
    protected void onClientButtonClick() throws IOException {
        playerConfig.setPlayerType(PlayerType.PLAYER);
        windowConfig.updateWindow("server-launch.fxml");
    }

    @FXML
    protected void onExitButtonClick() {
        exit(0);
    }
}