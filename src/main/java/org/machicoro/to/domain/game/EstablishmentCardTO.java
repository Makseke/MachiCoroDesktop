package org.machicoro.to.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.machicoro.enumaration.CardType;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstablishmentCardTO extends CardTO implements Serializable {
    private CardType type;
    private int passive;
    private int count;
    private List<Integer> condition;

    public EstablishmentCardTO(int id, String title, String description, int cost, CardType type, int passive, int count, List<Integer> condition) {
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
