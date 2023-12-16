package org.example.mapper;

import org.example.entity.Player;
import org.example.to.domain.game.PlayerTO;

public class PlayerMapper {
    public static PlayerTO toTransferObject(Player player){
        return new PlayerTO(
                ClientMapper.toTransferObject(player.getClient()),
                player.getCoins(),
                player.getEstablishmentCards().stream().map(EstablishmentCardMapper::toTransferobject).toList(),
                player.getLandmarkCards().stream().map(LandmarkCardMapper::toTransferobject).toList()
        );

    }
}
