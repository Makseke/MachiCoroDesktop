package org.machicoro.service;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import lombok.*;
import org.machicoro.config.WindowConfig;
import org.machicoro.controller.LobbyController;
import org.machicoro.repository.ClientsRepository;
import org.machicoro.util.LoggerConfig;
import org.machicoro.util.mapper.PlayerMapper;
import org.machicoro.repository.PlayerRepository;
import org.machicoro.to.domain.game.PlayerTO;
import org.machicoro.to.domain.server.ClientPublicInfoTO;
import org.machicoro.to.domain.server.ClientsListTO;
import org.machicoro.to.request.StartGameRequestTO;
import org.machicoro.to.request.UpdatePlayersRequestTO;
import org.machicoro.to.domain.server.ClientTO;
import org.machicoro.to.domain.server.TextTO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientService {

    public static ClientService instance;

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
    }

    private Client client = new Client();

    private ClientsRepository clientsRepository = ClientsRepository.getInstance();
    private PlayerRepository playerRepository = PlayerRepository.getInstance();
    private WindowConfig windowConfig = WindowConfig.getInstance();

    private ClientTO clientTO;

    private Gson gson = new Gson();

    private String jsonParts = "";


    public void startClient(String clientName, String ip) throws IOException {
        client.getKryo().register(TextTO.class);
        client.getKryo().register(ClientTO.class);
        client.getKryo().register(ClientPublicInfoTO.class);
        client.getKryo().register(ClientsListTO.class);
        client.getKryo().register(StartGameRequestTO.class);
        client.getKryo().register(UpdatePlayersRequestTO.class);

        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        ClientTO clientTO = new ClientTO(localhost.getHostAddress(), 0, clientName, "[PLAYER]");


        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof TextTO response) {
                    LoggerConfig.getLogger().info("SERVER RESPONSE: " + response.getText());
                } else if (object instanceof ClientsListTO response) {
                    LoggerConfig.getLogger().info("UPDATED LOBBY LIST");
                    Type listType = new TypeToken<List<ClientPublicInfoTO>>() {
                    }.getType();
                    clientsRepository.setClientsPublicInfoTO(gson.fromJson(response.getJsonClients(), listType));
                    System.out.println("UPDATED CLIENT REPOSITORY");
                    updateLobbyList();
                } else if (object instanceof ClientPublicInfoTO response) {
                    LoggerConfig.getLogger().info("DISPLAY NAME");
                    System.out.println("YOUR NAME IS " + response.getName());
                } else if (object instanceof StartGameRequestTO) {
                    LoggerConfig.getLogger().info("GAME STARTED");
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                windowConfig.updateWindow("table-menu.fxml");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                } else if (object instanceof UpdatePlayersRequestTO request) {
                    Type listType = new TypeToken<List<PlayerTO>>() {
                    }.getType();

                    jsonParts += request.getPlayersJson();
                    try {
                        List<PlayerTO> playersListTO = gson.fromJson(jsonParts, listType);
                        playerRepository.setPlayers(playersListTO.stream().map(PlayerMapper::toObject).toList());
                        updateLobbyList();
                        System.out.println("BUILD COMPLETED");
                        System.out.println(playerRepository.getPlayers().size());
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void connected(Connection connection) {
                connection.setTimeout(0);
                connection.setKeepAliveTCP(5000);
                connection.sendTCP(clientTO);
            }
        });

        client.start();
        client.connect(5000, ip, 54555, 54777);
        LoggerConfig.getLogger().info("CONNECTED AS " + clientTO.getName() + " WITH IP " + clientTO.getIp());
    }

    private void updateLobbyList() {
        FXMLLoader loader = windowConfig.getLoader();
        if (loader.getController() instanceof LobbyController controller) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    controller.updateLobby();
                }
            });
        }
        jsonParts = "";
    }
}
