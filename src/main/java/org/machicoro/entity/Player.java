package org.machicoro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player {
    private Client client;
    private int coins;
    private List<EstablishmentCard> establishmentCards = new ArrayList<>();
    private List<LandmarkCard> landmarkCards = new ArrayList<>();
}
