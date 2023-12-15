package org.example.service;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.App;
import org.example.to.domain.ClientPublicInfoTO;
import org.example.to.domain.ClientTO;
import org.example.to.domain.ClientsListTO;
import org.example.to.domain.TextTO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

@AllArgsConstructor
@Getter
@Setter
public class ClientService {
    private static Scanner in = new Scanner(System.in);
    private static int  operationType;

    private static Client client;

    private static Gson gson = new Gson();

    public static void startClient() {
        client = new Client();

        client.getKryo().register(List.class);
        client.getKryo().register(ArrayList.class);

        client.getKryo().register(TextTO.class);
        client.getKryo().register(ClientTO.class);
        client.getKryo().register(ClientPublicInfoTO.class);
        client.getKryo().register(ClientsListTO.class);

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

        ClientTO clientTO = new ClientTO(localhost.getHostAddress(), 0, clientName, "PLAYER");

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof TextTO response) {
                    App.logger.info("SERVER RESPONSE: " + response.getText());
                }
                else if (object instanceof ClientsListTO response) {
                    App.logger.info("LOBBY LIST");
                    Type listType = new TypeToken<List<ClientPublicInfoTO>>() {}.getType();
                    List<ClientPublicInfoTO> clients = gson.fromJson(response.getJsonClients(), listType);
                    for (ClientPublicInfoTO clientInfo : clients){
                        System.out.println(clientInfo.getClientType() + " " + clientInfo.getName());
                    }
                }
                else if (object instanceof ClientPublicInfoTO response) {
                    App.logger.info("DISPLAY NAME");
                    System.out.println("YOUR NAME IS " + response.getName());
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
            App.logger.info("CONNECTED AS " + clientTO.getName() + " WITH IP " + clientTO.getIp());

            clientController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clientController(){
        while (true){
            System.out.println("SELECT OPTION " +
                    "\n 1 - SHOW PLAYERS LIST " +
                    "\n 2 - SHOW NAME " +
                    "\n 3 - EXIT");
            operationType = in.nextInt();
            switch (operationType){
                case 1:
                    App.logger.info("PLAYERS LIST ");
                    client.sendTCP(new ClientsListTO());
                    continue;
                case 2:
                    App.logger.info("CLIENT NAME");
                    client.sendTCP(new ClientPublicInfoTO());
                    continue;
                case 3:
                    exit(0);
                default:
                    App.logger.info("INVALID INPUT");
            }
        }
    }
}
