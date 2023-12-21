package org.machicoro.controller.window;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.machicoro.repository.ClientsRepository;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.lang.System.exit;

public class WindowClientLobbyController {
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

    @FXML
    public Runnable updateLobby(){
        vbox.getChildren().clear();
        ClientsRepository.getClientsPublicInfoTO()
                .stream()
                .forEach(clientTO -> addClientToLobby(clientTO.getClientType(), clientTO.getName()));
        return null;
    }
}
