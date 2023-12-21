package org.machicoro.service;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.machicoro.config.WindowConfig;
import org.machicoro.controller.window.WindowClientLobbyController;
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
import java.util.Scanner;

@AllArgsConstructor
@Data
public class ClientService {
    private static final Scanner in = new Scanner(System.in);
    private static int  operationType;

    @Getter
    @Setter
    private static Client client = new Client();

    @Setter
    @Getter
    private static ClientTO clientTO;

    private static Gson gson = new Gson();

    private static String jsonParts = "";


    public static void startClient(String clientName, String ip) throws IOException {
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
                }
                else if (object instanceof ClientsListTO response) {
                    LoggerConfig.getLogger().info("UPDATED LOBBY LIST");
                    Type listType = new TypeToken<List<ClientPublicInfoTO>>() {}.getType();
                    ClientsRepository.setClientsPublicInfoTO(gson.fromJson(response.getJsonClients(), listType));
                    System.out.println("UPDATED CLIENT REPOSITORY");
                    updateLobbyList();
                }
                else if (object instanceof ClientPublicInfoTO response) {
                    LoggerConfig.getLogger().info("DISPLAY NAME");
                    System.out.println("YOUR NAME IS " + response.getName());
                }
                else if (object instanceof StartGameRequestTO) {
                    LoggerConfig.getLogger().info("GAME STARTED");
                }
                else if (object instanceof UpdatePlayersRequestTO request) {
                    LoggerConfig.getLogger().info("PLAYERS INFO PART");
                    Type listType = new TypeToken<List<PlayerTO>>() {}.getType();

                    jsonParts += request.getPlayersJson();
                    try{
                        List<PlayerTO> playersListTO = gson.fromJson(jsonParts, listType);
                        PlayerRepository.setPlayers(playersListTO.stream().map(PlayerMapper ::toObject).toList());
                        System.out.println("BUILD COMPLETED");
                    }
                    catch (Exception e){

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

    private static void updateLobbyList(){
        FXMLLoader loader = WindowConfig.getLoader();
        if (loader.getController() instanceof WindowClientLobbyController controller){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    controller.updateLobby();
                }
            });
        }
    }
}
