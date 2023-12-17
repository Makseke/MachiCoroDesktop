package org.example.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LandmarkCard extends Card {
    private boolean status;
    private int active;

    public LandmarkCard(int id, String title, String description, int cost, boolean status, int active) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
        this.setCost(cost);
        this.setStatus(status);
        this.setActive(active);
    }
}
