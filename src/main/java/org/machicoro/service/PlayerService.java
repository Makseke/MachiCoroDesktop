package org.machicoro.service;

import org.machicoro.entity.EstablishmentCard;
import org.machicoro.entity.LandmarkCard;
import org.machicoro.entity.Player;
import org.machicoro.repository.PlayerRepository;

import java.util.List;

public class PlayerService {
    private PlayerRepository playerRepository = PlayerRepository.getInstance();

    public static PlayerService instance;

    public static PlayerService getInstance() {
        if (instance == null) {
            instance = new PlayerService();
        }
        return instance;
    }

    public void getPlayersInfo() {
        List<Player> players = playerRepository.getPlayers();
        for (Player player : players) {
            System.out.println("NAME: " + player.getName());
            System.out.println("COINS: " + player.getCoins());
            for (EstablishmentCard card : player.getEstablishmentCards()) {
                System.out.println("CARD NAME: " + card.getTitle());
                System.out.println("DESCRIPTION: " + card.getDescription());
                System.out.print("COST: " + card.getCost() + "  ");
                System.out.print("TYPE: " + card.getType() + "  ");
                System.out.print("EFFECT: " + card.getPassive() + "  ");
                System.out.print("COUNT: " + card.getCount() + "  ");
                System.out.println("CONDITION: " + card.getCondition());
            }

            for (LandmarkCard card : player.getLandmarkCards()) {
                System.out.println("CARD NAME: " + card.getTitle());
                System.out.println("DESCRIPTION: " + card.getDescription());
                System.out.print("COST: " + card.getCost() + "  ");
                System.out.println("STATUS " + card.isStatus() + "\n");

            }
        }
    }

    public void getPlayersInfoExceptClient(String clientName) {
        List<Player> players = playerRepository.getPlayers();
        for (Player player : players) {
            if (!player.getName().equals(clientName)) {
                System.out.println("NAME: " + player.getName());
                System.out.println("COINS: " + player.getCoins());
                for (EstablishmentCard card : player.getEstablishmentCards()) {
                    System.out.println("CARD NAME: " + card.getTitle());
                    System.out.println("DESCRIPTION: " + card.getDescription());
                    System.out.print("COST: " + card.getCost() + "  ");
                    System.out.print("TYPE: " + card.getType() + "  ");
                    System.out.print("EFFECT: " + card.getPassive() + "  ");
                    System.out.print("COUNT: " + card.getCount() + "  ");
                    System.out.println("CONDITION: " + card.getCondition());
                }
                for (LandmarkCard card : player.getLandmarkCards()) {
                    System.out.println("CARD NAME: " + card.getTitle());
                    System.out.println("DESCRIPTION: " + card.getDescription());
                    System.out.print("COST: " + card.getCost() + "  ");
                    System.out.println("STATUS " + card.isStatus() + "\n");

                }
            }
        }
    }

    public void getPlayerInfo(String clientName) {
        List<Player> players = playerRepository.getPlayers();
        for (Player player : players) {
            if (player.getName().equals(clientName)) {
                System.out.println("NAME: " + player.getName());
                System.out.println("COINS: " + player.getCoins());
                for (EstablishmentCard card : player.getEstablishmentCards()) {
                    System.out.println("CARD NAME: " + card.getTitle());
                    System.out.println("DESCRIPTION: " + card.getDescription());
                    System.out.print("COST: " + card.getCost() + "  ");
                    System.out.print("TYPE: " + card.getType() + "  ");
                    System.out.print("EFFECT: " + card.getPassive() + "  ");
                    System.out.print("COUNT: " + card.getCount() + "  ");
                    System.out.println("CONDITION: " + card.getCondition());
                }
                for (LandmarkCard card : player.getLandmarkCards()) {
                    System.out.println("CARD NAME: " + card.getTitle());
                    System.out.println("DESCRIPTION: " + card.getDescription());
                    System.out.print("COST: " + card.getCost() + "  ");
                    System.out.println("STATUS " + card.isStatus() + "\n");

                }
            }
        }
    }
}
