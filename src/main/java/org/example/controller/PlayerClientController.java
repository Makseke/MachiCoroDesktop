package org.example.controller;

import com.esotericsoftware.kryonet.Client;
import org.example.App;
import org.example.service.ClientService;
import org.example.service.PlayerService;
import org.example.service.ServerService;
import org.example.to.domain.server.ClientPublicInfoTO;
import org.example.to.domain.server.ClientsListTO;

import java.util.Scanner;

import static java.lang.System.exit;

public class PlayerClientController {
    private static final Scanner in = new Scanner(System.in);
    private static int operationType;

    private static Client client = ClientService.getClient();

    public static void playerClientController(){
        System.out.println("SELECT OPTION " +
                "\n 1 - MY INFO " +
                "\n 2 - PLAYERS INFO " +
                "\n 3 - ALL PLAYERS INFO " +
                "\n 4 - EXIT");
        operationType = in.nextInt();
        switch (operationType) {
            case 1 -> {

            }
            case 2 -> {

            }
            case 3 -> {
                App.logger.info("ALL PLAYERS INFO");
                PlayerService.getPlayersInfo();
            }
            case 3 -> exit(0);
            default -> App.logger.info("INVALID INPUT");
        }
    }
}
