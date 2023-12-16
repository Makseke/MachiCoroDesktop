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

    public EstablishmentCardTO(int id, String title, String description, int cost, int type, int passive, int count, int condition) {
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
