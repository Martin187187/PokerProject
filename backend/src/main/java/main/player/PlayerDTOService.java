package main.player;

import main.GameSingleton;
import org.springframework.stereotype.Service;
import com.stemmer.pokergame.model.player.Player;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerDTOService {
    private List<PlayerDTO> playerDTOList = new ArrayList<>();

    public PlayerDTOService(){
        var players = new LinkedList<>(GameSingleton.getInstance().getGame().getPlayerList());
        for (int i = 0; i < players.size(); i++) {
            playerDTOList.add(new PlayerDTO(i, players.get(i), players.get(i).getName().equals("Player 1")));
        }
    }

    public PlayerDTO createPlayerDTO(int id, Player player, boolean visible) {
        PlayerDTO playerDTO = new PlayerDTO(id, player, visible);
        playerDTOList.add(playerDTO);
        return playerDTO;
    }

    public List<PlayerDTO> getAllPlayerDTOs() {
        playerDTOList.clear();
        var players = new LinkedList<>(GameSingleton.getInstance().getGame().getPlayerList());
        for (int i = 0; i < players.size(); i++) {
            playerDTOList.add(new PlayerDTO(i, players.get(i), i == 0));
        }
        return playerDTOList;
    }

    public PlayerDTO getPlayerDTOById(int id) {
        Optional<PlayerDTO> optionalPlayerDTO = playerDTOList.stream()
                .filter(playerDTO -> playerDTO.getId() == id)
                .findFirst();

        return optionalPlayerDTO.orElse(null);
    }
}
