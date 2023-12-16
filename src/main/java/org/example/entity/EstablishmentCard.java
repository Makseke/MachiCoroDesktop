package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstablishmentCard extends Card {

    // 1 - red , 2 - green, 3 - blue, 4 - purple
    private int type;
    //Effect by cards: +coins or -coins
    private int passive;
    private int count;
    //your move, someone else's, everyone and etc.
    private int condition;
}
