package org.example.controller;

import com.esotericsoftware.kryonet.Client;
import org.example.App;
import org.example.service.ClientService;
import org.example.to.domain.server.ClientPublicInfoTO;
import org.example.to.domain.server.ClientsListTO;

import java.util.Scanner;

import static java.lang.System.exit;

public class ClientController {
    private static final Scanner in = new Scanner(System.in);
    private static int operationType;

    private static Client client = ClientService.getClient();

    public static void clientController() {
            System.out.println("SELECT OPTION " +
                    "\n 1 - SHOW PLAYERS LIST " +
                    "\n 2 - SHOW NAME " +
                    "\n 3 - EXIT");
            operationType = in.nextInt();
            switch (operationType) {
                case 1 -> {
                    App.logger.info("PLAYERS LIST ");
                    client.sendTCP(new ClientsListTO());
                }
                case 2 -> {
                    App.logger.info("CLIENT NAME");
                    client.sendTCP(new ClientPublicInfoTO());
                }
                case 3 -> exit(0);
                default -> App.logger.info("INVALID INPUT");
            }
    }
}
