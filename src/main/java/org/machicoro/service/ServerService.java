package org.machicoro.service;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.machicoro.config.WindowConfig;
import org.machicoro.controller.window.WindowServerLobbyController;
import org.machicoro.entity.Player;
import org.machicoro.util.LoggerConfig;
import org.machicoro.util.mapper.ClientMapper;
import org.machicoro.util.mapper.ClientPublicInfoMapper;
import org.machicoro.util.mapper.PlayerMapper;
import org.machicoro.repository.CardRepository;
import org.machicoro.repository.ClientsRepository;
import org.machicoro.repository.PlayerRepository;
import org.machicoro.to.domain.server.ClientPublicInfoTO;
import org.machicoro.to.domain.server.ClientsListTO;
import org.machicoro.to.request.StartGameRequestTO;
import org.machicoro.to.request.UpdatePlayersRequestTO;
import org.machicoro.util.IsUniqueClient;
import org.machicoro.to.domain.server.ClientTO;
import org.machicoro.to.domain.server.TextTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@Getter
@Setter
public class ServerService {
    private static final Scanner in = new Scanner(System.in);
    private static int operationType;

    @Getter
    private static Server server = new Server();

    private static Gson gson = new Gson();

    public static void startServer() {
        server.getKryo().register(TextTO.class);
        server.getKryo().register(ClientTO.class);
        server.getKryo().register(ClientPublicInfoTO.class);
        server.getKryo().register(ClientsListTO.class);
        server.getKryo().register(StartGameRequestTO.class);
        server.getKryo().register(UpdatePlayersRequestTO.class);

        server.addListener(new Listener() {

            @Override
            public void connected(Connection connection) {
                if (ClientsRepository.getClients().size() > 4) {
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
                    LoggerConfig.getLogger().info("MESSAGE " + text.getText() + " FROM " + connection.getRemoteAddressTCP());
                } else if (object instanceof ClientTO clientTO) {
                    LoggerConfig.getLogger().info("ADDED NEW PLAYER TO LOBBY " + clientTO.getName() + " WITH IP " + connection.getRemoteAddressTCP());
                    clientTO.setConnectionId(connection.getID());

                    if (!IsUniqueClient.isUnique(clientTO)) {
                        List<ClientTO> clients = ClientsRepository.getClients();
                        clients.add(clientTO);
                        ClientsRepository.setClients(clients);
                        updateLobbyList();
                    } else {
                        connection.sendTCP(new TextTO("THIS USER ALREADY EXIST"));
                        connection.close();
                    }
                } else if (object instanceof ClientsListTO) {
                    LoggerConfig.getLogger().info("SEND LOBBY LIST TO " + connection.getRemoteAddressTCP());
                    server.sendToAllTCP(new ClientsListTO(gson.toJson(ClientsRepository.getClientPublicInfo())));
                } else if (object instanceof ClientPublicInfoTO) {
                    LoggerConfig.getLogger().info("SEND CLIENT NAME TO " + connection.getRemoteAddressTCP());
                    connection.sendTCP(
                            ClientPublicInfoMapper.toTransferObject(ClientsRepository.getClientToByConnectionId(connection.getID()))
                    );
                }
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                List<ClientTO> clients = ClientsRepository.getClients();
                for (ClientTO client : clients) {
                    if (client.getConnectionId() == connection.getID()) {
                        clients.remove(client);
                        break;
                    }
                }
                ClientsRepository.setClients(clients);
                FXMLLoader loader = WindowConfig.getLoader();
                updateLobbyList();
            }
        });

        try {
            server.start();
            server.bind(54555, 54777);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerPlayers() {
        List<ClientTO> clients = ClientsRepository.getClients();
        List<Player> players = new ArrayList<>();
        for (ClientTO clientTO : clients) {
            players.add(new Player(ClientMapper.toObject(clientTO), 3, List.of(CardRepository.addEstablishmentCard(1), CardRepository.addEstablishmentCard(2)), List.of(CardRepository.addLandscapeCard(1), CardRepository.addLandscapeCard(2), CardRepository.addLandscapeCard(3), CardRepository.addLandscapeCard(4))));
        }
        PlayerRepository.setPlayers(players);
    }

    public static void updateClientsPlayersLists() {
        String playersJson = gson.toJson(
                PlayerRepository.getPlayers()
                        .stream()
                        .map(PlayerMapper::toObject)
                        .toList());

        int length = playersJson.length();

        List<String> result = new ArrayList<>();
        int j = 0;

        for (int i = 0; i < length; i += 255) {
            int end = Math.min(length, i + 255);
            result.add(playersJson.substring(i, end));
        }

        for (String jsonPart : result){
            server.sendToAllTCP(new UpdatePlayersRequestTO(jsonPart));
        }
    }

    private static void updateLobbyList(){
        FXMLLoader loader = WindowConfig.getLoader();
        if (loader.getController() instanceof WindowServerLobbyController controller){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    controller.updateLobby();
                }
            });
        }
        server.sendToAllTCP(new ClientsListTO(gson.toJson(ClientsRepository.getClientPublicInfo())));
    }
}
