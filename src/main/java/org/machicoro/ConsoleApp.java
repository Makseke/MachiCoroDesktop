package org.machicoro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.machicoro.controller.LaunchController;
import org.machicoro.repository.ClientsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@Getter
@Setter
public class ConsoleApp {
    public static ClientsRepository clientsRepository = new ClientsRepository();

    public static boolean gameStatus = false;

    public static final Logger logger = LoggerFactory.getLogger("MACHICORO");

    public static void main(String[] args) {
        System.out.println("" +
                "███╗   ███╗ █████╗  ██████╗██╗  ██╗██╗     ██████╗ ██████╗ ██████╗  ██████╗ \n" +
                "████╗ ████║██╔══██╗██╔════╝██║  ██║██║    ██╔════╝██╔═══██╗██╔══██╗██╔═══██╗\n" +
                "██╔████╔██║███████║██║     ███████║██║    ██║     ██║   ██║██████╔╝██║   ██║\n" +
                "██║╚██╔╝██║██╔══██║██║     ██╔══██║██║    ██║     ██║   ██║██╔══██╗██║   ██║\n" +
                "██║ ╚═╝ ██║██║  ██║╚██████╗██║  ██║██║    ╚██████╗╚██████╔╝██║  ██║╚██████╔╝\n" +
                "╚═╝     ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═╝     ╚═════╝ ╚═════╝ ╚═╝  ╚═╝ ╚═════╝ \n" +
                "                                                                            ");
        logger.info("VERSION 0.1 HAS STARTED");
        LaunchController launchController = new LaunchController();
        launchController.launchController();
    }
}
