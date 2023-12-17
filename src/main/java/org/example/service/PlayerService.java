package org.example.service;

import org.example.entity.EstablishmentCard;
import org.example.entity.LandmarkCard;
import org.example.entity.Player;
import org.example.repository.PlayerRepository;
import org.example.to.domain.game.PlayerTO;

import java.util.ArrayList;
import java.util.List;

public class PlayerService {

    public static void getPlayersInfo() {
        List<Player> players = PlayerRepository.getPlayers();
        for (Player player: players){
            System.out.println("NAME: " + player.getClient().getName());
            System.out.println("COINS: " + player.getCoins());
            for (EstablishmentCard card: player.getEstablishmentCards()){
                System.out.println("CARD NAME: " + card.getTitle());
                System.out.println("DESCRIPTION: " + card.getDescription());
                System.out.print("COST: " + card.getCost() + "  ");
                System.out.print("TYPE: " + card.getType() + "  ");
                System.out.print("EFFECT: " + card.getPassive() + "  ");
                System.out.print("COUNT: " + card.getCount() + "  ");
                System.out.println("CONDITION: " + card.getCondition());
            }

            for (LandmarkCard card: player.getLandmarkCards()){
                System.out.println("CARD NAME: " + card.getTitle());
                System.out.println("DESCRIPTION: " + card.getDescription());
                System.out.print("COST: " + card.getCost() + "  ");
                System.out.println("STATUS " + card.isStatus() + "\n");

            }
        }
    }

    public static void getPlayersInfoExceptClient(String clientName){

    }
    public static void getPlayerInfo(String clientName){

    }
}
