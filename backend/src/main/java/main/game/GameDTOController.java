package main.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;;
import main.game.GameDTO;
import main.player.PlayerDTO;
import com.stemmer.pokergame.model.Card;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameDTOController {

    private final GameDTOService gameDTOService;

    @Autowired
    public GameDTOController(GameDTOService gameDTOService) {
        this.gameDTOService = gameDTOService;
    }

    @GetMapping("")
    public GameDTO getGameDTOt() {
        return gameDTOService.getGameDTO();
    }

}
