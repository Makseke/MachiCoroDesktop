package org.machicoro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.machicoro.enumaration.CardType;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstablishmentCard extends Card {

    // 1 - red , 2 - green, 3 - blue, 4 - purple
    private CardType type;
    //Effect by cards: +coins or -coins
    private int passive;
    private int count;
    //your move, someone else's, everyone and etc.
    private List<Integer> condition;

    public EstablishmentCard(int id, String title, String description, int cost, CardType type, int passive, int count, List<Integer> condition) {
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

