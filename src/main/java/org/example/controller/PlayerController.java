package org.example.controller;

import org.example.App;
import org.example.service.ServerService;

import java.util.Scanner;

import static java.lang.System.exit;

public class PlayerController {
    private static final Scanner in = new Scanner(System.in);
    private static int operationType;

    public static void playerController(){
            System.out.println("SELECT OPTION " +
                    "\n 1 - SHOW PLAYERS LIST " +
                    "\n 2 - SHOW NAME " +
                    "\n 3 - EXIT");
            operationType = in.nextInt();
            switch (operationType) {
                case 1 -> {
                    ServerService.showPlayersList();
                }
                case 2 -> {
                    ServerService.showHostName();
                }
                case 3 -> exit(0);
                default -> App.logger.info("INVALID INPUT");
            }
    }
}
