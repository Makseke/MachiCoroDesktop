package org.example.to.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Client;
import org.example.entity.EstablishmentCard;
import org.example.entity.LandmarkCard;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerTO {
    private ClientTO clientTO;
    private int coins;
    private List<EstablishmentCardTO> establishmentCardsTO;
    private List<LandmarkCardTO> landmarkCardsTO;
}
