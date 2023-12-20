package org.machicoro.controller;

import com.esotericsoftware.kryonet.Client;
import org.machicoro.ConsoleApp;
import org.machicoro.service.ClientService;
import org.machicoro.service.PlayerService;

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
                System.out.println(" ");
            }
            case 2 -> {
                System.out.println(" ");
            }
            case 3 -> {
                ConsoleApp.logger.info("ALL PLAYERS INFO");
                PlayerService.getPlayersInfo();
            }
            case 4 -> exit(0);
            default -> ConsoleApp.logger.info("INVALID INPUT");
        }
    }
}
