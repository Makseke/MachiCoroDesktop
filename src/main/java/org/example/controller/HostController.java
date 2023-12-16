package org.example.controller;

import com.esotericsoftware.kryonet.Server;
import org.example.App;
import org.example.service.ServerService;

import java.util.Scanner;

import static java.lang.System.exit;

public class HostController {
    private static final Scanner in = new Scanner(System.in);
    private static int operationType;

    private static Server server = ServerService.getServer();

    public static void hostController(){
        while (true){
            System.out.println("SELECT OPTION " +
                    "\n 1 - SHOW PLAYERS LIST " +
                    "\n 2 - SHOW NAME " +
                    "\n 3 - EXIT");
            operationType = in.nextInt();
            switch (operationType) {
                case 1 -> {
                    ServerService.showPlayersList();
                    continue;
                }
                case 2 -> {
                    ServerService.showHostName();
                    continue;
                }
                case 3 -> exit(0);
                default -> App.logger.info("INVALID INPUT");
            }
        }
    }
}
