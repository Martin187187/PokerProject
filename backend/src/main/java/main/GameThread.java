package main;


import static com.stemmer.pokergame.model.ActionType.CALL;
import static com.stemmer.pokergame.model.ActionType.RAISE;
import com.stemmer.pokergame.model.Board;
import com.stemmer.pokergame.model.Game;
import com.stemmer.pokergame.model.NaiveGame;
import com.stemmer.pokergame.model.player.ConsolePlayer;
import com.stemmer.pokergame.model.player.Player;
import com.stemmer.pokergame.model.player.RepeatAIPlayer;

public class GameThread extends Thread {
    @Override
    public void run() {
        Game game = new NaiveGame(50, new Board());
        GameSingleton.getInstance().setGame(game);

        game.addPlayer(new ConsolePlayer("Player 1", 20000));
        game.addPlayer(new RepeatAIPlayer("Player 2", 20000, RAISE, 0, CALL));
        game.addPlayer(new RepeatAIPlayer("Player 3", 20000, RAISE, 1, CALL));

        while (true) {
            game.printGameState();
            System.out.println(game.playRound());
            game.determineWinners();
            game.terminateLosers();
            game.reset();
            game.shiftPlayers();
            System.out.println("----------------");

            int total = game.getOriginalPlayerList().stream().mapToInt(Player::getTotalMoney).sum();
            if (total != 60000) {
                System.out.println("something went wrong: " + total);
                System.out.println(game.getOriginalPlayerList());
                return;
            }

            if (game.getPlayerList().size() == 1) {
                System.out.println("Winner is: " + game.getPlayerList().poll());
                System.out.println("--");
                System.out.println(game.getOriginalPlayerList());
                return;
            } else if (game.getPlayerList().isEmpty()) {
                System.out.println("no more players in game lol");
                return;
            }
        }
    }
}
