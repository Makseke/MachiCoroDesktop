package org.machicoro;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    protected void getName() {
        System.out.println(nameField.getText());
    }

    @FXML
    protected void getIp() {
        System.out.println(ipField.getText());
    }

    @FXML
    protected void onExitButtonClick() {
        exit(0);
    }

    @FXML
    public void initialize() {
        clientText.setText("Client menu");
    }
}
