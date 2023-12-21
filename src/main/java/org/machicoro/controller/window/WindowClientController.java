package org.machicoro.controller.window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.machicoro.WindowApp;
import org.machicoro.config.WindowConfig;
import org.machicoro.service.ClientService;
import org.machicoro.to.domain.server.ClientTO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.lang.System.exit;

public class WindowClientController {
    @FXML
    private Label clientText;

    @FXML
    private TextField ipField;
    @FXML
    private TextField nameField;

    @FXML
    protected void getName() {
        System.out.println(nameField.getText());
    }

    @FXML
    protected void tryToConnect() throws IOException {
        ClientService.startClient(nameField.getText(), ipField.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(WindowApp.class.getResource("client-menu.fxml"));
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
        clientText.setText("Client menu");
    }

    protected String showIP() {
        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return localhost.getHostAddress();
    }
}
