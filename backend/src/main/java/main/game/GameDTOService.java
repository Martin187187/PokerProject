package main.game;
import com.stemmer.pokergame.model.Card;
import main.GameSingleton;
import main.player.PlayerDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import main.game.GameDTO;

@Service
public class GameDTOService {
    private final GameDTO gameDTO;

    public GameDTOService() {
        this.gameDTO = new GameDTO(GameSingleton.getInstance().getGame());
    }

    public GameDTO getGameDTO() {
        return gameDTO;
    }
}
