package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.controller.LaunchController;
import org.example.repository.ClientsRepository;

import org.example.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@Getter
@Setter
public class App {
    public static ClientsRepository clientsRepository = new ClientsRepository();

    public static boolean gameStatus = false;

    public static final Logger logger = LoggerFactory.getLogger("MACHICORO");

    public static void main(String[] args) {
        System.out.println("                               __                            ____                             \n" +
                            " /'\\_/`\\                      /\\ \\         __               /\\  _`\\                           \n" +
                            "/\\      \\      __       ___   \\ \\ \\___    /\\_\\              \\ \\ \\/\\_\\    ___    _ __    ___   \n" +
                            "\\ \\ \\__\\ \\   /'__`\\    /'___\\  \\ \\  _ `\\  \\/\\ \\              \\ \\ \\/_/_  / __`\\ /\\`'__\\ / __`\\ \n" +
                            " \\ \\ \\_/\\ \\ /\\ \\L\\.\\_ /\\ \\__/   \\ \\ \\ \\ \\  \\ \\ \\              \\ \\ \\L\\ \\/\\ \\L\\ \\\\ \\ \\/ /\\ \\L\\ \\\n" +
                            "  \\ \\_\\\\ \\_\\\\ \\__/.\\_\\\\ \\____\\   \\ \\_\\ \\_\\  \\ \\_\\              \\ \\____/\\ \\____/ \\ \\_\\ \\ \\____/\n" +
                            "   \\/_/ \\/_/ \\/__/\\/_/ \\/____/    \\/_/\\/_/   \\/_/               \\/___/  \\/___/   \\/_/  \\/___/ \n" +
                            "                                                                                              ");
        logger.info("VERSION 0.1 HAS STARTED");
        LaunchController launchController = new LaunchController();
        launchController.launchController();
    }


}
