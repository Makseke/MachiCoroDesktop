package org.example.util;

import org.example.App;
import org.example.to.domain.server.ClientTO;

public class IsUniqueClient {
    public static boolean isUnique(ClientTO client){
        return App.clientsRepository.getClients()
                .stream()
                .anyMatch(client_ -> client_.getName().equals(client.getName()));
    }
}
