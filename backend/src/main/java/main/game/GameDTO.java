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

    public List<PlayerDTO> getPlayerDTOList() {
        List<PlayerDTO> result = new LinkedList<>();
        var players = new LinkedList<>(GameSingleton.getInstance().getGame().getPlayerList());
        for (int i = 0; i < players.size(); i++) {
            result.add(new PlayerDTO(i, players.get(i), i == 0));
        }
        return result;
    }

    public List<Card> getBoardCardList() {
        return game.getBoard().getBoardCards();
    }
}
