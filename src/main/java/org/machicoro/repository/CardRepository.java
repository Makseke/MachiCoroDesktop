package org.machicoro.repository;

import lombok.Setter;
import org.machicoro.entity.EstablishmentCard;
import org.machicoro.entity.LandmarkCard;
import org.machicoro.enumaration.CardType;

import java.util.ArrayList;

public class CardRepository {
    @Setter
    private static CardRepository instance;

    public static CardRepository getInstance() {
        if (instance == null) {
            instance = new CardRepository();
        }
        return instance;
    }

    public EstablishmentCard addEstablishmentCard(int id) {
        switch (id) {
            case 1 -> {
                return new EstablishmentCard(id, "Поля", "Получите 1 монету в ход любого игрока", 1, CardType.BLUE_FOR_ALL, 1, 1, new ArrayList<>() {{add(1);}});
            }
            case 2 -> {
                return new EstablishmentCard(id, "Ферма", "Получите 1 монету в свой ход", 1, CardType.GREEN_FOR_PLAYER, 1, 1, new ArrayList<>() {{add(2);}});
            }
            default -> {
                return new EstablishmentCard(id, "Ошибка Id", "Меня не должно тут быть", 100, CardType.BLUE_FOR_ALL, 100, 1, new ArrayList<>() {{add(-1);}});
            }
        }
    }

    public LandmarkCard addLandscapeCard(int id) {
        switch (id) {
            case 1 -> {
                return new LandmarkCard(id, "Вокзал", "Можете бросать 2 кубика вместо 1. В свой ход", 4, false, id);
            }
            case 2 -> {
                return new LandmarkCard(id, "Торговый центр", "Каждое ваше предприятие типа кафе или магазина приносят на одну монету больше.", 10, false, id);
            }
            case 3 -> {
                return new LandmarkCard(id, "Парк развлечений", "Если на кубиках выпал дубль, сделайте еще один ход. В свой ход.", 16, false, id);
            }
            case 4 -> {
                return new LandmarkCard(id, "Радиовышка", "Один раз можете перебросить кубики. В свой ход.", 22, false, id);
            }
            default -> {
                return new LandmarkCard(id, "Ошибка Id", "Меня не должно тут быть", 999, false, 0);
            }
        }
    }
}
