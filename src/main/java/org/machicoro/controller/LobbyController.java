package org.machicoro.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.machicoro.config.PlayerConfig;
import org.machicoro.config.WindowConfig;
import org.machicoro.enumaration.PlayerType;
import org.machicoro.repository.PlayerRepository;
import org.machicoro.service.ServerService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.lang.System.exit;

public class LobbyController {
    private ServerService serverService = ServerService.getInstance();
    private PlayerRepository playerRepository = PlayerRepository.getInstance();
    private WindowConfig windowConfig = WindowConfig.getInstance();
    private PlayerConfig playerConfig = PlayerConfig.getInstance();

    @FXML
    private Label ip = new Label(showIP());

    @FXML
    private VBox vbox;

    @FXML
    private VBox vboxWithButton;

    @FXML
    private Button startButton;

    protected String showIP() {
        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return localhost.getHostAddress();
    }

    @FXML
    protected void startGame() throws IOException {
        if (playerConfig.getPlayerType().equals(PlayerType.HOST)){
            serverService.updateClientsPlayersLists();
            serverService.startGame();
        }
        windowConfig.updateWindow("table-menu.fxml");
    }

    @FXML
    protected void onExitButtonClick() {
        exit(0);
    }

    @FXML
    public void initialize() {
        updateLobby();
        if (playerConfig.getPlayerType().equals(PlayerType.HOST)){
            startButton.setText("Start game");
            ip.setText("Server IP: " + showIP());
            startButton.setOnAction(onAction -> {
                try {
                    startGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        else{
            vboxWithButton.getChildren().remove(startButton);
            vboxWithButton.getChildren().remove(ip);
        }
    }

    @FXML
    public void addClientToLobby(PlayerType clientType, String clientName) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);

        Label clientTypeLabel = new Label(clientType.toString());
        Label clientNameLabel = new Label(clientName);

        hbox.getChildren().addAll(clientTypeLabel, clientNameLabel);
        vbox.getChildren().add(hbox);
    }

    public Runnable updateLobby(){
        vbox.getChildren().clear();
        playerRepository.getPlayers()
                .stream()
                .forEach(player -> addClientToLobby(player.getPlayerType(), player.getName()));
        return null;
    }
}
