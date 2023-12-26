package org.machicoro.config;

import lombok.*;
import org.machicoro.enumaration.PlayerType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerConfig {
    public static PlayerConfig instance;

    public static PlayerConfig getInstance() {
        if (instance == null) {
            instance = new PlayerConfig();
        }
        return instance;
    }

    private String name;
    private PlayerType playerType = PlayerType.HOST;
}
