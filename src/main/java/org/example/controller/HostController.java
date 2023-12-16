package org.example.controller;

import com.esotericsoftware.kryonet.Server;
import org.example.App;
import org.example.service.ServerService;
import org.example.to.domain.server.StartGameTO;

import java.util.Scanner;

import static java.lang.System.exit;

public class HostController {
    private static final Scanner in = new Scanner(System.in);
    private static int operationType;

    private static Server server = ServerService.getServer();

    public static void hostController(){
            System.out.println("SELECT OPTION " +
                    "\n 1 - START GAME " +
                    "\n 2 - SHOW PLAYERS LIST " +
                    "\n 3 - SHOW NAME " +
                    "\n 4 - EXIT");
            operationType = in.nextInt();
            switch (operationType) {
                case 1 -> {
                    App.logger.info("GAME STARTED");
                    App.gameStatus = true;
                    server.sendToAllTCP(new StartGameTO());
                }
                case 2 -> {
                    ServerService.showPlayersList();
                }
                case 3 -> {
                    ServerService.showHostName();
                }
                case 4 -> exit(0);
                default -> App.logger.info("INVALID INPUT");
        }
    }
}
