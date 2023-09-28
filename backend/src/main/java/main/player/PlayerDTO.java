package main.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stemmer.pokergame.model.Card;
import com.stemmer.pokergame.model.player.Player;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

public class PlayerDTO {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("hand_cards")
    private List<Card> cardList;
    @JsonProperty("total_money")
    private int totalMoney;
    @JsonProperty("invested_money")
    private int investedMoney;
    @JsonProperty("status")
    private String status;

    public PlayerDTO(int id, Player player, boolean visible){
        this.id = id;
        this.name = player.getName();
        this.cardList = visible ? player.getHand().getHandCards() : new LinkedList<>();
        this.totalMoney = player.getTotalMoney();
        this.investedMoney = player.getBettingInvestment();
        this.status = player.isFolded() ? "folded" : "is in";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public int getInvestedMoney() {
        return investedMoney;
    }

    public String getStatus() {
        return status;
    }
}
