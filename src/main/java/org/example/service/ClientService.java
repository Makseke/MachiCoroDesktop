package org.example.service;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.to.domain.ClientTO;
import org.example.to.domain.TextTO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

@AllArgsConstructor
@Getter
@Setter
public class ClientService {
    public static void startClient() {
        Client client = new Client();
        client.getKryo().register(TextTO.class);
        client.getKryo().register(ClientTO.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("SELECT IP ADDRESS");
        String ip = scanner.nextLine();

        System.out.println("SELECT PUBLIC NAME");
        String clientName = scanner.nextLine();

        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        ClientTO clientTO = new ClientTO(localhost.getHostAddress(), 0, clientName);

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof TextTO response) {
                    System.out.println("SERVER RESPONSE: " + response.getText());
                } else {

                }
            }

            @Override
            public void connected(com.esotericsoftware.kryonet.Connection connection) {
                connection.setTimeout(0);
                connection.setKeepAliveTCP(5000);
                connection.sendTCP(clientTO);
            }
        });

        try {
            client.start();
            client.connect(5000, ip, 54555, 54777);
            TextTO request = new TextTO();
            request.setText("CONNECTED AS " + clientTO.getName() + " WITH IP " + clientTO.getIp());
            client.sendTCP(request);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            while (true) {

            }
        }
    }
}
