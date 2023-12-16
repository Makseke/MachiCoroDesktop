package org.example.to.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstablishmentCardTO extends CardTO {
    private int type;
    private int passive;
    private int count;
    private int condition;
}
