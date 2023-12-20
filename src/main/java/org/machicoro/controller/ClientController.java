package org.machicoro.controller;

import com.esotericsoftware.kryonet.Client;
import lombok.Setter;
import org.machicoro.ConsoleApp;
import org.machicoro.service.ClientService;
import org.machicoro.to.domain.server.ClientPublicInfoTO;
import org.machicoro.to.domain.server.ClientsListTO;

import java.util.Scanner;

import static java.lang.System.exit;

public class ClientController {
    private static final Scanner in = new Scanner(System.in);

    @Setter
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
                    ConsoleApp.logger.info("PLAYERS LIST ");
                    client.sendTCP(new ClientsListTO());
                }
                case 2 -> {
                    ConsoleApp.logger.info("CLIENT NAME");
                    client.sendTCP(new ClientPublicInfoTO());
                }
                case 3 -> exit(0);
                default -> ConsoleApp.logger.info("INVALID INPUT");
            }
    }
}
