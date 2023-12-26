package org.machicoro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.machicoro.enumaration.PlayerType;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player {
    private int connectionId;
    private String name;
    private PlayerType playerType;
    private int coins;
    private List<EstablishmentCard> establishmentCards = new ArrayList<>();
    private List<LandmarkCard> landmarkCards = new ArrayList<>();
}
