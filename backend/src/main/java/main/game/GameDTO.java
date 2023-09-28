package main.game;

import com.stemmer.pokergame.model.Card;
import com.stemmer.pokergame.model.Game;
import main.GameSingleton;
import main.player.PlayerDTO;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

public class GameDTO {

    private Game game;
    public GameDTO(Game game) {
        this.game = game;
    }

    public List<PlayerDTO> getPlayers() {
        List<PlayerDTO> result = new LinkedList<>();
        var players = new LinkedList<>(GameSingleton.getInstance().getGame().getPlayerList());
        for (int i = 0; i < players.size(); i++) {
            result.add(new PlayerDTO(i, players.get(i), players.get(i).getName().equals("Player 1")));
        }
        return result;
    }

    public List<Card> getBoardCards() {
        return game.getBoard().getBoardCards();
    }
}
