package org.machicoro;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.machicoro.service.ServerService;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
    protected void launchServer() {
        ServerService.startServer();
        System.out.println(textField.getText());
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
