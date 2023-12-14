package org.example.service;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.App;
import org.example.to.domain.ClientTO;
import org.example.to.domain.TextTO;
import org.example.util.IsUniqueClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

@AllArgsConstructor
@Getter
@Setter
public class ServerService {
    public static void startServer() {
        Server server = new Server();
        server.getKryo().register(TextTO.class);
        server.getKryo().register(ClientTO.class);

        Scanner scanner = new Scanner(System.in);

        System.out.println("SELECT PUBLIC NAME");
        String clientName = scanner.nextLine();

        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        ClientTO clientTO = new ClientTO(localhost.getHostAddress(), 0, clientName);
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
                    System.out.println(text.getText());
                } else if (object instanceof ClientTO clientTO) {
                    clientTO.setConnectionId(connection.getID());

                    if (!IsUniqueClient.isUnique(clientTO)) {
                        List<ClientTO> clients = App.clientsRepository.getClients();
                        clients.add(clientTO);
                        App.clientsRepository.setClients(clients);
                    } else {
                        connection.sendTCP(new TextTO("THIS USER ALREADY EXIST"));
                        connection.close();
                        exit(0);
                    }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
