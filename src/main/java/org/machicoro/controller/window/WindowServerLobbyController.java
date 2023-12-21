package org.machicoro.controller.window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.machicoro.WindowApp;
import org.machicoro.config.WindowConfig;
import org.machicoro.repository.ClientsRepository;
import org.machicoro.service.ServerService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.lang.System.exit;

public class WindowServerLobbyController {
    @FXML
    private Label ip = new Label(showIP());

    @FXML
    private VBox vbox;

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
        ServerService.registerPlayers();

        FXMLLoader fxmlLoader = new FXMLLoader(WindowApp.class.getResource("table-menu.fxml"));
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

    @FXML
    public void initialize() {
        updateLobby();
        ip.setText("Server IP: " + showIP());
    }

    @FXML
    public void addClientToLobby(String clientType, String clientName) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);

        Label clientTypeLabel = new Label(clientType);
        Label clientNameLabel = new Label(clientName);

        hbox.getChildren().addAll(clientTypeLabel, clientNameLabel);
        vbox.getChildren().add(hbox);
    }

    public Runnable updateLobby(){
        vbox.getChildren().clear();
        ClientsRepository.getClients()
                .stream()
                .forEach(clientTO -> addClientToLobby(clientTO.getUserType(), clientTO.getName()));
        return null;
    }
}
