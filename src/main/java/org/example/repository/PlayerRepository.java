package org.example.repository;

import lombok.*;
import org.example.entity.Player;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class PlayerRepository {
    @Getter
    @Setter
    private static List<Player> players = new ArrayList<>();
}
