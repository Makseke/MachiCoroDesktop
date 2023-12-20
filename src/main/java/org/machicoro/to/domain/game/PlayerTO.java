package org.machicoro.to.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.machicoro.to.domain.server.ClientTO;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerTO {
    private ClientTO clientTO;
    private int coins;
    private List<EstablishmentCardTO> establishmentCardsTO = new ArrayList<>();
    private List<LandmarkCardTO> landmarkCardsTO = new ArrayList<>();
}
