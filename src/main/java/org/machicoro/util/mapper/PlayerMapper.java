package org.machicoro.util.mapper;

import org.machicoro.entity.Player;
import org.machicoro.to.domain.game.PlayerTO;

public class PlayerMapper {
    public static PlayerTO toObject(Player player){
        return new PlayerTO(
                player.getName(),
                player.getPlayerType(),
                player.getCoins(),
                player.getEstablishmentCards().stream().map(EstablishmentCardMapper::toTransferobject).toList(),
                player.getLandmarkCards().stream().map(LandmarkCardMapper::toTransferObject).toList()
        );
    }

    public static Player toObject(PlayerTO player){
        return new Player(
                -1,
                player.getName(),
                player.getPlayerType(),
                player.getCoins(),
                player.getEstablishmentCardsTO().stream().map(EstablishmentCardMapper::toObject).toList(),
                player.getLandmarkCardsTO().stream().map(LandmarkCardMapper::toObject).toList()
        );
    }
}
