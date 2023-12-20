package org.machicoro.to.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LandmarkCardTO extends CardTO {
    private boolean status;
    private int active;

    public LandmarkCardTO(int id, String title, String description, int cost, int active, boolean status) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
        this.setCost(cost);
        this.setActive(active);
        this.setStatus(status);
    }
}
