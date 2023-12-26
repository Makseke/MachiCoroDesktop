package org.machicoro.util;

import org.machicoro.repository.PlayerRepository;
import org.machicoro.to.domain.server.AddPlayerResponseTO;

public class IsUniqueClient {
    private static PlayerRepository playerRepository = PlayerRepository.getInstance();
    public static boolean isUnique(AddPlayerResponseTO client){
        return playerRepository.getPlayers()
                .stream()
                .anyMatch(player -> player.getName().equals(client.getName()));
    }
}
