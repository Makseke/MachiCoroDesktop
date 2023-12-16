package org.example.controller;

import org.example.App;
import org.example.service.ClientService;
import org.example.service.ServerService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import static java.lang.System.exit;

public class LaunchController {
    private static final Scanner in = new Scanner(System.in);
    int operationType;



    public void launchController(){
        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        while (true){
            System.out.println("SELECT START MODE \n 1 - SERVER \n 2 - CLIENT \n 3 - EXIT");
            operationType = in.nextInt();
            switch (operationType) {
                case 1 -> {
                    App.logger.info("SERVER START ON IP " + localhost.getHostAddress());
                    ServerService.startServer();
                }
                case 2 -> {
                    App.logger.info("YOUR IP IS " + localhost.getHostAddress());
                    ClientService.startClient();
                }
                case 3 -> exit(0);
                default -> App.logger.info("INVALID INPUT");
            }
        }
    }
}
