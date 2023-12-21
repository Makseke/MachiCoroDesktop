package org.machicoro.controller.window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.machicoro.WindowApp;
import org.machicoro.config.WindowConfig;
import org.machicoro.repository.ClientsRepository;
import org.machicoro.service.ServerService;
import org.machicoro.to.domain.server.ClientTO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static java.lang.System.exit;

public class WindowServerController {
    @FXML
    private Label ip = new Label(showIP());

    @FXML
    private TextField textField;

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
        ClientTO clientTO = new ClientTO(showIP(), 0, textField.getText(), "[HOST]  ");
        List<ClientTO> clients = ClientsRepository.getClients();
        clients.add(clientTO);
        ClientsRepository.setClients(clients);
        System.out.println(clients);

        FXMLLoader fxmlLoader = new FXMLLoader(WindowApp.class.getResource("server-menu.fxml"));
        WindowConfig.setLoader(fxmlLoader);
        Scene scene = new Scene(fxmlLoader.load(), WindowConfig.getWindowWight(), WindowConfig.getWindowHeight());
        WindowApp.primaryStage.setTitle(WindowConfig.getWindowName());
        WindowApp.primaryStage.setScene(scene);
        WindowApp.primaryStage.show();

        ServerService.startServer();
    }

    @FXML
    protected void onExitButtonClick() {
        exit(0);
    }

    @FXML
    public void initialize() {
        ip.setText("Server IP: " + showIP());
    }
}
