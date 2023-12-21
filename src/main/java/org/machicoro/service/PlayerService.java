package org.machicoro.service;

import org.machicoro.entity.Client;
import org.machicoro.entity.EstablishmentCard;
import org.machicoro.entity.LandmarkCard;
import org.machicoro.entity.Player;
import org.machicoro.repository.PlayerRepository;
import org.machicoro.to.domain.server.ClientTO;

import java.util.List;
import java.util.stream.Stream;

public class PlayerService {

    public static void getPlayersInfo() {
        List<Player> players = PlayerRepository.getPlayers();
        for (Player player : players) {
            System.out.println("NAME: " + player.getClient().getName());
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

    public static void getPlayersInfoExceptClient(String clientName) {
        List<Player> players = PlayerRepository.getPlayers();
        for (Player player : players) {
            if (!player.getClient().getName().equals(clientName)) {
                System.out.println("NAME: " + player.getClient().getName());
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

    public static void getPlayerInfo(String clientName) {
        List<Player> players = PlayerRepository.getPlayers();
        for (Player player : players) {
            if (player.getClient().getName().equals(clientName)) {
                System.out.println("NAME: " + player.getClient().getName());
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
