package org.machicoro.to.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.machicoro.enumaration.PlayerType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerTO implements Serializable {
    private String name;
    private PlayerType playerType;
    private int coins;
    private List<EstablishmentCardTO> establishmentCardsTO = new ArrayList<>();
    private List<LandmarkCardTO> landmarkCardsTO = new ArrayList<>();
}
