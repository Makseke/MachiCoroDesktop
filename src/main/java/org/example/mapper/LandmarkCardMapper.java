package org.example.mapper;

import org.example.entity.LandmarkCard;
import org.example.to.domain.game.LandmarkCardTO;

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
