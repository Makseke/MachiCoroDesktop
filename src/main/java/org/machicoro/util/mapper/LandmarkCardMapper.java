package org.machicoro.util.mapper;

import org.machicoro.entity.LandmarkCard;
import org.machicoro.to.domain.game.LandmarkCardTO;

public class LandmarkCardMapper {
    public static LandmarkCardTO toTransferObject(LandmarkCard card){
        return new LandmarkCardTO(
                card.getId(),
                card.getTitle(),
                card.getDescription(),
                card.getCost(),
                card.getActive(),
                card.isStatus()
        );
    }

    public static LandmarkCard toObject(LandmarkCardTO card){
        return new LandmarkCard(
                card.getId(),
                card.getTitle(),
                card.getDescription(),
                card.getCost(),
                card.isStatus(),
                card.getActive()
                );
    }
}
