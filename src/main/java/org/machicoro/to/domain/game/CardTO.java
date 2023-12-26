package org.machicoro.to.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardTO implements Serializable {
    protected int id;
    protected String title;
    protected String description;
    protected int cost;
}
