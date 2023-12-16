package org.example.mapper;

import org.example.entity.LandmarkCard;
import org.example.to.domain.game.LandmarkCardTO;

public class LandmarkCardMapper {
    public static LandmarkCardTO toTransferobject(LandmarkCard card){
        return new LandmarkCardTO(
                card.getId(),
                card.getTitle(),
                card.getDescription(),
                card.getCost(),
                card.getActive(),
                card.isStatus()
        );
    }
}
