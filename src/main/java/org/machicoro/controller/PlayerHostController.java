package org.machicoro.controller;

import com.esotericsoftware.kryonet.Server;
import org.machicoro.service.PlayerService;
import org.machicoro.service.ServerService;
import org.machicoro.util.LoggerConfig;

import java.util.Scanner;

import static java.lang.System.exit;

public class PlayerHostController {
    private static final Scanner in = new Scanner(System.in);
    private static int operationType;

    private static Server server = ServerService.getServer();

    public static void playerHostController(){
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
                LoggerConfig.getLogger().info("ALL PLAYERS INFO");
                PlayerService.getPlayersInfo();
            }
            case 4 -> exit(0);
            default -> LoggerConfig.getLogger().info("INVALID INPUT");
        }
    }
}
