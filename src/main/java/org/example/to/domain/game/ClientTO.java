package org.example.to.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientTO {
    private String ip;
    private int connectionId;
    private String name;
    private String userType = "PLAYER";
}
