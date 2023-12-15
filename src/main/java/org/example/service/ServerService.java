package org.example.service;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.App;
import org.example.mapper.ClientPublicInfoMapper;
import org.example.repository.ClientsRepository;
import org.example.to.domain.ClientPublicInfoTO;
import org.example.to.domain.ClientTO;
import org.example.to.domain.ClientsListTO;
import org.example.to.domain.TextTO;
import org.example.util.IsUniqueClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

@AllArgsConstructor
@Getter
@Setter
public class ServerService {
    private static Scanner in = new Scanner(System.in);
    private static int  operationType;
    private static Server server;

    private static Gson gson = new Gson();

    public static void startServer() {
        server = new Server();

        server.getKryo().register(List.class);
        server.getKryo().register(ArrayList.class);

        server.getKryo().register(TextTO.class);
        server.getKryo().register(ClientTO.class);
        server.getKryo().register(ClientPublicInfoTO.class);
        server.getKryo().register(ClientsListTO.class);

        Scanner scanner = new Scanner(System.in);

        System.out.println("SELECT PUBLIC NAME");
        String clientName = scanner.nextLine();

        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        ClientTO clientTO = new ClientTO(localhost.getHostAddress(), 0, clientName, "HOST   ");
        clientTO.setConnectionId(0);
        List<ClientTO> clients = App.clientsRepository.getClients();
        clients.add(clientTO);
        App.clientsRepository.setClients(clients);


        server.addListener(new Listener() {

            @Override
            public void connected(Connection connection) {
                if (App.clientsRepository.getClients().size() > 5) {
                    TextTO welcomeMessage = new TextTO("TO MANY PLAYERS IN THIS ROOM");
                    connection.sendTCP(welcomeMessage);
                    connection.close();
                } else {
                    TextTO welcomeMessage = new TextTO("CONNECTION SUCCESS");
                    connection.sendTCP(welcomeMessage);
                    connection.setTimeout(0);
                    connection.setKeepAliveTCP(5000);
                }
            }

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof TextTO text) {
                    App.logger.info("MESSAGE " + text.getText() + " FROM " + connection.getRemoteAddressTCP());
                } else if (object instanceof ClientTO clientTO) {
                    App.logger.info("ADDED NEW PLAYER TO LOBBY " + clientTO.getName() + " WITH IP " + connection.getRemoteAddressTCP());
                    clientTO.setConnectionId(connection.getID());

                    if (!IsUniqueClient.isUnique(clientTO)) {
                        List<ClientTO> clients = App.clientsRepository.getClients();
                        clients.add(clientTO);
                        App.clientsRepository.setClients(clients);
                    } else {
                        connection.sendTCP(new TextTO("THIS USER ALREADY EXIST"));
                        connection.close();
                    }
                }
                else if (object instanceof ClientsListTO) {
                    App.logger.info("SEND LOBBY LIST TO " + connection.getRemoteAddressTCP());
                    connection.sendTCP(
                            new ClientsListTO(gson.toJson(ClientsRepository.getClientPublicInfo()))
                    );
                }
                else if (object instanceof ClientPublicInfoTO) {
                    App.logger.info("SEND CLIENT NAME TO " + connection.getRemoteAddressTCP());
                    connection.sendTCP(
                            ClientPublicInfoMapper.toTransferObject(ClientsRepository.getClientToByConnectionId(connection.getID()))
                    );
                }
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                List<ClientTO> clients = App.clientsRepository.getClients();
                for (ClientTO client : clients) {
                    if (client.getConnectionId() == connection.getID()) {
                        clients.remove(client);
                        break;
                    }
                }
                App.clientsRepository.setClients(clients);
            }
        });

        try {
            server.start();
            server.bind(54555, 54777);
            serverController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serverController(){
        while (true){
            System.out.println("SELECT OPTION " +
                    "\n 1 - SHOW PLAYERS LIST " +
                    "\n 2 - SHOW NAME " +
                    "\n 3 - EXIT");
            operationType = in.nextInt();
            switch (operationType){
                case 1:
                    showPlayersList();
                    continue;
                case 2:
                    showHostName();
                    continue;
                case 3:
                    exit(0);
                default:
                    App.logger.info("INVALID INPUT");
            }
        }
    }

    private static void showPlayersList(){
        App.logger.info("LOBBY LIST");
        List<ClientPublicInfoTO> clients = ClientsRepository.getClientPublicInfo();
        for (ClientPublicInfoTO clientInfo : clients){
            System.out.println(clientInfo.getClientType() + " " + clientInfo.getName());
        }
    }

    private static void showHostName(){
        App.logger.info("DISPLAY NAME");
        System.out.println("YOUR NAME IS " + ClientPublicInfoMapper.toTransferObject(ClientsRepository.getClientToByConnectionId(0)).getName());

    }
}
