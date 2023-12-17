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

    public EstablishmentCard(int id, String title, String description, int cost, int type, int passive, int count, int condition) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
        this.setCost(cost);
        this.setType(type);
        this.setPassive(passive);
        this.setCount(count);
        this.setCondition(condition);
    }
}

