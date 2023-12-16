package org.example.to.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardTO {
    private int id;
    private String title;
    private String description;
    private int cost;
}
