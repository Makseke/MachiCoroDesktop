package org.example.repository;

import org.example.entity.EstablishmentCard;
import org.example.entity.LandmarkCard;

public class CardRepository {
    public static EstablishmentCard addEstablishmentCard(int id) {
        switch (id) {
            case 1 -> {
                return new EstablishmentCard(id, "Поля", "Получите 1 монету в ход любого игрока", 1, 3, 1, 1, 1);
            }
            case 2 -> {
                return new EstablishmentCard(id, "Ферма", "Получите 1 монету в свой ход", 1, 3, 1, 1, 1);
            }
            default -> {
                return new EstablishmentCard(id, "Ошибка Id", "Меня не должно тут быть", 100, 1, 100, 1, 1);
            }
        }
    }

    public static LandmarkCard addLandscapeCard(int id) {
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
