package main.player;


import com.stemmer.pokergame.model.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerDTOController {

    private final PlayerDTOService playerDTOService;

    @Autowired
    public PlayerDTOController(PlayerDTOService playerDTOService) {
        this.playerDTOService = playerDTOService;
    }

    @PostMapping("/create")
    public PlayerDTO createPlayerDTO(@RequestParam int id, @RequestBody Player player, @RequestParam boolean visible) {
        return playerDTOService.createPlayerDTO(id, player, visible);
    }

    @GetMapping("/all")
    public List<PlayerDTO> getAllPlayerDTOs() {
        return playerDTOService.getAllPlayerDTOs();
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerDTOById(@PathVariable int id) {
        return playerDTOService.getPlayerDTOById(id);
    }
}
