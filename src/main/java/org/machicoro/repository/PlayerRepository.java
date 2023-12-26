package org.machicoro.repository;

import lombok.*;
import org.machicoro.entity.Player;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerRepository {
    private static PlayerRepository instance;

    public static PlayerRepository getInstance() {
        if (instance == null) {
            instance = new PlayerRepository();
        }
        return instance;
    }

    private List<Player> players = new ArrayList<>();

    public Player getPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public Player removePlayerByConnectionId(int id) {
        for (Player player : players) {
            if (player.getConnectionId() == id) {
                players.remove(player);
                break;
            }
        }
        return null;
    }
}
