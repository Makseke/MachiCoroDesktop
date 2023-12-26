package org.machicoro.service;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import lombok.*;
import org.machicoro.config.WindowConfig;
import org.machicoro.controller.LobbyController;
import org.machicoro.entity.Player;
import org.machicoro.enumaration.PlayerType;
import org.machicoro.util.LoggerConfig;
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

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServerService {
    private ClientsRepository clientsRepository = ClientsRepository.getInstance();
    private PlayerRepository playerRepository = PlayerRepository.getInstance();
    private WindowConfig windowConfig = WindowConfig.getInstance();
    private CardRepository cardRepository = CardRepository.getInstance();

    private static ServerService instance;

    public static ServerService getInstance() {
        if (instance == null) {
            instance = new ServerService();
        }
        return instance;
    }

    private Server server = new Server();


    private Gson gson = new Gson();

    public void startServer() {
        server.getKryo().register(TextTO.class);
        server.getKryo().register(ClientTO.class);
        server.getKryo().register(ClientPublicInfoTO.class);
        server.getKryo().register(ClientsListTO.class);
        server.getKryo().register(StartGameRequestTO.class);
        server.getKryo().register(UpdatePlayersRequestTO.class);

        server.addListener(new Listener() {

            @Override
            public void connected(Connection connection) {
                if (clientsRepository.getClients().size() > 4) {
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
                    if (!IsUniqueClient.isUnique(clientTO)) {
                        playerRepository.getPlayers().add(
                                new Player(
                                        connection.getID(),
                                        clientTO.getName(),
                                        PlayerType.PLAYER,
                                        3,
                                        List.of(
                                                cardRepository.addEstablishmentCard(1),
                                                cardRepository.addEstablishmentCard(2)),
                                        List.of(
                                                cardRepository.addLandscapeCard(1),
                                                cardRepository.addLandscapeCard(2),
                                                cardRepository.addLandscapeCard(3),
                                                cardRepository.addLandscapeCard(4)
                                        )
                                )
                        );

                        LoggerConfig.getLogger().info("ADDED NEW PLAYER TO LOBBY " + clientTO.getName() + " WITH IP " + connection.getRemoteAddressTCP());
                        updateClientsPlayersLists();
                        updateLobbyList();
                    } else {
                        connection.sendTCP(new TextTO("THIS USER ALREADY EXIST"));
                        connection.close();
                    }
                } else if (object instanceof ClientsListTO) {
                    LoggerConfig.getLogger().info("SEND LOBBY LIST TO " + connection.getRemoteAddressTCP());
                    server.sendToAllTCP(new ClientsListTO(gson.toJson(clientsRepository.getClientPublicInfo())));
                    updateClientsPlayersLists();

                } else if (object instanceof ClientPublicInfoTO) {
                    LoggerConfig.getLogger().info("SEND CLIENT NAME TO " + connection.getRemoteAddressTCP());
                    updateClientsPlayersLists();
                    updateLobbyList();
                    connection.sendTCP(
                            ClientPublicInfoMapper.toTransferObject(clientsRepository.getClientToByConnectionId(connection.getID()))
                    );
                }
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                playerRepository.removePlayerByConnectionId(connection.getID());
                updateClientsPlayersLists();
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

    public void updateClientsPlayersLists() {
        String playersJson = gson.toJson(
                playerRepository.getPlayers()
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

    private void updateLobbyList(){
        FXMLLoader loader = windowConfig.getLoader();
        if (loader.getController() instanceof LobbyController controller){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    controller.updateLobby();
                }
            });
        }
        server.sendToAllTCP(new ClientsListTO(gson.toJson(clientsRepository.getClientPublicInfo())));
    }

    public void startGame(){
        server.sendToAllTCP(new StartGameRequestTO());
    }
}
