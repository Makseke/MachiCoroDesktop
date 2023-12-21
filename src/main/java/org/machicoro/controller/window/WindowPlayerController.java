package org.machicoro.controller.window;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.machicoro.entity.EstablishmentCard;
import org.machicoro.entity.LandmarkCard;
import org.machicoro.entity.Player;
import org.machicoro.repository.PlayerRepository;

public class WindowPlayerController {

    @FXML
    private HBox hBox;

    @FXML
    public void initialize() {
        updatePlayersInfo();
    }

    public void updatePlayersInfo() {
        for (Player player : PlayerRepository.getPlayers()) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER_LEFT);

            Label clientNameLabel = new Label(player.getClient().getName());
            Label clientCoins = new Label("COINS: " + String.valueOf(player.getCoins()));

            VBox establishmentVBox = new VBox();
            VBox landmarkVBox = new VBox();


            Label establishment = new Label("ESTABLISHMENT CARDS: ");
            for (EstablishmentCard card : player.getEstablishmentCards()) {
                establishmentVBox.getChildren().addAll(
                        new Label("CARD NAME: " + card.getTitle()),
                        new Label("DESCRIPTION: " + card.getDescription()),
                        getEsthBox(card),
                        new Label("CONDITION: " + card.getCondition())
                );

            }

            Label landmark = new Label("LANDMARK CARDS: ");
            for (LandmarkCard card : player.getLandmarkCards()) {
                landmarkVBox.getChildren().addAll(
                        new Label("CARD NAME: " + card.getTitle()),
                        new Label("DESCRIPTION: " + card.getDescription()),
                        getLndhBox(card)
                );
            }

            vBox.getChildren().addAll(
                    clientNameLabel,
                    clientCoins,
                    establishment,
                    establishmentVBox,
                    landmark,
                    landmarkVBox
            );
            hBox.getChildren().addAll(vBox);
        }
    }

    private HBox getEsthBox(EstablishmentCard card) {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(
                new Label("COST: " + card.getCost() + "  "),
                new Label("TYPE: " + card.getType() + "  "),
                new Label("EFFECT: " + card.getPassive() + "  "),
                new Label("COUNT: " + card.getCount() + "  ")
        );
        return hbox;
    }

    private HBox getLndhBox(LandmarkCard card) {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(
                new Label("COST: " + card.getCost() + "  "),
                new Label("STATUS: " + card.isStatus() + "  ")
        );
        return hbox;
    }
}
