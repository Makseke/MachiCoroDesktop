package org.machicoro.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.machicoro.config.PlayerConfig;
import org.machicoro.entity.EstablishmentCard;
import org.machicoro.entity.LandmarkCard;
import org.machicoro.entity.Player;
import org.machicoro.repository.PlayerRepository;

import java.util.Objects;

public class PlayerController {
    private PlayerRepository playerRepository = PlayerRepository.getInstance();
    private PlayerConfig playerConfig = PlayerConfig.getInstance();


    @FXML
    private HBox playersList;

    @FXML
    private HBox hBox;

    @FXML
    private void showCurrentPlayerCards(){
        hBox.getChildren().clear();
        playersList.getChildren().clear();
        showPlayerCards(playerConfig.getName());
    }

    @FXML
    private void showPlayerCards(String name){
        hBox.getChildren().clear();
        Player player = playerRepository.getPlayerByName(name);
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER_LEFT);

        Label clientNameLabel = new Label(player.getName());
        Label clientCoins = new Label("COINS: " + String.valueOf(player.getCoins()));

        HBox establishmentHBox = new HBox();
        establishmentHBox.setSpacing(20);
        HBox landmarkHBox = new HBox();
        landmarkHBox.setSpacing(20);


        Label establishment = new Label("ESTABLISHMENT CARDS: ");
        for (EstablishmentCard card : player.getEstablishmentCards()) {
            StackPane stackPane = new StackPane(new Rectangle(), new VBox(
                    new Label("CARD NAME: " + card.getTitle()),
                    new Label("DESCRIPTION: " + card.getDescription()) {{setWrapText(true); setMinWidth(200);}},
                    getEsthBox(card),
                    new Label("CONDITION: " + card.getCondition())));
            establishmentHBox.getChildren().addAll(
                    stackPane
            );
        }

        Label landmark = new Label("LANDMARK CARDS: ");
        for (LandmarkCard card : player.getLandmarkCards()) {
            StackPane stackPane = new StackPane(new Rectangle(), new VBox(
                    new Label("CARD NAME: " + card.getTitle()),
                    new Label("DESCRIPTION: " + card.getDescription()) {{setWrapText(true); setMinWidth(200);}},
                    getLndhBox(card)));
            landmarkHBox.getChildren().addAll(
                    stackPane
            );

        }

        ScrollBar verticalSlider = new ScrollBar();
        verticalSlider.setOrientation(javafx.geometry.Orientation.VERTICAL);

        vBox.getChildren().addAll(
                clientNameLabel,
                clientCoins,
                establishment,
                establishmentHBox,
                landmark,
                landmarkHBox
        );
        hBox.getChildren().addAll(vBox);
    }

    @FXML
    private void showPlayersList(){
        hBox.getChildren().clear();
        playersList.getChildren().clear();
        playerRepository.getPlayers().stream().forEach(player -> {
        if (!Objects.equals(player.getName(), playerConfig.getName())){
            playersList.getChildren().addAll(
                    new Button() {{
                        setOnAction(actionEvent -> showPlayerCards(player.getName()));
                    setText(player.getName());
                    }}
            );
        }
        });
    }

    @FXML
    public void initialize() {
        showPlayersList();
    }

    private HBox getEsthBox(EstablishmentCard card) {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(
                new Label("COST: " + card.getCost() + "  "),
                new Label("TYPE: " + card.getType() + "  "),
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
