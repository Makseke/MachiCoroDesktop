package org.machicoro.util;

import org.machicoro.ConsoleApp;
import org.machicoro.to.domain.server.ClientTO;

public class IsUniqueClient {
    public static boolean isUnique(ClientTO client){
        return ConsoleApp.clientsRepository.getClients()
                .stream()
                .anyMatch(client_ -> client_.getName().equals(client.getName()));
    }
}
