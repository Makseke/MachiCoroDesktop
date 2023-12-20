package org.machicoro.util.mapper;

import org.machicoro.entity.EstablishmentCard;
import org.machicoro.to.domain.game.EstablishmentCardTO;

public class EstablishmentCardMapper {
    public static EstablishmentCardTO toTransferobject(EstablishmentCard card){
        return new EstablishmentCardTO(
                card.getId(),
                card.getTitle(),
                card.getDescription(),
                card.getCost(),
                card.getType(),
                card.getPassive(),
                card.getCount(),
                card.getCondition()
        );
    }

    public static EstablishmentCard toObject(EstablishmentCardTO card){
        return new EstablishmentCard(
                card.getId(),
                card.getTitle(),
                card.getDescription(),
                card.getCost(),
                card.getType(),
                card.getPassive(),
                card.getCount(),
                card.getCondition()
        );
    }
}
