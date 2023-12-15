package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.controller.Controller;
import org.example.repository.ClientsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@Getter
@Setter
public class App {
    public static ClientsRepository clientsRepository = new ClientsRepository();

    public static final Logger logger = LoggerFactory.getLogger("MACHICORO");

    public static void main(String[] args) {
        logger.info("VERSION 0.1 HAS STARTED");
        Controller controller = new Controller();
        controller.launchController();
    }


}
