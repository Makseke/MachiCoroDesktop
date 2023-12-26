package org.machicoro.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.machicoro.config.PlayerConfig;
import org.machicoro.config.WindowConfig;
import org.machicoro.entity.Player;
import org.machicoro.enumaration.PlayerType;
import org.machicoro.repository.CardRepository;
import org.machicoro.repository.PlayerRepository;
import org.machicoro.service.ClientService;
import org.machicoro.service.ServerService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static java.lang.System.exit;

public class ServerController {
    private ServerService serverService = ServerService.getInstance();
    private PlayerConfig playerConfig = PlayerConfig.getInstance();
    private WindowConfig windowConfig = WindowConfig.getInstance();
    private PlayerRepository playerRepository = PlayerRepository.getInstance();
    private CardRepository cardRepository = CardRepository.getInstance();
    private ClientService clientService = ClientService.getInstance();


    @FXML
    private VBox vBox;

    @FXML
    private Label ip = new Label(showIP());

    @FXML
    private TextField nameField;

    @FXML
    private TextField ipField;

    @FXML
    private Label menuLabel;

    @FXML
    private HBox hBoxIp;

    @FXML
    private Button launchButton;

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
    protected void launchServer() throws IOException {
        playerRepository.getPlayers().add(
                new Player(
                        0,
                        nameField.getText(),
                        PlayerType.HOST,
                        3,
                        List.of(
                                cardRepository.addEstablishmentCard(1),
                                cardRepository.addEstablishmentCard(2)),
                        List.of(
                                cardRepository.addLandscapeCard(1),
                                cardRepository.addLandscapeCard(2),
                                cardRepository.addLandscapeCard(3),
                                cardRepository.addLandscapeCard(4)
                        )
                )
        );
        playerConfig.setName(nameField.getText());
        windowConfig.updateWindow("server-menu.fxml");
        serverService.startServer();
    }

    @FXML
    protected void tryToConnect() throws IOException {
        playerConfig.setName(nameField.getText());
        clientService.startClient(nameField.getText(), ipField.getText());
        playerConfig.setName(nameField.getText());
        windowConfig.updateWindow("server-menu.fxml");
    }

    @FXML
    protected void onExitButtonClick() {
        exit(0);
    }

    @FXML
    public void initialize() {
        if (playerConfig.getPlayerType().equals(PlayerType.HOST)) {
            ip.setText("Server IP: " + showIP());
            menuLabel.setText("Server menu");
            vBox.getChildren().remove(hBoxIp);
            launchButton.setOnAction(action ->{
                try {
                    launchServer();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        else {
            menuLabel.setText("Client menu");
            vBox.getChildren().removeAll(ip);
            launchButton.setOnAction(action ->{
                try {
                    tryToConnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
