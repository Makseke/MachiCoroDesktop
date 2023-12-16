package org.example.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LandmarkCard extends Card {
    private boolean status;
    private int active;
}
